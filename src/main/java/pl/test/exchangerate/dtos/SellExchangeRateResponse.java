package pl.test.exchangerate.dtos;

/**
 * @author prabesh on 25/Jan/2023
 */
public class SellExchangeRateResponse {
    private final String date;
    private final double exchangeRate;

    public SellExchangeRateResponse(String date, double exchangeRate) {
        this.date = date;
        this.exchangeRate = exchangeRate;
    }

    public String getDate() {
        return date;
    }

    public double getExchangeRate() {
        return exchangeRate;
    }
}
