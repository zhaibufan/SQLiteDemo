package com.example.zhai.sqlitedemo.db.dao;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.zhai.sqlitedemo.db.DataBaseHelper;
import com.example.zhai.sqlitedemo.db.table.SearchHistoryTable;


/**
 * Created by Meiji on 2017/6/17.
 */

public class SearchHistoryDao {

    private SQLiteDatabase db;

    public SearchHistoryDao() {
        this.db = DataBaseHelper.getDatabase();
    }

    public boolean add(String keyWord) {
        ContentValues values = new ContentValues();
        values.put(SearchHistoryTable.KEYWORD, keyWord);
//        values.put(SearchHistoryTable.TIME, TimeUtil.getCurrentTimeStamp());
        long result = db.insert(SearchHistoryTable.TABLENAME, null, values);
        return result != -1;
    }


    public boolean queryisExist(String keyWord) {
        Cursor cursor = db.query(SearchHistoryTable.TABLENAME, null, SearchHistoryTable.KEYWORD + "=?", new String[]{keyWord}, null, null, null);
        if (cursor.moveToNext()) {
            cursor.close();
            return true;
        }
        cursor.close();
        return false;
    }

    public boolean delete(String keyWord) {
        int id = db.delete(SearchHistoryTable.TABLENAME, SearchHistoryTable.KEYWORD + "=?", new String[]{keyWord});
        return id != -1;
    }

    public boolean update(String keyWord) {
        ContentValues values = new ContentValues();
        values.put(SearchHistoryTable.KEYWORD, keyWord);
//        values.put(SearchHistoryTable.TIME, TimeUtil.getCurrentTimeStamp());
        int result = db.update(SearchHistoryTable.TABLENAME, values, SearchHistoryTable.KEYWORD + "=?", new String[]{keyWord});
        return result != -1;
    }

    public boolean deleteAll() {
        int id = db.delete(SearchHistoryTable.TABLENAME, null, null);
        return id != -1;
    }
}
