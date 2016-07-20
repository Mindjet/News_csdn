package com.mindjet.com.news_csdn;

import android.annotation.TargetApi;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ProgressBar;

import com.mindjet.com.news_csdn.Adapter.NewsDetailAdapter;
import com.mindjet.com.news_csdn.ItemBean.NewsDetailPart;
import com.mindjet.com.news_csdn.Util.DecodeUtil;
import com.mindjet.com.news_csdn.View.XListView;

import java.util.ArrayList;
import java.util.List;

/**
 *  Show the detail of specific news, implemented by XListView
 *
 * @author Mindjet
 * @date 2016/7/13
 */
public class DetailActivity extends FragmentActivity {

    private XListView xListView;
    private ProgressBar progressBar;
    private String url;
    private List<NewsDetailPart> parts = new ArrayList<>();
    private NewsDetailAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_detail);

        immersiveMode();

        final Intent intent = getIntent();
        Bundle extra = intent.getExtras();
        url = extra.getString("url");

        progressBar = (ProgressBar)findViewById(R.id.detail_progressBar);
        progressBar.setVisibility(View.VISIBLE);

        xListView = (XListView) findViewById(R.id.detaill_xlistview);
        xListView.setPullRefreshEnable(false);
        xListView.setPullLoadEnable(false);
        adapter = new NewsDetailAdapter(this);
        xListView.setAdapter(adapter);

        //start fetching data
        new MyAsyncTask().execute();

    }

    @TargetApi(Build.VERSION_CODES.KITKAT)
    private void immersiveMode() {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {

            Window window = getWindow();
            window.setFlags(
                    WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS,
                    WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION
            );

        }

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

    }

    //decode html and update the adapter data to setText or setImage.
    class MyAsyncTask extends AsyncTask<Void, Void, Void>{

        @Override
        protected Void doInBackground(Void... voids) {

            try {

                parts = DecodeUtil.getNews(url).getList();

            }catch (Exception e){

//                Looper.prepare();
//                Toast.makeText(DetailActivity.this, e.getMessage(),Toast.LENGTH_SHORT).show();
//                Looper.loop();
                e.printStackTrace();

            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {

            if (parts.size()!=0){

                adapter.addParts(parts);
                adapter.notifyDataSetChanged();
                progressBar.setVisibility(View.GONE);

            }

        }
    }


}
