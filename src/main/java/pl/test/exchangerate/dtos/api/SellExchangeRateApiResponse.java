package pl.test.exchangerate.dtos.api;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author prabesh on 25/Jan/2023
 */
public class SellExchangeRateApiResponse {
    private String table;
    private String currency;
    private String code;
    private List<Rate> rates;

    public String getTable() {
        return table;
    }

    public void setTable(String table) {
        this.table = table;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public List<Rate> getRates() {
        return rates;
    }

    public void setRates(List<Rate> rates) {
        this.rates = rates;
    }

}
