package com.example.zhai.sqlitedemo.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.zhai.sqlitedemo.InitApp;
import com.example.zhai.sqlitedemo.db.table.MediaChannelTable;
import com.example.zhai.sqlitedemo.db.table.NewsChannelTable;
import com.example.zhai.sqlitedemo.db.table.SearchHistoryTable;

/**
 * Created by Mastra on 2017/12/4.
 */

public class DataBaseHelper extends SQLiteOpenHelper {

    private static DataBaseHelper instance;
    private static final String DB_NAME = "Toutiao";
    private static final int DB_VERSION = 1;
    private static final String CLEAR_TABLE_DATA = "delete from ";
    private static SQLiteDatabase mDb;

    public DataBaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public static synchronized DataBaseHelper getInstance() {
        if (instance == null) {
            instance = new DataBaseHelper(InitApp.AppContext, DB_NAME, null, DB_VERSION);
        }
       return instance;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(MediaChannelTable.CREATE_TABLE);
        db.execSQL(NewsChannelTable.CREATE_TABLE);
        db.execSQL(SearchHistoryTable.CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        switch (oldVersion) {
            case 1:
                db.execSQL(MediaChannelTable.CREATE_TABLE);
                break;
            case 2:
                db.execSQL(CLEAR_TABLE_DATA + NewsChannelTable.TABLENAME);
                break;
            case 3:
                ContentValues values = new ContentValues();
                values.put(NewsChannelTable.ID, "");
                values.put(NewsChannelTable.NAME, "推荐");
                values.put(NewsChannelTable.IS_ENABLE, 0);
                values.put(NewsChannelTable.POSITION, 46);
                db.insert(NewsChannelTable.TABLENAME, null, values);
                break;
            case 4:
                db.execSQL(SearchHistoryTable.CREATE_TABLE);
                break;
        }
    }

    public static synchronized SQLiteDatabase getDatabase() {
        if (mDb == null) {
            mDb = getInstance().getWritableDatabase();
        }
        return mDb;
    }

    public static synchronized void closeDatabase() {
        if (mDb != null) {
            mDb.close();
        }
    }
}
