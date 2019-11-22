package com.example.fragmentationdemo;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;

/**
 * author : Luffy Harris
 * e-mail : 744423651@qq.com
 * phone  : 13002903389
 * date   : 2019/11/18-16:54
 * desc   : 新闻的活动
 * version: 1.0
 */
public class NewsContentActivity extends FragmentActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_content);
        Intent intent = getIntent();
        if (null != intent) {
            String newsTitle = intent.getStringExtra("news_title");
            String newsContent = intent.getStringExtra("news_content");

            NewsContentFragment newsContentFragment = (NewsContentFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_news_content);
            newsContentFragment.refresh(newsTitle, newsContent);//刷新NewsContentFragment界面

        }

    }

    public static void actionStart(Context context, String newsTitle, String newsContent) {
        Intent intent = new Intent(context, NewsContentActivity.class);
        intent.putExtra("news_title", newsTitle);
        intent.putExtra("news_content", newsContent);
        context.startActivity(intent);
    }
}
