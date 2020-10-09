package com.example.newsapp.MyViewModel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.newsapp.Models.News;
import com.example.newsapp.Network.NewsRepository;

public class MyViewModel extends ViewModel {
    //ViewModel, что бы сохранять состояние
    private MutableLiveData<News> mutableLiveData;
    public static final String API_KEY = "c4b506d5dbe34ca6ba4f18fb8deb721d";

    public void init(){
        if (mutableLiveData != null){
            return;
        }
        NewsRepository newsRepository = NewsRepository.getInstance();
        // Добовляем наш ключ и стану в котором хотим получать новости
        mutableLiveData = newsRepository.getNews("us", API_KEY);

    }

    public LiveData<News> getNewsRepository() {
        return mutableLiveData;
    }
}
