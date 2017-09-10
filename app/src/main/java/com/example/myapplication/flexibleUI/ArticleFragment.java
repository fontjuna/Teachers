package com.example.myapplication.flexibleUI;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.myapplication.R;

/**
 * Created by fontjuna on 2017-08-19.
 */

public class ArticleFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_article, container, false);
    }
    public void updateArticleView(int position){
        TextView textView = (TextView) getView().findViewById(R.id.article_text);
        textView.setText(Articles.Articles[position]);
    }
}
