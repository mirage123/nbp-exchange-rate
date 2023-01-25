package pl.test.exchangerate.services.impl;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientResponseException;
import org.springframework.web.client.RestTemplate;
import pl.test.exchangerate.configs.NbpApiConfig;
import pl.test.exchangerate.dtos.CalculateMiddleExchangeRateRequest;
import pl.test.exchangerate.dtos.SellExchangeRateResponse;
import pl.test.exchangerate.dtos.TotalCostResponse;
import pl.test.exchangerate.dtos.api.MiddleExchangeRate;
import pl.test.exchangerate.dtos.api.MiddleExchangeRateApiResponse;
import pl.test.exchangerate.dtos.api.SellExchangeRateApiResponse;
import pl.test.exchangerate.entities.ExchangeRate;
import pl.test.exchangerate.exceptions.CustomException;
import pl.test.exchangerate.repositories.SellExchangeRepository;
import pl.test.exchangerate.services.ExchangeRateService;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toSet;

/**
 * @author prabesh on 25/Jan/2023
 */

@Service
public class ExchangeRateServiceImpl implements ExchangeRateService {

    private final NbpApiConfig nbpApiConfig;
    private final RestTemplate restTemplate;
    private final SellExchangeRepository sellExchangeRepository;

    public ExchangeRateServiceImpl(NbpApiConfig nbpApiConfig, RestTemplate restTemplate, SellExchangeRepository sellExchangeRepository) {
        this.nbpApiConfig = nbpApiConfig;
        this.restTemplate = restTemplate;
        this.sellExchangeRepository = sellExchangeRepository;
    }

    @Override
    public SellExchangeRateResponse getSellExchangeRate(String currencyCode, String date) {
        final Optional<ExchangeRate> cachedSellExchange = this.sellExchangeRepository.findByCodeAndDateAndType(currencyCode, date, "C");
        final ExchangeRate exchangeRate = cachedSellExchange.orElseGet(() -> getSellExchangeFromApi(currencyCode, date));
        return new SellExchangeRateResponse(exchangeRate.getDate(), exchangeRate.getRate());
    }

    @Override
    public TotalCostResponse calculateMiddleExchangeRate(CalculateMiddleExchangeRateRequest calculateMiddleExchangeRateRequest) {

        final List<MiddleExchangeRate> middleExchangeRateFromApi = this.getMiddleExchangeRateFromApi(calculateMiddleExchangeRateRequest.getDate());

        List<MiddleExchangeRate> requestedMiddleExchangeRates = remaining(middleExchangeRateFromApi, calculateMiddleExchangeRateRequest.getCurrencyCode(),
                MiddleExchangeRate::getCode, requested -> requested,
                Function.identity());

        if (requestedMiddleExchangeRates.size() != calculateMiddleExchangeRateRequest.getCurrencyCode().size()){
            throw new CustomException("Currency code is not found", HttpStatus.BAD_REQUEST);
        }

        final double totalMiddleExchangeRateSum = requestedMiddleExchangeRates.stream().mapToDouble(MiddleExchangeRate::getMid).sum();

       return new TotalCostResponse(totalMiddleExchangeRateSum * calculateMiddleExchangeRateRequest.getAmount());
    }

    public static <A, B, R, I> List<R> remaining(
            List<A> l1, List<B> l2, Function<A, I> aID, Function<B, I> bID, Function<A, R> r) {

        Set<I> b = l2.stream().map(bID).collect(toSet());
        return l1.stream().filter(a -> b.contains(aID.apply(a)))
                .map(r).collect(Collectors.toList());
    }

    private ExchangeRate getSellExchangeFromApi(String currencyCode, String date) {
        try {
            ResponseEntity<SellExchangeRateApiResponse> response
                    = restTemplate.getForEntity(nbpApiConfig.getNbpBaseUrl() + "/rates/c/" + currencyCode + "/" + date + "?format=json", SellExchangeRateApiResponse.class);

            if (response.getBody() != null) {
                SellExchangeRateApiResponse body = response.getBody();
                ExchangeRate exchangeRate = this.convertApiResponseToEntity(body);
                this.sellExchangeRepository.save(exchangeRate);
                return exchangeRate;
            }
        }catch(RestClientResponseException ex){
            throw new CustomException(ex.getStatusText(), HttpStatus.valueOf(ex.getRawStatusCode()));
        }
        return new ExchangeRate();
    }

    private List<MiddleExchangeRate> getMiddleExchangeRateFromApi(String date) {
        try {
            ResponseEntity<List<MiddleExchangeRateApiResponse>> response
                    = restTemplate.exchange(nbpApiConfig.getNbpBaseUrl() + "/tables/a/" + date + "?format=json",
                    HttpMethod.GET, null,
                    new ParameterizedTypeReference<List<MiddleExchangeRateApiResponse>>() {});

            if (response.getBody() != null) {
                List<MiddleExchangeRateApiResponse> body = response.getBody();
                final Optional<MiddleExchangeRateApiResponse> firstObject = body.stream().findFirst();
                if (firstObject.isPresent()) {
                    return this.convertMiddleExchangeApiResponseToEntity(firstObject.get());
                }
            }
        }catch(RestClientResponseException ex){
            throw new CustomException(ex.getStatusText(), HttpStatus.valueOf(ex.getRawStatusCode()));
        }
        return Collections.emptyList();
    }

    private List<MiddleExchangeRate> convertMiddleExchangeApiResponseToEntity(MiddleExchangeRateApiResponse body) {
        return body.getRates();
    }

    private ExchangeRate convertApiResponseToEntity(SellExchangeRateApiResponse apiResponse) {
        ExchangeRate exchangeRate = new ExchangeRate();

        apiResponse.getRates().stream().findFirst().ifPresent(rate -> exchangeRate.setRate(rate.getAsk()));
        apiResponse.getRates().stream().findFirst().ifPresent(rate -> exchangeRate.setDate(rate.getEffectiveDate()));
        exchangeRate.setCode(apiResponse.getCode());
        exchangeRate.setType(apiResponse.getTable());
        return exchangeRate;
    }
}
