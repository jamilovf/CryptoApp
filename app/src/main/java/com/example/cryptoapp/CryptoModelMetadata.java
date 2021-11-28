package com.example.cryptoapp;

import com.google.gson.annotations.SerializedName;

public class CryptoModelMetadata {

    @SerializedName("name")
    private String name;

    @SerializedName("website_url")
    private String websiteUrl;

    @SerializedName("twitter_url")
    private String twitterUrl;

    @SerializedName("logo_url")
    private String logoUrl;

    public CryptoModelMetadata() {
    }

    public CryptoModelMetadata(String name, String websiteUrl, String twitterUrl, String logoUrl) {
        this.name = name;
        this.websiteUrl = websiteUrl;
        this.twitterUrl = twitterUrl;
        this.logoUrl = logoUrl;
    }

    public String getWebsiteUrl() {
        return websiteUrl;
    }

    public void setWebsiteUrl(String websiteUrl) {
        this.websiteUrl = websiteUrl;
    }

    public String getTwitterUrl() {
        return twitterUrl;
    }

    public void setTwitterUrl(String twitterUrl) {
        this.twitterUrl = twitterUrl;
    }

    public String getLogoUrl() {
        return logoUrl;
    }

    public void setLogoUrl(String logoUrl) {
        this.logoUrl = logoUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
