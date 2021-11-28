package com.example.cryptoapp;

import com.google.gson.annotations.SerializedName;

public class CryptoModelMetadata {

    @SerializedName("name")
    private String name;

    @SerializedName("website_url")
    private String websiteUrl;

    @SerializedName("blog_url")
    private String blogUrl;

    @SerializedName("logo_url")
    private String logoUrl;

    public CryptoModelMetadata() {
    }

    public CryptoModelMetadata(String name, String websiteUrl, String blogUrl, String logoUrl) {
        this.name = name;
        this.websiteUrl = websiteUrl;
        this.blogUrl = blogUrl;
        this.logoUrl = logoUrl;
    }

    public String getWebsiteUrl() {
        return websiteUrl;
    }

    public void setWebsiteUrl(String websiteUrl) {
        this.websiteUrl = websiteUrl;
    }

    public String getBlogUrl() {
        return blogUrl;
    }

    public void setBlogUrl(String blogUrl) {
        this.blogUrl = blogUrl;
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
