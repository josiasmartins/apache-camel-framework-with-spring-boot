package com.in28minutes.microservices.camelmicroserviceb.routes;

import com.in28minutes.microservices.camelmicroserviceb.CurrencyExchange;
import org.apache.camel.builder.RouteBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

//@Component
public class KafkaReiceverRouter extends RouteBuilder {

    @Override
    public void configure() throws Exception {

        from("kafka:myKafkaTopic")
                .to("log:receiver-message-from-kafka");

    }
}
