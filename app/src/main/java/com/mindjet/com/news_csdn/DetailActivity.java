package com.mindjet.com.news_csdn;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;

import com.mindjet.com.news_csdn.R;
import com.mindjet.com.news_csdn.View.XListView;

/**
 * @author Mindjet
 * @date 2016/7/13
 */
public class DetailActivity extends Activity {


    private XListView xListView;
    private ProgressBar progressBar;
    private String url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_detail);

        Intent intent = getIntent();
        Bundle extra = intent.getExtras();
        url = extra.getString("url");


        xListView = (XListView) findViewById(R.id.detaill_xlistview);
        xListView.setPullRefreshEnable(false);
        xListView.setPullLoadEnable(false);

        progressBar = (ProgressBar)findViewById(R.id.detail_progressBar);
        progressBar.setVisibility(View.VISIBLE);

        // TODO: 2016/7/13 adapter for xlistview is still not ready, asynctask is not implemented


    }


    class MyAsyncTask extends AsyncTask<Void, Void, Void>{

        @Override
        protected Void doInBackground(Void... voids) {



            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
        }
    }


}
