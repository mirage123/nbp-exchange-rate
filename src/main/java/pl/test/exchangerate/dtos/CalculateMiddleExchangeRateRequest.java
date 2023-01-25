package pl.test.exchangerate.dtos;

import java.util.List;

/**
 * @author prabesh on 25/Jan/2023
 */
public class CalculateMiddleExchangeRateRequest {
    private List<String> currencyCode;
    private double amount;
    private String date;

    public List<String> getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(List<String> currencyCode) {
        this.currencyCode = currencyCode;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
