package com.example.cryptoapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.github.twocoffeesoneteam.glidetovectoryou.GlideToVectorYou;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class CryptoMetadataActivity extends AppCompatActivity {
    Retrofit retrofit;

    CompositeDisposable compositeDisposable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crypto_metadata);

        final String BASE_URL = this.getResources().getString(R.string.base_url);

        Gson gson = new GsonBuilder().setLenient().create();

        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        loadData();

    }

    private void loadData() {

        final CryptoAPI cryptoAPI = retrofit.create(CryptoAPI.class);

        compositeDisposable = new CompositeDisposable();

        System.err.println(String.format(Constants.SPECIFIC_CURRENCIES_METADATA_URL,getIntent().getStringExtra("currency")));

        compositeDisposable.
                add(cryptoAPI.getCryptoMetadata(String.format(Constants.SPECIFIC_CURRENCIES_METADATA_URL,getIntent().getStringExtra("currency")))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(cryptoModelMetadata -> handleResponse(cryptoModelMetadata)));

    }

    private void handleResponse(List<CryptoModelMetadata> cryptoModelMetadataList) {

        TextView cryptoNameTextView = findViewById(R.id.cryptoNameTextView);
        TextView websiteTextView = findViewById(R.id.websiteTextView);
        TextView blogsiteTextView = findViewById(R.id.blogsiteTextView);
        ImageView cryptoLogoImageView = findViewById(R.id.cryptoLogoImageView);

        cryptoNameTextView.setText(cryptoModelMetadataList.get(0).getName());
        websiteTextView.setText(cryptoModelMetadataList.get(0).getWebsiteUrl());
        blogsiteTextView.setText(cryptoModelMetadataList.get(0).getBlogUrl());
        GlideToVectorYou.init().with(this)
                .load(Uri.parse(cryptoModelMetadataList.get(0).getLogoUrl()), cryptoLogoImageView);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();

        compositeDisposable.clear();
    }
}