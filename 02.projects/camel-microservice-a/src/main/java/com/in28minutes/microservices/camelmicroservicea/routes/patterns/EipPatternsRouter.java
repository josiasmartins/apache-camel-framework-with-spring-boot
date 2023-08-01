package com.in28minutes.microservices.camelmicroservicea.routes.patterns;

import com.in28minutes.microservices.camelmicroserviceb.CurrencyExchange;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.dataformat.JsonLibrary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class EipPatternsRouter extends RouteBuilder {

    @Autowired
    SplitterComponent splitter;

    @Override
    public void configure() throws Exception {
        // Pipeline
        // Content Based Routing - choice()
        // Multicast

//        from("timer:multicast?period=10000")
//                .multicast()
//                .to("log:something1", "log:something2", "log:something3");

//        from("file:files/csv")
//                .unmarshal().csv()
//                .split(body())
//                .to("activemq:split-queue");

        // message1, message2, messgae3
//        from("file:files/csv")
//                .convertBodyTo(String.class)
//                //.split(body(), ",")
//                .split(method(splitter))
//                .to("activemq:split-queue");

        // Aggregate
        // Messages => Aggregate => Endpoint
        // to, 3
        from("file:files/aggregate-json")
                .unmarshal().json(JsonLibrary.Jackson, CurrencyExchange.class)
                .aggregate(simple("${body.to}"), new ArrayListAggregationStrategy())
                .completionSize(3)
                //.completionTimeout(HIGHEST)
                .to("log:aggregation-json");


    }
}

@Component
class SplitterComponent {

    public List<String> splitInput(String body) {
        return List.of("ABC", "DEF", "GHI");
    };

}
