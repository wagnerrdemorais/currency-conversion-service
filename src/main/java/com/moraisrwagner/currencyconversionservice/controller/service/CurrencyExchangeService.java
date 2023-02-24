package com.moraisrwagner.currencyconversionservice.controller.service;

import com.moraisrwagner.currencyconversionservice.controller.bean.CurrencyConversion;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.HashMap;

@Service
public class CurrencyExchangeService {

    public CurrencyConversion getCurrencyConversionExchange(String from, String to, BigDecimal quantity) {
        var uriVariables = new HashMap<String, String>();
        uriVariables.put("from", from);
        uriVariables.put("to", to);

        var responseEntity = new RestTemplate().getForEntity(
                "http://localhost:8000/currency-exchange/from/{from}/to/{to}",
                CurrencyConversion.class,
                uriVariables);

        var currencyConversion = responseEntity.getBody();

        return CurrencyConversion.builder()
                .id(currencyConversion.getId())
                .from(from)
                .to(to)
                .quantity(quantity)
                .conversionMultiple(currencyConversion.getConversionMultiple())
                .totalCalculatedAmount(quantity.multiply(currencyConversion.getConversionMultiple()))
                .environment(currencyConversion.getEnvironment())
                .build();

    }
}
