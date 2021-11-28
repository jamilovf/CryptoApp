package com.example.cryptoapp;

import com.google.gson.annotations.SerializedName;

public class CryptoModel {

    @SerializedName("currency")
    private String currency;

    @SerializedName("price")
    private String price;

    @SerializedName("logo_url")
    private String logoUrl;

    public CryptoModel(String price, String currency, String logoUrl) {
        this.price = price;
        this.currency = currency;
        this.logoUrl = logoUrl;
    }

    public CryptoModel() {
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getLogoUrl() {
        return logoUrl;
    }

    public void setLogoUrl(String logoUrl) {
        this.logoUrl = logoUrl;
    }
}
