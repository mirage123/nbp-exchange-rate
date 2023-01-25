package pl.test.exchangerate.dtos.api;

import java.util.List;

/**
 * @author prabesh on 25/Jan/2023
 */
public class MiddleExchangeRateApiResponse {
    private String table;
    private String effectiveDate;
    private List<MiddleExchangeRate> rates;

    public String getTable() {
        return table;
    }

    public void setTable(String table) {
        this.table = table;
    }

    public String getEffectiveDate() {
        return effectiveDate;
    }

    public void setEffectiveDate(String effectiveDate) {
        this.effectiveDate = effectiveDate;
    }

    public List<MiddleExchangeRate> getRates() {
        return rates;
    }

    public void setRates(List<MiddleExchangeRate> rates) {
        this.rates = rates;
    }
}
