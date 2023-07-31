package com.in28minutes.microservices.camelmicroserviceb;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
public class CurrencyExchangeController {

    @GetMapping("/currency-exchange/from/{from}/to/{to}")
    public CurrencyExchange findConversionValue(
            @PathVariable String from,
            @PathVariable String to
    ) {
        System.out.println("IBG TEst");
        CurrencyExchange currencyExchange = new CurrencyExchange(1001L, "USD", "INR", BigDecimal.TEN);
        System.out.println(currencyExchange + ": ibg currencyExchange");
        return new CurrencyExchange(1001L, from, to, BigDecimal.TEN);
    }

}
