package com.example.fragmentationdemo;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * author : Luffy Harris
 * e-mail : 744423651@qq.com
 * phone  : 13002903389
 * date   : 2019/11/18-16:37
 * desc   : 展示新闻的Fragment
 * version: 1.0
 */
public class NewsContentFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_news_content, container, false);
    }

    public void refresh(String newsTitle, String newsContent) {
        View view = getView();
        LinearLayout llNews = view.findViewById(R.id.ll_news);
        llNews.setVisibility(View.VISIBLE);
        TextView tvNewsTitle = view.findViewById(R.id.tv_news_title);
        TextView tvNewsContent = view.findViewById(R.id.tv_news_content);
        tvNewsTitle.setText(newsTitle);//刷新新闻标题
        tvNewsContent.setText(newsContent);//刷新新闻内容


    }
}
