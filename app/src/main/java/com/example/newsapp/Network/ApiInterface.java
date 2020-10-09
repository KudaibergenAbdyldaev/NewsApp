package com.example.newsapp.Network;

import com.example.newsapp.Models.News;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiInterface {
    //For retrofit
    @GET("top-headlines")
    Call<News> getNewsList(
            @Query("country") String country,
            @Query("apiKey") String apiKey);
}
