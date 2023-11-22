package com.tata.currencyexchange.repository;

import com.tata.currencyexchange.entity.ExchangeRate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ExchangeRateRepository extends JpaRepository<ExchangeRate, Long> {

    @Query(value = "SELECT * FROM EXCHANGE_RATE WHERE " +
            "FROM_CURRENCY_ID = (SELECT CURRENCY_ID FROM CURRENCY WHERE CODE = ?1) AND " +
            "TO_CURRENCY_ID = (SELECT CURRENCY_ID FROM CURRENCY WHERE CODE = ?2)", nativeQuery = true)
    Optional<ExchangeRate> getExchangeRateByCurrencyCode(String codeOrigin, String codeDest);

    @Modifying
    @Query(value = "UPDATE EXCHANGE_RATE SET RATE = ?3 WHERE " +
            "FROM_CURRENCY_ID = (SELECT CURRENCY_ID FROM CURRENCY WHERE CODE = ?1) AND " +
            "TO_CURRENCY_ID = (SELECT CURRENCY_ID FROM CURRENCY WHERE CODE = ?2)", nativeQuery = true)
    void updateExchangeRate(String codeOrigin, String codeDest, Double newAmount);
}
