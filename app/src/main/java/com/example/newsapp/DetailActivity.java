package com.example.newsapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class DetailActivity extends AppCompatActivity {

    TextView title, desc, author, content, data;
    ImageView img;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        title = (TextView) findViewById(R.id.title);
        desc = (TextView) findViewById(R.id.desc);
        author = (TextView) findViewById(R.id.author);
        content = (TextView) findViewById(R.id.content);
        data = (TextView) findViewById(R.id.publishedAt);
        button = (Button) findViewById(R.id.button);
        img = (ImageView) findViewById(R.id.img);

        final Intent intent = getIntent();

        String name = intent.getStringExtra("title");
        title.setText(name);

        String desc1 = intent.getStringExtra("desc");
        desc.setText(desc1);

        String author1 = intent.getStringExtra("author");
        author.setText(author1);

        String content1 = intent.getStringExtra("content");
        content.setText(content1);

        String data1 = intent.getStringExtra("data");
        data.setText(data1);


        String image_url = intent.getStringExtra("img");
        Picasso.get().load(image_url).into(img);

        final String url = intent.getStringExtra("url");
        // Если нажать на кнопку, то мы переходим в Chrome, что узнать по больше информации
        button.setOnClickListener(view -> {
            Intent intent1 = new Intent(Intent.ACTION_VIEW);
            intent1.setData(Uri.parse(url));
            startActivity(intent1);
        });

    }

}