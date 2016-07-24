package com.mindjet.com.news_csdn.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.mindjet.com.news_csdn.ItemBean.DetailContent;
import com.mindjet.com.news_csdn.ItemBean.DetailPartType;
import com.mindjet.com.news_csdn.ItemBean.NewsDetailPart;
import com.mindjet.com.news_csdn.R;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;

import java.util.ArrayList;
import java.util.List;

/**
 * Implement an adapter for the XListView of DetailActivity
 *
 * @author Mindjet
 * @date 2016/7/13
 */
public class NewsDetailAdapter extends BaseAdapter {

    private List<DetailContent> contents = new ArrayList<>();
    private LayoutInflater inflater;
    private ImageLoader imageLoader = ImageLoader.getInstance();
    private DisplayImageOptions options;

    public NewsDetailAdapter(Context context) {
        this.inflater = LayoutInflater.from(context);

        imageLoader.init(ImageLoaderConfiguration.createDefault(context));
        options = new DisplayImageOptions.Builder().showStubImage(R.mipmap.ic_launcher).showImageForEmptyUri(R.mipmap
                .ic_launcher).showImageOnFail(R.mipmap.ic_launcher).cacheInMemory().cacheOnDisc().displayer(new
                RoundedBitmapDisplayer(20)).displayer(new FadeInBitmapDisplayer(300)).build();

    }

    public void addParts(List<DetailContent> contents) {
        this.contents.addAll(contents);
    }

    @Override
    public int getCount() {
        return contents.size();
    }

    @Override
    public Object getItem(int i) {
        return contents.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }


    /*
     * As there are several types of NewsDetailPart,
     * we use switch structure to find corresponding layout according to the type.
     */
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        DetailContent part = contents.get(i);

        ViewHolder holder;

        if (view == null) {

            holder = new ViewHolder();
            switch (part.getType()) {

                case DetailPartType.TITLE:
                    view = inflater.inflate(R.layout.detail_part_title, null);
                    holder.mTextView = (TextView) view.findViewById(R.id.part_title);
                    break;
                case DetailPartType.BRIEF:
                    view = inflater.inflate(R.layout.detail_part_brief, null);
                    holder.mTextView = (TextView) view.findViewById(R.id.part_brief);
                    break;
                case DetailPartType.CONTENT:
                    view = inflater.inflate(R.layout.detail_part_content, null);
                    holder.mTextView = (TextView) view.findViewById(R.id.part_content);
                    break;
                case DetailPartType.IMAGELINK:
                    view = inflater.inflate(R.layout.detail_part_image, null);
                    holder.mImageView = (ImageView) view.findViewById(R.id.part_image);
                    break;
                case DetailPartType.BOLD:
                    view = inflater.inflate(R.layout.detai_part_bold, null);
                    holder.mTextView = (TextView) view.findViewById(R.id.part_bold);
                    break;

            }
            view.setTag(holder);

        } else {
            holder = (ViewHolder) view.getTag();
        }

        if (part != null) {

            switch (part.getType()) {

                case NewsDetailPart.PartType.TITLE:
                    holder.mTextView.setText(part.getContent());
                    break;

                case NewsDetailPart.PartType.BRIEF:
                    holder.mTextView.setText(part.getContent());
                    break;

                case NewsDetailPart.PartType.CONTENT:
                    holder.mTextView.setText(part.getContent());
                    break;

                case NewsDetailPart.PartType.IMAGELINK:
                    imageLoader.displayImage(part.getContent(), holder.mImageView, options);
                    break;

                case NewsDetailPart.PartType.BOLD:
                    holder.mTextView.setText(part.getContent());
                    break;
            }

        }


        return view;

    }

    class ViewHolder {

        TextView mTextView;
        ImageView mImageView;

    }
}
