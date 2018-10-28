package com.example.lucky.reviewbase;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.lucky.reviewbase.fragment.NewsContentFragment;

public class NewsContentActivity extends AppCompatActivity {

    public static void actionStart(Context context,String newTitle,String newContent){
        Intent intent = new Intent(context,NewsContentActivity.class);
        intent.putExtra("new_title",newTitle);
        intent.putExtra("new_content",newContent);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.news_content);

        String newTitle = getIntent().getStringExtra("new_title");
        String newContetn = getIntent().getStringExtra("new_content");
        NewsContentFragment newsContentFragment = (NewsContentFragment) getSupportFragmentManager().findFragmentById(R.id.news_content_fragment);
        newsContentFragment.refresh(newTitle,newContetn);
    }
}
