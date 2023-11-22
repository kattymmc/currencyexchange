package com.tata.currencyexchange.model;

import lombok.Data;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
public class CurrencyResponse {
    Double amount;
    Double amountConverted;
    String originCurrency;
    String destinationCurrency;
    Double exchangeRate;
}
