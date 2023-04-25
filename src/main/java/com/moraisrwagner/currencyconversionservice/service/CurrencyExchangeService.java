package com.moraisrwagner.currencyconversionservice.service;

import com.moraisrwagner.currencyconversionservice.bean.CurrencyConversion;
import com.moraisrwagner.currencyconversionservice.proxy.CurrencyExchangeProxy;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.HashMap;

@Service
@AllArgsConstructor
public class CurrencyExchangeService {

    private final CurrencyExchangeProxy currencyExchangeProxy;
    private final RestTemplate restTemplate;

    public CurrencyConversion getCurrencyConversionExchange(String from, String to, BigDecimal quantity) {
        var uriVariables = new HashMap<String, String>();
        uriVariables.put("from", from);
        uriVariables.put("to", to);

        var responseEntity = restTemplate.getForEntity(
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

    public CurrencyConversion getCurrencyConversionExchangeFeign(String from, String to, BigDecimal quantity) {
        var currencyConversion = currencyExchangeProxy.retrieveExchangeValue(from, to);

        return CurrencyConversion.builder()
                .id(currencyConversion.getId())
                .from(from)
                .to(to)
                .quantity(quantity)
                .conversionMultiple(currencyConversion.getConversionMultiple())
                .totalCalculatedAmount(quantity.multiply(currencyConversion.getConversionMultiple()))
                .environment(currencyConversion.getEnvironment() + " feign")
                .build();
    }
}
