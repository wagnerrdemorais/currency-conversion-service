package com.moraisrwagner.currencyconversionservice.controller;

import com.moraisrwagner.currencyconversionservice.controller.bean.CurrencyConversion;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
public class CurrencyConversionController {

    @GetMapping("currency-conversion/from/{from}/to/{to}/quantity/{quantity}")
    public CurrencyConversion calculateCurrencyConversion(
            @PathVariable String from,
            @PathVariable String to,
            @PathVariable BigDecimal quantity
    ) {

        return CurrencyConversion.builder()
                .id(10001L)
                .from(from)
                .to(to)
                .quantity(quantity)
                .conversionMultiple(BigDecimal.ONE)
                .totalCalculatedAmount(BigDecimal.ONE)
                .environment("")
                .build();
    }
}
