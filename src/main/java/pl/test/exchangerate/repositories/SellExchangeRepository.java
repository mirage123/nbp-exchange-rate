package pl.test.exchangerate.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.test.exchangerate.entities.ExchangeRate;

import java.util.List;
import java.util.Optional;

/**
 * @author prabesh on 25/Jan/2023
 */
public interface SellExchangeRepository extends JpaRepository<ExchangeRate, Integer> {

    Optional<ExchangeRate> findByCodeAndDateAndType(String currencyCode, String date, String type);
    List<ExchangeRate> findByCodeAndDateAndType(String date, String type, String... currencyCode);

}
