package com.mindjet.com.news_csdn.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.mindjet.com.news_csdn.R;
import com.mindjet.test.NewsDetailPart;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Mindjet
 * @date 2016/7/13
 */
public class NewsDetailAdapter extends BaseAdapter {


    private List<NewsDetailPart> parts = new ArrayList<>();
    private LayoutInflater inflater;

    public NewsDetailAdapter(List<NewsDetailPart> parts, Context context) {
        this.parts = parts;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return parts.size();
    }

    @Override
    public Object getItem(int i) {
        return parts.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        NewsDetailPart part = parts.get(i);

        ViewHolder holder = null;

        if (view == null){

            holder = new ViewHolder();
            switch (part.getType()){


                case NewsDetailPart.PartType.TITLE:
                    view = inflater.inflate(R.layout.detail_part_title, null);
                    holder.mTextView = (TextView) view.findViewById(R.id.part_title);
                    break;
                case NewsDetailPart.PartType.BRIEF:
                    view = inflater.inflate(R.layout.detail_part_brief, null);
                    holder.mTextView = (TextView) view.findViewById(R.id.part_brief);
                    break;
                case NewsDetailPart.PartType.CONTENT:
                    view = inflater.inflate(R.layout.detail_part_content, null);
                    holder.mTextView = (TextView) view.findViewById(R.id.part_content);
                    break;

                // TODO: 2016/7/13 from here to restart
                
            }

        }





        return view;
    }

    class ViewHolder{

        TextView mTextView;
        ImageView mImageView;

    }
}
