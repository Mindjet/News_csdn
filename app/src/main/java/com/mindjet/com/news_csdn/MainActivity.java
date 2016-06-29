package com.mindjet.com.news_csdn;

import android.os.Handler;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.os.Bundle;

import com.mindjet.com.news_csdn.Bean.CommonException;
import com.mindjet.com.news_csdn.Bean.NewsItem;
import com.mindjet.com.news_csdn.Utils.Constraint;
import com.viewpagerindicator.TabPageIndicator;

import java.util.List;

public class MainActivity extends FragmentActivity {

    private TabPageIndicator mIndicator;
    private ViewPager mViewPager;
    private TabAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mIndicator = (TabPageIndicator) findViewById(R.id.id_indicator);
        mViewPager = (ViewPager) findViewById(R.id.id_viewpager);

        mAdapter = new TabAdapter(getSupportFragmentManager());
        mViewPager.setAdapter(mAdapter);
        mIndicator.setViewPager(mViewPager, 0);

    }
}
