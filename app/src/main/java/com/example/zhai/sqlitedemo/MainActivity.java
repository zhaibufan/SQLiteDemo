package com.example.zhai.sqlitedemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.zhai.sqlitedemo.db.dao.NewsChannelDao;

import java.util.List;

/**
 * demo是对数据库的简单封装，共由三部分组成
 *
 *      1.DataBaseHelp：继承SQLiteOpenHelper，创建数据库和表，并对外暴露获取SQliteDatabase的方法
 *      2.table：每一张表对应一个table类，该类中有表名，字段名，字段名编号以及创建表的sql语句
 *      3.dao:对应每一张表的增删改查方法
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        NewsChannelDao dao = new NewsChannelDao();
        dao.addInitData();
        List<NewsChannelBean> newsChannelBeen = dao.queryAll();
        for (NewsChannelBean bean : newsChannelBeen ) {
            System.out.println("bean="+bean.toString());
        }
    }
}
