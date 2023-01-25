package pl.test.exchangerate;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;


/**
 * @author prabesh on 25/Jan/2022
 */

@SpringBootApplication
public class ExchangeRateApplication {
    public static void main(String[] args) {
        SpringApplication.run(ExchangeRateApplication.class, args);
    }

    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }

}
