package com.moraisrwagner.currencyconversionservice.controller;

import com.moraisrwagner.currencyconversionservice.controller.bean.CurrencyConversion;
import com.moraisrwagner.currencyconversionservice.controller.service.CurrencyExchangeService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
@AllArgsConstructor
public class CurrencyConversionController {

    private final CurrencyExchangeService currencyExchangeService;

    @GetMapping("currency-conversion/from/{from}/to/{to}/quantity/{quantity}")
    public CurrencyConversion calculateCurrencyConversion(
            @PathVariable String from,
            @PathVariable String to,
            @PathVariable BigDecimal quantity
    ) {

        return currencyExchangeService.getCurrencyConversionExchange(from, to, quantity);
    }
}
