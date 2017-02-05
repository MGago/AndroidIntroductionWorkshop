package com.example.mariogago.introductionworkshop.api;

import android.location.Location;

import java.util.List;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SearchEvents {

    private static final String BASE_URL = "https://www.eventbriteapi.com/v3/";
    private static final String WITHIN = "20km";
    private static final String EXPAND = "venue,category";
    private static final String AUTH_TOKEN = "P5CZPNXOBJ2BKFDH7QKJ";
    private final EventbriteApi service;

    public SearchEvents() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        service = retrofit.create(EventbriteApi.class);
    }

    public void search(Location location, final Callback callback) {
        service.eventSearch(AUTH_TOKEN, EXPAND, WITHIN, location.getLatitude(), location
                .getLongitude()).enqueue(new retrofit2.Callback<SearchResponse>() {
            @Override
            public void onResponse(Call<SearchResponse> call, Response<SearchResponse> response) {
                if (response.isSuccessful()) {
                    callback.onEventResults(response.body().events);
                } else {
                    callback.onError();
                }
            }

            @Override
            public void onFailure(Call<SearchResponse> call, Throwable t) {
                callback.onError();
            }
        });
    }

    public interface Callback {
        void onEventResults(List<SearchResponse.Event> event);
        void onError();
    }
}