package com.example.cryptoapp;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Url;

public interface CryptoAPI {

    @GET(Constants.CURRENCIES_TICKER_URL)
    Observable<List<CryptoModel>> getCurrenciesTickerData();

    @GET
    Observable<List<CryptoModel>> getSpecificCryptoData(@Url String url);

    @GET
    Observable<List<CryptoModelMetadata>> getCryptoMetadata(@Url String url);

}
