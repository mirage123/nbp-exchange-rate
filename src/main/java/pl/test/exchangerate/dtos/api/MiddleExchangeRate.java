package pl.test.exchangerate.dtos.api;

/**
 * @author prabesh on 25/Jan/2023
 */
public class MiddleExchangeRate {
    private String currency;
    private String code;
    private double mid;

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

    public double getMid() {
        return mid;
    }

    public void setMid(double mid) {
        this.mid = mid;
    }
}
