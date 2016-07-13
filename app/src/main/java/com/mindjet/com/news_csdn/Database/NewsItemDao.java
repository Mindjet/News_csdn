package com.mindjet.com.news_csdn.Database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.mindjet.com.news_csdn.Database.DBHelper;
import com.mindjet.com.news_csdn.ItemBean.NewsItem;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Mindjet
 * @date 2016/7/1
 */
public class NewsItemDao {

    private DBHelper dbHelper;

    public NewsItemDao(Context context) {

        dbHelper = new DBHelper(context);
    }



    /**
     * 添加单条数据
     * @param newsItem
     */
    public void add(NewsItem newsItem) {

        String sql = "insert into tb_newsItem (title,link,date,imgLink,content,newstype) values(?,?,?,?,?,?);";
        SQLiteDatabase database = dbHelper.getWritableDatabase();
        database.execSQL(sql, new Object[]{newsItem.getTitle(), newsItem.getLink(), newsItem.getDate(), newsItem
                .getImgLink(),newsItem.getContent(),newsItem.getNewsType()});
        database.close();
    }


    /**
     * 删除 对应 newsType 的所有数据
     * @param newsType
     */

    public void deleteAll(int newsType){

        String sql = "delete from tb_newsItem where newstype = ?";
        SQLiteDatabase database = dbHelper.getWritableDatabase();
        database.execSQL(sql,new Object[]{newsType});
        database.close();

    }

    /**
     * 添加一组数据
     * @param newsItemList 要到数据库添加的数据组
     */

    public void addAll(List<NewsItem> newsItemList){

        for (NewsItem newsItem : newsItemList){

            add(newsItem);

        }

    }

    /**
     * 根据 newsType 和 currentPage 从数据库读取数据
     * @param newsType 栏目类型
     * @param currentPage   页码
     * @return  返回数据组
     */

    public List<NewsItem> list(int newsType, int currentPage){

        List<NewsItem> list = new ArrayList<>();

        //因为 xlistView 一次刷新出现十条数据
        int offset = 10*(currentPage-1);

        String sql = "select title,link,date,imgLink,content,newstype from tb_newsItem where newstype = ? limit ?,? ";
        
        SQLiteDatabase database = dbHelper.getWritableDatabase();

        String start = offset+"";   //取数据的起点
        String num = 10+"";     //每次取十条

        Cursor cursor = database.rawQuery(sql, new String[]{ ""+newsType, start, num});

        NewsItem item = null;

        while (cursor.moveToNext()){

            item = new NewsItem();

            item.setTitle(cursor.getString(0));
            item.setLink(cursor.getString(1));
            item.setDate(cursor.getString(2));
            item.setImgLink(cursor.getString(3));
            item.setContent(cursor.getString(4));
            item.setNewsType(cursor.getInt(5));

            list.add(item);

        }

        cursor.close();
        database.close();
        return list;
    }

}
