package pl.test.exchangerate.entities;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * @author prabesh on 25/Jan/2023
 */

@Entity
public class ExchangeRate {

    @Id
    private int id;

    private String code;
    private double rate;
    private String date;
    private String type;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
