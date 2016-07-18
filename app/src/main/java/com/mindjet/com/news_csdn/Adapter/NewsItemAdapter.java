package com.mindjet.com.news_csdn.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.mindjet.com.news_csdn.ItemBean.NewsItem;
import com.mindjet.com.news_csdn.R;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Mindjet
 * @date 2016/6/29
 */
public class NewsItemAdapter extends BaseAdapter {


    private List<NewsItem> list = new ArrayList<>();
    private LayoutInflater mInflater;


    //使用 Universal-Image-Loader
    private ImageLoader imageLoader = ImageLoader.getInstance();
    private DisplayImageOptions options;


    public NewsItemAdapter(List<NewsItem> list, Context context) {
        this.list = list;
        mInflater = LayoutInflater.from(context);

        //初始化 UIL
        imageLoader.init(ImageLoaderConfiguration.createDefault(context));
        options = new DisplayImageOptions.Builder().showStubImage(R.mipmap.ic_launcher).showImageForEmptyUri(R.mipmap
                .ic_launcher).showImageOnFail(R.mipmap.ic_launcher).cacheInMemory().cacheOnDisc().displayer(new
                RoundedBitmapDisplayer(20)).displayer(new FadeInBitmapDisplayer(300)).build();

    }

    public void addAll(List<NewsItem> mData){
        this.list.addAll(mData);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        ViewHolder holder = null;

        if (view == null) {

            view = mInflater.inflate(R.layout.news_item, null);
            holder = new ViewHolder();
            holder.mTitle = (TextView) view.findViewById(R.id.id_title);
            holder.mContent = (TextView) view.findViewById(R.id.id_content);
            holder.mdate = (TextView) view.findViewById(R.id.id_date);
            holder.mImg = (ImageView) view.findViewById(R.id.id_newsImg);

            view.setTag(holder);

        } else {

            holder = (ViewHolder) view.getTag();

        }

        NewsItem item = list.get(i);
        holder.mTitle.setText(item.getTitle());
        holder.mContent.setText(item.getContent());
        holder.mdate.setText(item.getDate());

        if (item.getImgLink() != null) {
            holder.mImg.setVisibility(View.VISIBLE);
            imageLoader.displayImage(item.getImgLink(),holder.mImg,options);
        }else {
            holder.mImg.setVisibility(View.GONE);
        }

        return view;
    }


    class ViewHolder {

        TextView mdate;
        TextView mContent;
        TextView mTitle;
        ImageView mImg;

    }

    public void setList(List<NewsItem> list) {
        this.list = list;
    }

    public List<NewsItem> getList(){return this.list;}

}
