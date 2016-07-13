package com.mindjet.com.news_csdn.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * @author Mindjet
 * @date 2016/7/1
 */
public class DBHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "csdn_app_demo";

    public DBHelper(Context context) {

        super(context, DB_NAME, null, 1);
//        SQLiteDatabase db = this.getWritableDatabase();

    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        /**
         * id, title, link, date, imgLink, content, newstype
         */

        String sql = "create table tb_newsItem(_id integer primary key autoincrement , "
                + " title text , link text , date text , imgLink text , content text , newstype integer);";

        System.out.println("create db----------------");

        sqLiteDatabase.execSQL(sql);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {


    }
}
