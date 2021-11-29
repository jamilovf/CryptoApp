package com.example.cryptoapp;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    List<CryptoModel> cryptoModels;
    Retrofit retrofit;
    RecyclerView recyclerView;
    ItemListAdapter itemListAdapter;

    CompositeDisposable compositeDisposable;

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final String BASE_URL = this.getResources().getString(R.string.base_url);

        recyclerView = findViewById(R.id.recyclerView);

        Gson gson = new GsonBuilder().setLenient().create();

        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();


        loadData();

    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void loadData() {

        final CryptoAPI cryptoAPI = retrofit.create(CryptoAPI.class);

        compositeDisposable = new CompositeDisposable();

        compositeDisposable.add(cryptoAPI.getCurrenciesTickerData()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(cryptoModelList -> handleResponse(cryptoModelList)));

    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void handleResponse(List<CryptoModel> cryptoModelList) {
        cryptoModels = new ArrayList<>(cryptoModelList);
        cryptoModels = cryptoModels.stream()
        .sorted(((c1, c2) ->
                Double.compare(Double.parseDouble(c2.getPrice()),
                        Double.parseDouble(c1.getPrice()))))
        .collect(Collectors.toList());

        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
        itemListAdapter = new ItemListAdapter(this, cryptoModels);
        recyclerView.setAdapter(itemListAdapter);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();

        compositeDisposable.clear();
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void searchSpecificCrypto(View view) {
        EditText editText = findViewById(R.id.searchText);

        final CryptoAPI cryptoAPI = retrofit.create(CryptoAPI.class);

        compositeDisposable.add(cryptoAPI
                .getSpecificCryptoData(String.format(Constants.SPECIFIC_CURRENCIES_TICKER_URL,editText.getText()))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(cryptoModelList -> handleResponse(cryptoModelList)));
    }
}