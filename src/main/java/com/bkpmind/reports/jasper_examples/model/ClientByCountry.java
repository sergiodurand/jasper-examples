package com.bkpmind.reports.jasper_examples.model;

public class ClientByCountry {

    private String country;
    private int total;

    public ClientByCountry(String country, int total) {
        this.country = country;
        this.total = total;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }
    
}