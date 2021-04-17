package com.simi.microservices2.currencyconversionservice;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
public class ConversionController {

    @GetMapping("currency-conversion/from/{from}/to/{to}/quantity/{quantity}")
    public CurrencyConversion getCurrencyConversion(
            @PathVariable String to,
            @PathVariable String from,
            @PathVariable BigDecimal quantity) {

        return new CurrencyConversion(Long.valueOf(1L), from, to, quantity,
                BigDecimal.valueOf(1L), BigDecimal.valueOf(2L));

    }
}
