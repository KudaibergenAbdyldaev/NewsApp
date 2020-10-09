package com.example.newsapp.Network;

import androidx.lifecycle.MutableLiveData;

import com.example.newsapp.Models.News;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NewsRepository {
    // Добовляем в отдельный класс Call и Response Retrofit, что бы не вызывать снова и снова и для ViewModel(MVVM)
    private static NewsRepository newsRepository;

    public static NewsRepository getInstance(){
        if (newsRepository == null){
            newsRepository = new NewsRepository();
        }
        return newsRepository;
    }

    private ApiInterface newsApi;

    public NewsRepository(){
        newsApi = RetrofitService.createService(ApiInterface.class);
    }

    public MutableLiveData<News> getNews(String source, String key){
        final MutableLiveData<News> newsData = new MutableLiveData<>();
        newsApi.getNewsList(source, key).enqueue(new Callback<News>() {
            @Override
            public void onResponse(Call<News> call,
                                   Response<News> response) {
                if (response.isSuccessful()){
                    assert response.body() != null;
                    newsData.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<News> call, Throwable t) {
                newsData.setValue(null);
            }
        });
        return newsData;
    }
}
