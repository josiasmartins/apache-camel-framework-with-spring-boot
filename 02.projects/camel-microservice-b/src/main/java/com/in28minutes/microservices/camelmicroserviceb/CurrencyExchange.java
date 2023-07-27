package com.in28minutes.microservices.camelmicroserviceb;

import java.math.BigDecimal;

public class CurrencyExchange {

    private Long id;
    private String from;
    private String to;
    private BigDecimal conversaionMultiple;

    public CurrencyExchange() {

    }

    public CurrencyExchange(Long id, String from, String to, BigDecimal conversionMultiple) {
        super();
        this.id = id;
        this.from = from;
        this.to = to;
        this.conversaionMultiple = conversionMultiple;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public BigDecimal getConversaionMultiple() {
        return conversaionMultiple;
    }

    public void setConversaionMultiple(BigDecimal conversaionMultiple) {
        this.conversaionMultiple = conversaionMultiple;
    }
}
