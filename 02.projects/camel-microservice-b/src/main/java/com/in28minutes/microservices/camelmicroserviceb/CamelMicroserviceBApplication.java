package com.in28minutes.microservices.camelmicroserviceb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;

@SpringBootApplication
public class CamelMicroserviceBApplication {

    public static void main(String[] args) {

        SpringApplication.run(CamelMicroserviceBApplication.class, args);
    }

}