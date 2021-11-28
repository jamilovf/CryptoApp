package com.example.cryptoapp;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface CryptoAPI {

    @GET("currencies/ticker?key=1a669b66debde82a7ac91ea89a4db4c194ab117a&ids=BTC,ETH,XRP&interval=1d&convert=USD&per-page=100&page=1")
    Observable<List<CryptoModel>> getData();
}
