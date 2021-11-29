package com.example.cryptoapp;

public class Constants {
    public static final String CURRENCIES_TICKER_URL = "currencies/ticker?key=1a669b66debde82a7ac91ea89a4db4c194ab117a&interval=1d&convert=USD&per-page=10&page=1";
    public static final String SPECIFIC_CURRENCIES_TICKER_URL = "currencies/ticker?key=1a669b66debde82a7ac91ea89a4db4c194ab117a&ids=%s&interval=1d&convert=USD&per-page=100&page=1";
    public static final String SPECIFIC_CURRENCIES_METADATA_URL = "currencies?key=1a669b66debde82a7ac91ea89a4db4c194ab117a&ids=%s&attributes=name,website_url,twitter_url,facebook_url,youtube_url,logo_url";
}
