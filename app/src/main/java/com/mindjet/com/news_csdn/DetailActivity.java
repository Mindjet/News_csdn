package com.mindjet.com.news_csdn;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Looper;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ProgressBar;
import android.widget.Toast;

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
public class DetailActivity extends Activity {


    private XListView xListView;
    private ProgressBar progressBar;
    private String url;
    private List<NewsDetailPart> parts = new ArrayList<>();
    NewsDetailAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_detail);

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

//        xListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//
//                NewsDetailPart part = parts.get(i-1);
//                String imageLink = part.getImageLink();
//
//                if (imageLink!=null){
//
//                    Intent intent = new Intent(DetailActivity.this, ImageShowActivity.class);
//                    intent.putExtra("imageLink", imageLink);
//                    startActivity(intent);
//
//                }
//            }
//        });

        //start fetching data
        new MyAsyncTask().execute();

    }

    //decode html and update the adapter data to setText or setImage.
    class MyAsyncTask extends AsyncTask<Void, Void, Void>{

        @Override
        protected Void doInBackground(Void... voids) {

            try {

                parts = DecodeUtil.getNews(url).getList();

            }catch (Exception e){

                Looper.prepare();
                Toast.makeText(DetailActivity.this, e.getMessage(),Toast.LENGTH_SHORT).show();
                Looper.loop();

            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {

            if (parts!=null){

                adapter.addParts(parts);
                adapter.notifyDataSetChanged();
                progressBar.setVisibility(View.GONE);

            }

        }
    }


}
