package com.in28minutes.microservices.camelmicroserviceb.routes;

import com.in28minutes.microservices.camelmicroserviceb.CurrencyExchange;
import org.apache.camel.builder.RouteBuilder;
//import org.apache.camel.model.dataformat.CryptoDataFormat;
import org.apache.camel.converter.crypto.CryptoDataFormat;
import org.apache.camel.model.dataformat.JsonLibrary;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.math.BigDecimal;
import java.security.*;
import java.security.cert.CertificateException;

@Component
public class ActiveMqReceiverRouter extends RouteBuilder {
    @Autowired
    private MyCurrencyExchangeProcessor myCurrencyExchangeProcessor;

    @Autowired
    private MyCurrencyExchangeTransformer myCurrencyExchangeTransformer;

    @Override
    public void configure() throws Exception {

        // JSON
        // CurrencyExchange
        // {
        //  "id": 1000,
        //  "from": "USD",
        //  "to": "INR",
        //  "conversionMultiple": 70
        //}

        from("activemq:my-activemq-queue")
                .marshal(createEncryptor())
//                .unmarshal()
//                .json(JsonLibrary.Jackson, CurrencyExchange.class)
//                .bean(myCurrencyExchangeProcessor)
//                .bean(myCurrencyExchangeTransformer)
                .to("log:receiver-message-from-active-mq");

//        from("activemq:my-activemq-xml-queue")
//                .unmarshal()
//                .jacksonxml(CurrencyExchange.class)
//                .to("log:receiver-message-from-active-mq");

//        from("activemq:my-activemq-xml-queue")
//                .unmarshal()
//                .jacksonxml(CurrencyExchange.class)
//                .to("log:receiver-message-from-active-mq");

//        from("activemq:split-queue")
//                .to("log:received-message-from-active-mq");

    }

    private CryptoDataFormat createEncryptor() throws KeyStoreException, IOException, NoSuchAlgorithmException,
            CertificateException, UnrecoverableKeyException {
        KeyStore keyStore = KeyStore.getInstance("JCEKS");
        ClassLoader classLoader = getClass().getClassLoader();
        keyStore.load(classLoader.getResourceAsStream("myDeskey.jceks"), "someKeystorePassword".toCharArray());
        Key sharedKey = keyStore.getKey("myDesKey", "someKeyPassword".toCharArray());

        CryptoDataFormat sharedKeyCrypto = new CryptoDataFormat("DES", sharedKey);
        return sharedKeyCrypto;
    }
}

@Component
class MyCurrencyExchangeProcessor {

    Logger logger = LoggerFactory.getLogger(ActiveMqReceiverRouter.class);

    public void processMessage(CurrencyExchange currencyExchange) {

        logger.info("Do some processing with currencyExchange.getConversationMultiple() value is {}",
                CurrencyExchange.class);

    }

}

@Component
class MyCurrencyExchangeTransformer {

    Logger logger = LoggerFactory.getLogger(ActiveMqReceiverRouter.class);

    public CurrencyExchange processMessage(CurrencyExchange currencyExchange) {

        currencyExchange.setConversaionMultiple(
                currencyExchange.getConversaionMultiple().multiply(BigDecimal.TEN)
        );

        return currencyExchange;

    }

}
