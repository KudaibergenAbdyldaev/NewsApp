package com.example.newsapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Bundle;
import android.os.Handler;

import com.example.newsapp.Adapter.NewsAdapter;
import com.example.newsapp.Models.Article;
import com.example.newsapp.MyViewModel.MyViewModel;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ArrayList<Article> articleArrayList = new ArrayList<>();
    NewsAdapter newsAdapter;
    RecyclerView recyclerView;
    MyViewModel newsViewModel;
    SwipeRefreshLayout swipeRefreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        swipeRefreshLayout = findViewById(R.id.swipeRefreshLayout);

        newsViewModel = ViewModelProviders.of(MainActivity.this).get(MyViewModel.class);
        newsViewModel.init();
        //call
        callObserver();
        setupRecyclerView();
        //Refreshing
        swipeRefreshLayout.setOnRefreshListener(() -> {
            new Handler().postDelayed(() -> {
                setupRecyclerView();
                swipeRefreshLayout.setRefreshing(false);
            }, 1000);
        });

    }
//observe ViewModel
    private void callObserver() {
        newsViewModel.getNewsRepository().observe(MainActivity.this, news -> {
            List<Article> newsArticles = news.getArticles();
            articleArrayList.addAll(newsArticles);
            newsAdapter.notifyDataSetChanged();
        });
    }
//put the value to RecyclerView
    private void setupRecyclerView() {
        if (newsAdapter == null) {
            newsAdapter = new NewsAdapter(MainActivity.this, articleArrayList);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
            recyclerView.setAdapter(newsAdapter);
            recyclerView.setItemAnimator(new DefaultItemAnimator());
            recyclerView.setNestedScrollingEnabled(true);
        } else {
            newsAdapter.notifyDataSetChanged();
        }
    }
}