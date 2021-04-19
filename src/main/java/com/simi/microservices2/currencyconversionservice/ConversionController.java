package com.simi.microservices2.currencyconversionservice;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.xml.ws.Response;
import java.math.BigDecimal;
import java.util.HashMap;

@RestController
public class ConversionController {

    @GetMapping("currency-conversion/from/{from}/to/{to}/quantity/{quantity}")
    public CurrencyConversion getCurrencyConversion(
            @PathVariable String to,
            @PathVariable String from,
            @PathVariable BigDecimal quantity) {

        HashMap<String, String> uriVariables = new HashMap<>();
        uriVariables.put("from", from);
        uriVariables.put("to", to);

        ResponseEntity<CurrencyConversion> entity =  new RestTemplate().getForEntity(
            "http://localhost:8001/currency-exchange/from/{from}/to/{to}",
                CurrencyConversion.class,
                uriVariables);

        CurrencyConversion currencyConversion = entity.getBody();
        currencyConversion.setQuantity(quantity);
        currencyConversion.setTotalCalculatedAmount(
                currencyConversion.getConversionMultiple().multiply(quantity));

        return currencyConversion;
    }
}
