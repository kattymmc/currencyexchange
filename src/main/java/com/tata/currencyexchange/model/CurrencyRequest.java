package com.tata.currencyexchange.model;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class CurrencyRequest {

    @NotNull(message = "El monto no puede ser nulo")
    Double amount;

    @NotBlank(message = "La moneda origen no puede estar vacía")
    String originCurrency;

    @NotBlank(message = "La moneda destino no puede estar vacía")
    String destinationCurrency;
}