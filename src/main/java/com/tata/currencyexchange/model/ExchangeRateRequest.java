package com.tata.currencyexchange.model;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class ExchangeRateRequest {

    @NotNull(message = "El tipo de cambio no puede ser nulo")
    Double rate;

    @NotBlank(message = "La moneda origen no puede estar vacía")
    String originCurrency;

    @NotBlank(message = "La moneda destino no puede estar vacía")
    String destinationCurrency;
}
