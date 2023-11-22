package com.tata.currencyexchange.controller;

import com.tata.currencyexchange.model.CurrencyRequest;
import com.tata.currencyexchange.model.CurrencyResponse;
import com.tata.currencyexchange.model.ExchangeRateRequest;
import com.tata.currencyexchange.service.ExchangeRateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.Map;

@Controller
@RequestMapping("/api")
public class CurrencyExchangeController {

    @Autowired
    ExchangeRateService exchangeRateService;

    @PostMapping("/exchange-rate")
    public ResponseEntity<?> convertCurrencyAmount(@Valid @RequestBody CurrencyRequest currencyRequest) {
        CurrencyResponse cr = exchangeRateService.calculateConversion(currencyRequest);

        if(cr != null)
            return ResponseEntity.ok(cr);
        else
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("No se tiene registrado el tipo de cambio para las monedas.");
    }

    @PutMapping("/update-exchange-rate")
    public ResponseEntity<?> updateExchangeRate(@Valid @RequestBody ExchangeRateRequest exchangeRateRequest) {
        Boolean success = exchangeRateService.updateExchangeRate(exchangeRateRequest);
        if(success)
            return ResponseEntity.status(HttpStatus.CREATED).body("Se actualizo el tipo de cambio exitosamente.");
        else
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("No se pudo actualizar el tipo de cambio.");

    }
}
