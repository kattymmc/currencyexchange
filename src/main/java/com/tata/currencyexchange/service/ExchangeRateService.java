package com.tata.currencyexchange.service;

import com.tata.currencyexchange.entity.ExchangeRate;
import com.tata.currencyexchange.model.CurrencyRequest;
import com.tata.currencyexchange.model.CurrencyResponse;
import com.tata.currencyexchange.model.ExchangeRateRequest;
import com.tata.currencyexchange.repository.ExchangeRateRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class ExchangeRateService {

    private final ExchangeRateRepository exchangeRateRepository;
    public CurrencyResponse calculateConversion(CurrencyRequest currencyRequest) {

        String codeOrigin = currencyRequest.getOriginCurrency();
        String codeDest = currencyRequest.getDestinationCurrency();
        Optional<ExchangeRate> exRate = exchangeRateRepository.getExchangeRateByCurrencyCode(codeOrigin, codeDest);

        if(exRate.isPresent()) {
            Double amountConverted = currencyRequest.getAmount() * exRate.get().getRate();

            return CurrencyResponse.builder()
                    .amount(currencyRequest.getAmount())
                    .amountConverted(amountConverted)
                    .originCurrency(codeOrigin)
                    .destinationCurrency(codeDest)
                    .exchangeRate(exRate.get().getRate())
                    .build();
        } else {
            return null;
        }
    }

    @Transactional
    public Boolean updateExchangeRate(ExchangeRateRequest exchangeRateReq) {
        try {
            exchangeRateRepository.updateExchangeRate(exchangeRateReq.getOriginCurrency(), exchangeRateReq.getDestinationCurrency(), exchangeRateReq.getRate());
            return true;
        } catch (Exception e) {
            log.error("Error al actualizar el tipo de cambio: " + e);
            return false;
        }
    }
}
