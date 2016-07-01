package com.mindjet.com.news_csdn;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.mindjet.com.news_csdn.Adapter.NewsItemAdapter;
import com.mindjet.com.news_csdn.Biz.NewsItemBiz;
import com.mindjet.com.news_csdn.ItemBean.NewsItem;
import com.mindjet.com.news_csdn.Util.Constraint;
import com.mindjet.com.news_csdn.View.XListView;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author Mindjet
 * @date 2016/6/29
 */
@SuppressLint("ValidFragment")
public class MainFragment extends Fragment implements XListView.IXListViewListener{

    private int newsType = Constraint.NEWS_TYPE_YEJIE;  //默认的栏目

    //当前页面
    private int currentPage = 1;

    //处理新闻的类
    private NewsItemBiz biz;

    //扩展的ListView
    private XListView xListView;

    //数据适配器
    private NewsItemAdapter mAdapter;

    //数据源
    private List<NewsItem> mData = new ArrayList<>();


    public MainFragment(int newsType)
    {
        this.newsType = newsType;
        biz = new NewsItemBiz();
    }


    @Override
    public void onActivityCreated(Bundle savedInstanceState)
    {
        super.onActivityCreated(savedInstanceState);

        mAdapter = new NewsItemAdapter(mData, getActivity());

        /**
         * 初始化
         */
        xListView = (XListView) getView().findViewById(R.id.id_xlistView);
        xListView.setXListViewListener(this);
        xListView.setAdapter(mAdapter);
        xListView.setPullLoadEnable(true);
        xListView.setPullRefreshEnable(true);

        xListView.startRefresh();

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        return inflater.inflate(R.layout.tab_item_fragment_main, null);
    }


    @Override
    public void onRefresh() {

        new LoadDataTask().execute();
        xListView.setRefreshTime(""+new Date(System.currentTimeMillis()));

    }

    @Override
    public void onLoadMore() {

    }

    class LoadDataTask extends AsyncTask<Void, Void, Void>{

        @Override
        protected Void doInBackground(Void... voids) {

            List<NewsItem> newsItemList = biz.getNewsItems(newsType, currentPage);
            mData = newsItemList;
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {

            mAdapter.addAll(mData);
            mAdapter.notifyDataSetChanged();
            xListView.stopRefresh();

        }
    }

}
