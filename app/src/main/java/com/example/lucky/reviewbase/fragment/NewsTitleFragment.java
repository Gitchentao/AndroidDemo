package com.example.lucky.reviewbase.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.lucky.reviewbase.NewsContentActivity;
import com.example.lucky.reviewbase.R;
import com.example.lucky.reviewbase.entity.News;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class NewsTitleFragment extends Fragment {

    private boolean isTowPane;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_news_title, container, false);
        LinearLayoutManager manager = new LinearLayoutManager(getActivity());
        RecyclerView recyclerView = view.findViewById(R.id.news_title_recycler);
        recyclerView.setLayoutManager(manager);
        // 加载适配器
        recyclerView.setAdapter(new NewsAdapter(getNews()));
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (getActivity().findViewById(R.id.news_content_fragment) != null){
            isTowPane = true;
        }else {
            isTowPane = false;
        }
    }


    // 初始化新闻内容
    private List<News> getNews(){
        List<News> newsList = new ArrayList<>();
        for (int i = 0;i<20;i++){
            News news = new News();
            news.setTitle("this is title");
            news.setContent(getRandomLengthContent("this is content"));
            newsList.add(news);
        }
        return  newsList;
    }


    // 随机生成内容长度
    private String getRandomLengthContent(String str){
        Random random = new Random();
        int length  = random.nextInt(20)+1;
        StringBuilder builder = new StringBuilder();
        for (int i = 0;i < length;i++){
            builder.append(str);
        }
        return builder.toString();
    }

    class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.ViewHolder>{

        private List<News> newsList;

        public NewsAdapter(List<News> newsList) {
            this.newsList = newsList;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.news_item,parent,false);
            final ViewHolder holder = new ViewHolder(view);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    News news = newsList.get(holder.getAdapterPosition());
                    // 判断是单页还是双页
                    if (isTowPane){
                        NewsContentFragment contentFragment = (NewsContentFragment) getFragmentManager().findFragmentById(R.id.news_content_fragment);
                        contentFragment.refresh(news.getTitle(),news.getContent());
                    }else {
                        NewsContentActivity.actionStart(getActivity(),news.getTitle(),news.getContent());
                    }
                }
            });
            return holder;
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
                News news = newsList.get(position);
                holder.newsTitle.setText(news.getTitle());
        }

        @Override
        public int getItemCount() {
            return newsList.size();
        }

        class ViewHolder extends RecyclerView.ViewHolder {

            TextView newsTitle;
            public ViewHolder(View itemView) {
                super(itemView);

                newsTitle = itemView.findViewById(R.id.news_titles);
            }
        }
    }
}
