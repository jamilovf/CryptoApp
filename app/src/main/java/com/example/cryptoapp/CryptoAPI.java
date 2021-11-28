package com.example.cryptoapp;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Url;

public interface CryptoAPI {

    //@GET("currencies/ticker?key=1a669b66debde82a7ac91ea89a4db4c194ab117a&ids=BTC,ETH,XRP&interval=1d&convert=USD&per-page=100&page=1")
    @GET(Constants.CURRENCIES_TICKER_URL)
    Observable<List<CryptoModel>> getCurrenciesTickerData();

    @GET
    Observable<List<CryptoModel>> getSpecificCryptoData(@Url String url);

    @GET
    Observable<List<CryptoModelMetadata>> getCryptoMetadata(@Url String url);

}
