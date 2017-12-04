package com.example.zhai.sqlitedemo.db.dao;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.zhai.sqlitedemo.Constant;
import com.example.zhai.sqlitedemo.InitApp;
import com.example.zhai.sqlitedemo.NewsChannelBean;
import com.example.zhai.sqlitedemo.R;
import com.example.zhai.sqlitedemo.db.DataBaseHelper;
import com.example.zhai.sqlitedemo.db.table.NewsChannelTable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mastra on 2017/12/4.
 *
 * 新闻页面上tab的频道
 */

public class NewsChannelDao {

    private SQLiteDatabase db;

    public NewsChannelDao () {
        this.db = DataBaseHelper.getDatabase();
    }

    // 初始化默认的频道到数据库中
    public void addInitData() {
        String categoryId[] = InitApp.AppContext.getResources().getStringArray(R.array.mobile_news_id);
        String categoryName[] = InitApp.AppContext.getResources().getStringArray(R.array.mobile_news_name);
        for (int i = 0; i < 8; i++) {
            add(categoryId[i], categoryName[i], Constant.NEWS_CHANNEL_ENABLE, i);
        }
        for (int i = 8; i < categoryId.length; i++) {
            add(categoryId[i], categoryName[i], Constant.NEWS_CHANNEL_DISABLE, i);
        }
    }

    public boolean add(String channelId, String channelName, int isEnable, int position) {
        ContentValues values = new ContentValues();
        values.put(NewsChannelTable.ID, channelId);
        values.put(NewsChannelTable.NAME, channelName);
        values.put(NewsChannelTable.IS_ENABLE, isEnable);
        values.put(NewsChannelTable.POSITION, position);
        long result = db.insert(NewsChannelTable.TABLENAME, null, values);
        return result != -1;
    }

    public List<NewsChannelBean> query(int isEnable) {
        Cursor cursor = db.query(NewsChannelTable.TABLENAME, null, NewsChannelTable.IS_ENABLE + "=?", new String[]{isEnable + ""}, null, null, null);
        List<NewsChannelBean> list = new ArrayList<>();
        while (cursor.moveToNext()) {
            NewsChannelBean bean = new NewsChannelBean();
            bean.setChannelId(cursor.getString(NewsChannelTable.ID_ID));
            bean.setChannelName(cursor.getString(NewsChannelTable.ID_NAME));
            bean.setIsEnable(cursor.getInt(NewsChannelTable.ID_ISENABLE));
            bean.setPosition(cursor.getInt(NewsChannelTable.ID_POSITION));
            list.add(bean);
        }
        cursor.close();
        return list;
    }

    public List<NewsChannelBean> queryAll() {
        Cursor cursor = db.query(NewsChannelTable.TABLENAME, null, null, null, null, null, null);
        List<NewsChannelBean> list = new ArrayList<>();
        while (cursor.moveToNext()) {
            NewsChannelBean bean = new NewsChannelBean();
            bean.setChannelId(cursor.getString(NewsChannelTable.ID_ID));
            bean.setChannelName(cursor.getString(NewsChannelTable.ID_NAME));
            bean.setIsEnable(cursor.getInt(NewsChannelTable.ID_ISENABLE));
            bean.setPosition(cursor.getInt(NewsChannelTable.ID_POSITION));
            list.add(bean);
        }
        cursor.close();
        return list;
    }

    public void updateAll(List<NewsChannelBean> list) {
    }

    public boolean removeAll() {
        int result = db.delete(NewsChannelTable.TABLENAME, null, null);
        return result != -1;
    }
}
