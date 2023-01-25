package pl.test.exchangerate.controllers;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;
import pl.test.exchangerate.dtos.CalculateMiddleExchangeRateRequest;
import pl.test.exchangerate.dtos.SellExchangeRateResponse;
import pl.test.exchangerate.dtos.TotalCostResponse;
import pl.test.exchangerate.services.ExchangeRateService;

/**
 * @author prabesh on 25/Jan/2023
 */

@RestController
@RequestMapping("/api")
public class ExchangeRateController {
    private final ExchangeRateService exchangeRateService;

    public ExchangeRateController(ExchangeRateService exchangeRateService) {
        this.exchangeRateService = exchangeRateService;
    }

    /**
     * This is usecase 1.
     * @param currencyCode currency code (ISO 4217 format).
     * @param date required date
     * @return SellExchange
     */
    @GetMapping("/sell-exchange-rate/{currencyCode}/{date}")
    public SellExchangeRateResponse getSellExchangeRate(@PathVariable String currencyCode, @PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") String date){
        return this.exchangeRateService.getSellExchangeRate(currencyCode, date);

    }

    /**
     * This is usecase 2
     * @param calculateMiddleExchangeRateRequest request body
     * @return sum of all requested currency code * amount
     */
    @ResponseBody
    @PostMapping("/calculate/middle/exchange-rate")
    public TotalCostResponse calculateMiddleExchangeRate(@RequestBody CalculateMiddleExchangeRateRequest calculateMiddleExchangeRateRequest){
        return this.exchangeRateService.calculateMiddleExchangeRate(calculateMiddleExchangeRateRequest);

    }
}
