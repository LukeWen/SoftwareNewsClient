package com.icephone.softwarenewsclient.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.icephone.softwarenewsclient.modle.News;
import com.icephone.softwarenewsclient.util.Constant;

import java.util.List;


/**
 * SoftwareNewsClient
 * Created by 温程元 on 13-11-30.
 */

public class DBHelper extends SQLiteOpenHelper {

    private ContentValues values = new ContentValues();

    public DBHelper(Context c) {
        //CursorFactory设置为null,使用默认值
        super(c, Constant.DBProperty.DB_NAME, null, 1);
    }

    //数据库第一次被创建时onCreate会被调用
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(Constant.DBProperty.CREATE_TBL);
    }


    public void insertDetail(News news) {
        values.put("id", news.getId());
        values.put("category_id", news.getCategory_id());
        values.put("title", news.getTitle());
        values.put("article", news.getArticle());
        values.put("update_time", news.getUpdate_time().toString());
        values.put("page_view", news.getPage_view());
        values.put("supervisor_id", news.getSupervisor_id());
        values.put("alias", news.getAlias());
        SQLiteDatabase db = getWritableDatabase();
        db.replace(Constant.DBProperty.TBL_NAME, null, values);
        close(db);
    }

    public void insertTitle(News news) {
        values.put("id", news.getId());
        values.put("category_id", news.getCategory_id());
        values.put("title", news.getTitle());
        values.put("update_time", news.getUpdate_time().toString());
        SQLiteDatabase db = getWritableDatabase();
        db.replace(Constant.DBProperty.TBL_NAME, null, values);
        close(db);
    }

    public void insertOutline(News news) {
        values.put("id", news.getId());
        values.put("outline_id", news.getOutline_id());
        values.put("category_id", news.getCategory_id());
        values.put("title", news.getTitle());
        values.put("article", news.getArticle());
        values.put("update_time", news.getUpdate_time().toString());
        values.put("page_view", news.getPage_view());
        values.put("supervisor_id", news.getSupervisor_id());
        values.put("alias", news.getAlias());
        SQLiteDatabase db = getWritableDatabase();
        db.replace(Constant.DBProperty.TBL_NAME, null, values);
        close(db);
    }

    public void update(News news) {
        values.put("id", news.getId());
        values.put("outline_id", news.getOutline_id());
        values.put("category_id", news.getCategory_id());
        values.put("title", news.getTitle());
        values.put("article", news.getArticle());
        values.put("update_time", news.getUpdate_time().toString());
        values.put("page_view", news.getPage_view());
        values.put("supervisor_id", news.getSupervisor_id());
        values.put("alias", news.getAlias());
        SQLiteDatabase db = getWritableDatabase();
        db.update(Constant.DBProperty.TBL_NAME, values, "id=?", new String[]{String.valueOf(news.getId())});
        close(db);
    }

    public void insertListDetail(List newsList) {
        int size = newsList.size();
        for (int i = 0; i < size; i++) {
            insertDetail((News) newsList.get(i));
        }
    }

    public Cursor queryByCategory(int category) {
        SQLiteDatabase db = getWritableDatabase();
        Cursor c = null;
        try {
            c = db.query(Constant.DBProperty.TBL_NAME, null, "category_id=?",
                    new String[]{String.valueOf(category)}, null, null, "id desc", "0,10");
            close(db);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return c;
    }

    public Cursor queryByOutline(int outlineId) {
        SQLiteDatabase db = getWritableDatabase();
        Cursor c = null;
        try {
            c = db.query(Constant.DBProperty.TBL_NAME, null, "outline_id=?",
                    new String[]{String.valueOf(outlineId)}, null, null, "id desc", "0,10");
            close(db);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return c;
    }

    public Cursor findNewsById(int id) {
        SQLiteDatabase db = getWritableDatabase();
        Cursor c = null;
        try {
            c = db.query(Constant.DBProperty.TBL_NAME, null, "id=?",
                    new String[]{String.valueOf(id)}, null, null, null);
            close(db);
        } catch (Exception e) {
            e.printStackTrace();
            close(db);
        }
        /*Cursor c = db.rawQuery("SELECT * from "+Constant.DBProperty.TBL_NAME+
                        " WHERE id =" + id,null);*/
        if (c != null) {
            c.moveToFirst();
            c.getString(0);
        }
        return c;
    }

    public Cursor findNewsByIdAndType(int id, int type) {
        SQLiteDatabase db = getWritableDatabase();
        Cursor c = null;
        try {
            c = db.query(Constant.DBProperty.TBL_NAME, null, "id = ? and category_id =?",
                    new String[]{String.valueOf(id), String.valueOf(type)}, null, null, null);
            close(db);
        } catch (Exception e) {
            e.printStackTrace();
            close(db);
        }
        if (c != null) {
            c.moveToFirst();
        }
        return c;
    }

    public void close(SQLiteDatabase db) {
        if (db != null)
            try {
                db.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
    }

    //如果DATABASE_VERSION值被更改,系统发现现有数据库版本不同,即会调用onUpgrade
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }
}