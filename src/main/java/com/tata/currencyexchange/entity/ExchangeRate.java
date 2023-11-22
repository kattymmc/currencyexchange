package com.tata.currencyexchange.entity;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "EXCHANGE_RATE")
public class ExchangeRate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "EXCHANGE_RATE_ID")
    private Long exchangeRateId;

    @ManyToOne
    @JoinColumn(name = "FROM_CURRENCY_ID", referencedColumnName = "CURRENCY_ID")
    private Currency fromCurrencyId;

    @ManyToOne
    @JoinColumn(name = "TO_CURRENCY_ID", referencedColumnName = "CURRENCY_ID")
    private Currency toCurrencyId;

    @Column(name = "RATE")
    private Double rate;

    @Column(name = "DATE_ADDED", updatable = false)
    @CreationTimestamp
    private LocalDateTime dateAdded;

    @Column(name = "DATE_MODIFIED")
    @UpdateTimestamp
    private LocalDateTime dateModified;

}
