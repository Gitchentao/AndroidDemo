package com.example.lucky.reviewbase.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.lucky.reviewbase.R;

public class NewsContentFragment extends Fragment {


    private View view;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_news_content, container, false);
        return view;
    }

    public void refresh(String title,String content){

        LinearLayout nweLayout = view.findViewById(R.id.news_layout);
        nweLayout.setVisibility(View.VISIBLE);
        TextView newTitle = view.findViewById(R.id.news_title);
        TextView newContent = view.findViewById(R.id.news_content);
        newTitle.setText(title);
        newContent.setText(content);
    }
}
