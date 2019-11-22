package com.example.fragmentationdemo;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author Luffy Harris
 * e-mail : 744423651@qq.com
 * phone  : 13002903389
 * date   : 2019/11/18-17:11
 * desc   : 新闻标题的Fragment
 * version: 1.0
 */
public class NewsTitleFragment extends Fragment {
    private static final String TAG = "NewsTitleFragment";
    /**
     * 单页模式和双页模式的标志位，默认是单页模式
     */
    private boolean isTwoPane;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_news_title, container, false);
        RecyclerView newsTitleRecyclerView = view.findViewById(R.id.rv_news_title);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        //可以不设置，recyclerview默认是垂直排列
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        newsTitleRecyclerView.setLayoutManager(linearLayoutManager);
        NewsTitleAdapter newsTitleAdapter = new NewsTitleAdapter(getNews());
        newsTitleRecyclerView.setAdapter(newsTitleAdapter);
        return view;
    }

    private List<News> getNews() {
        List<News> newsList = new ArrayList<>();
        for (int i = 1; i < 36; i++) {
            News news = new News();
            news.setTitle("这是第" + i + "条新闻");
            news.setContent(getRandomLengthContent("这是第" + i + "条新闻的内容"));
            newsList.add(news);
        }
        return newsList;
    }

    private String getRandomLengthContent(String newsContent) {
        Random random = new Random();
        int length = random.nextInt(20) + 1;
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < length; i++) {
            builder.append(newsContent);
        }
        return builder.toString();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (getActivity().findViewById(R.id.tv_news_content) != null) {
            //可以找到tv_news_content 说明是双页模式
            isTwoPane = true;
        } else {//否则为单页模式
            isTwoPane = false;
        }
        Log.e(TAG, "onActivityCreated: isTwoPane == " + isTwoPane);

    }

    class NewsTitleAdapter extends RecyclerView.Adapter<NewsTitleAdapter.NewsTitleViewHolder> {

        private List<News> mNewsList;

        public NewsTitleAdapter(List<News> mNewsList) {
            Log.e(TAG, "NewsTitleAdapter: ");
            this.mNewsList = mNewsList;
        }

        @NonNull
        @Override
        public NewsTitleAdapter.NewsTitleViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, final int viewType) {
            Log.e(TAG, "onCreateViewHolder: viewType 111 == " + viewType);
            View view = LayoutInflater.from(getActivity()).inflate(R.layout.item_news_title, viewGroup, false);
            NewsTitleViewHolder newsTitleViewHolder = new NewsTitleViewHolder(view);
//            view.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    News news = mNewsList.get(postion);
//                    if (isTwoPane) {
//                        //如果是双页模式，则刷新NewsContentFragment中的内容
//                        NewsContentFragment newsContentFragment = (NewsContentFragment) getFragmentManager().findFragmentById(R.id.fragment_news_content);
//                        newsContentFragment.refresh(news.getTitle(), news.getContent());
//                    } else {
//                        //如果是单页模式，启动NewsContentActivity即可
//                        NewsContentActivity.actionStart(getActivity(), news.getTitle(), news.getContent());
//                    }
//                }
//            });
            return newsTitleViewHolder;
        }

        @Override
        public void onBindViewHolder(@NonNull NewsTitleAdapter.NewsTitleViewHolder newsTitleViewHolder, final int postion) {
            News news = mNewsList.get(postion);
            newsTitleViewHolder.newsTitles.setText(news.getTitle());
            newsTitleViewHolder.newsTitles.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    News news = mNewsList.get(postion);
                    if (isTwoPane) {
                        //如果是双页模式，则刷新NewsContentFragment中的内容
                        NewsContentFragment newsContentFragment = (NewsContentFragment) getFragmentManager().findFragmentById(R.id.fragment_news_content);
                        newsContentFragment.refresh(news.getTitle(), news.getContent());
                    } else {
                        //如果是单页模式，启动NewsContentActivity即可
                        NewsContentActivity.actionStart(getActivity(), news.getTitle(), news.getContent());
                    }
                }
            });
        }

        @Override
        public int getItemCount() {
            return mNewsList == null ? 0 : mNewsList.size();
        }

        public class NewsTitleViewHolder extends RecyclerView.ViewHolder {
            TextView newsTitles;

            public NewsTitleViewHolder(@NonNull View itemView) {
                super(itemView);
                newsTitles = itemView.findViewById(R.id.tv_news_title);
            }
        }
    }
}
