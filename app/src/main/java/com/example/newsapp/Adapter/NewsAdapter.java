package com.example.newsapp.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.newsapp.DetailActivity;
import com.example.newsapp.Models.Article;
import com.example.newsapp.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.NewsViewHolder> {

    Context context;
    ArrayList<Article> articles;

    public NewsAdapter(Context context, ArrayList<Article> articles) {
        this.context = context;
        this.articles = articles;
    }

    @NonNull
    @Override
    public NewsAdapter.NewsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.news_item, parent, false);
        return new NewsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NewsAdapter.NewsViewHolder holder, int position) {
        //Присваиваем полученные данные в RecyclerView
        holder.author.setText(articles.get(position).getAuthor());
        holder.title.setText(articles.get(position).getTitle());
        holder.desc.setText(articles.get(position).getDescription());
        holder.publishedAd.setText(articles.get(position).getPublishedAt());
        Picasso.get()
                .load(articles.get(position).getUrlToImage())
                .into(holder.img);

        // Если нажать одно из новостей то мы переходим в DetailActivity
        // И через intent передаем данные
        holder.frameLayout.setOnClickListener(view -> {
            Intent intent = new Intent(context, DetailActivity.class);
            intent.putExtra("title", articles.get(position).getAuthor());
            intent.putExtra("desc", articles.get(position).getDescription());
            intent.putExtra("data", articles.get(position).getPublishedAt());
            intent.putExtra("author", articles.get(position).getAuthor());
            intent.putExtra("content", articles.get(position).getContent());
            intent.putExtra("img", articles.get(position).getUrlToImage());
            intent.putExtra("url",articles.get(position).getUrl());
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return articles.size();
    }

    public static class NewsViewHolder extends RecyclerView.ViewHolder{

        ImageView img;
        TextView author, title, desc;
        TextView publishedAd;
        FrameLayout frameLayout;

        public NewsViewHolder(@NonNull View itemView) {
            super(itemView);

            img =itemView.findViewById(R.id.img);

            author = itemView.findViewById(R.id.author);
            title = itemView.findViewById(R.id.title);
            desc = itemView.findViewById(R.id.desc);

            publishedAd = itemView.findViewById(R.id.publishedAt);

            frameLayout = itemView.findViewById(R.id.frame_layout);

        }
    }
}