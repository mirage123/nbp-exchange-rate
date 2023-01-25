package pl.test.exchangerate.configs;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * @author prabesh on 25/Jan/2023
 */

@Configuration
public class NbpApiConfig {
    @Value("${api.nbp.exchange.rates}")
    private String nbpBaseUrl;

    public String getNbpBaseUrl() {
        return nbpBaseUrl;
    }

    public void setNbpBaseUrl(String nbpBaseUrl) {
        this.nbpBaseUrl = nbpBaseUrl;
    }
}
