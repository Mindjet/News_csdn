package com.mindjet.com.news_csdn;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ProgressBar;
import android.widget.Toast;


import com.mindjet.com.news_csdn.Adapter.NewsItemAdapter;
import com.mindjet.com.news_csdn.Biz.NewsItemBiz;
import com.mindjet.com.news_csdn.Database.NewsItemDao;
import com.mindjet.com.news_csdn.ItemBean.NewsItem;
import com.mindjet.com.news_csdn.Util.Constraint;
import com.mindjet.com.news_csdn.Util.NetUtil;
import com.mindjet.com.news_csdn.View.XListView;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author Mindjet
 * @date 2016/6/29
 */
@SuppressLint("ValidFragment")
public class MainFragment extends Fragment implements XListView.IXListViewListener {

    private int newsType = Constraint.NEWS_TYPE_YEJIE;  //默认的栏目

    //当前页面
    private int currentPage = 1;

    //处理新闻的类
    private NewsItemBiz biz;

    //扩展的ListView
    private XListView xListView;

    //加载圈
    private ProgressBar progressBar;

    //数据适配器
    private NewsItemAdapter mAdapter;

    //数据源
    private List<NewsItem> mData = new ArrayList<>();

    //数据库工具类
    private NewsItemDao newsItemDao = null;


    private static int LOAD_MORE = 0x110;
    private static int LOAD_REFRESH = 0x111;
    private static int LOAD_FROM_DATABASE = 0x112;

    private static int TIP_ERROR_NO_NETWORK = 0x113;


    public MainFragment(int newsType) {
        this.newsType = newsType;
        biz = new NewsItemBiz();

    }


    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        mAdapter = new NewsItemAdapter(mData, getActivity());
        newsItemDao = new NewsItemDao(getActivity());

        progressBar = (ProgressBar) getView().findViewById(R.id.id_progressBar);
        progressBar.setVisibility(View.VISIBLE);
        /**
         * initial the XlistView
         * ----attaching adapter
         * ----enable pull-load/pull-refresh
         * ----set listener
         */
        xListView = (XListView) getView().findViewById(R.id.id_xlistView);
        xListView.setXListViewListener(this);
        xListView.setAdapter(mAdapter);
        xListView.setPullLoadEnable(true);
        xListView.setPullRefreshEnable(true);

        xListView.startRefresh();

        xListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                mData = mAdapter.getList();
                NewsItem newsItem = mData.get(i-1);
                String url = newsItem.getLink();
                Intent intent = new Intent(getActivity(), DetailActivity.class);
                intent.putExtra("url", url);
                startActivity(intent);

            }
        });

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.tab_item_fragment_main, null);
    }


    @Override
    public void onRefresh() {

        new LoadDataTask().execute(LOAD_REFRESH);

    }

    @Override
    public void onLoadMore() {

        new LoadDataTask().execute(LOAD_MORE);

    }

    class LoadDataTask extends AsyncTask<Integer, Void, Integer> {

        @Override
        protected Integer doInBackground(Integer... params) {

            if (params[0] == LOAD_MORE) {
                return loadMoreData();
            } else if (params[0] == LOAD_REFRESH) {
                return refreshData();
            } else if (params[0] == LOAD_FROM_DATABASE) {
                return LOAD_FROM_DATABASE();
            }
            return -1;
        }

        @Override
        protected void onPostExecute(Integer result) {

            if (result == TIP_ERROR_NO_NETWORK) {
                Toast.makeText(getActivity(), "当前无网络链接", Toast.LENGTH_SHORT).show();
            }
            progressBar.setVisibility(View.GONE);
            mAdapter.notifyDataSetChanged();
            xListView.stopRefresh();
            xListView.stopLoadMore();
            xListView.setRefreshTime("" + new Date(System.currentTimeMillis()));

        }
    }

    private Integer LOAD_FROM_DATABASE() {

        mData = newsItemDao.list(newsType, currentPage);
        mAdapter.setList(mData);
        return null;

    }

    //refresh when pull down
    private Integer refreshData() {

        currentPage = 1;

        if (NetUtil.checkNet(getActivity())) {

            List<NewsItem> newsItems = biz.getNewsItems(newsType, currentPage);
            mAdapter.setList(newsItems);

            newsItemDao.deleteAll(newsType);    //clear database
            newsItemDao.addAll(newsItems);      //add to database

        } else {

            List<NewsItem> newsItems = newsItemDao.list(newsType, currentPage);
            if (newsItems != null) {
                mAdapter.setList(newsItems);
            }
            return TIP_ERROR_NO_NETWORK;
        }

        return -1;
    }

    //load more data when pull up
    private Integer loadMoreData() {

        currentPage += 1;

        if (NetUtil.checkNet(getActivity())) {

            List<NewsItem> newsItems = biz.getNewsItems(newsType, currentPage);
            newsItemDao.addAll(newsItems);
            mAdapter.addAll(newsItems);

        } else {

            List<NewsItem> newsItems = newsItemDao.list(newsType, currentPage);
            mAdapter.addAll(newsItems);

            if (newsItems.size() == 0) {
                currentPage--;
            }

            return TIP_ERROR_NO_NETWORK;

        }

        return -1;

    }


}
