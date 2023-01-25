package pl.test.exchangerate.services;

import pl.test.exchangerate.dtos.CalculateMiddleExchangeRateRequest;
import pl.test.exchangerate.dtos.SellExchangeRateResponse;
import pl.test.exchangerate.dtos.TotalCostResponse;

/**
 * @author prabesh on 25/Jan/2023
 */
public interface ExchangeRateService {
    SellExchangeRateResponse getSellExchangeRate(String currencyCode, String date);

    TotalCostResponse calculateMiddleExchangeRate(CalculateMiddleExchangeRateRequest calculateMiddleExchangeRateRequest);
}
