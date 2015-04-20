package com.icephone.softwarenewsclient.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.icephone.softwarenewsclient.util.Constant;


/**
 * Created by 温程元 on 13-11-30.
 */

public class DBHelper extends SQLiteOpenHelper {

    private SQLiteDatabase db;
    private ContentValues values = new ContentValues();

    public DBHelper(Context c) {
        //CursorFactory设置为null,使用默认值
        super(c, Constant.DBProperty.DB_NAME, null, 1);
    }

    //数据库第一次被创建时onCreate会被调用
    @Override
    public void onCreate(SQLiteDatabase db) {
        this.db = db;
        this.db.execSQL(Constant.DBProperty.CREATE_TBL);
        //this.db.close();
    }

    public void open() {
        this.db = getWritableDatabase();
    }

    /**
     * 插入
     *
     * @param news NEWS
     */
//    public void insert(News news) {
//        values.put("newId", news.getNewId());
//        values.put("typeId", news.getTypeId());
//        values.put("adminId", news.getAdminId());
//        values.put("newsContent", news.getNewsContent());
//        values.put("newsTitle", news.getNewsTitle());
//        values.put("newsTime", news.getNewsTime());
//        values.put("seeNum", news.getSeeNum());
//        values.put("thumbnail", news.getThumbnail());
//        values.put("subType", news.getSubType());
//        if (db == null) {
//            SQLiteDatabase db = getWritableDatabase();
//            this.db = db;
//        }
//        Log.e("test", String.valueOf(values.size()) + "##############FROM：insert########################");
//        db.insert(Constant.DBProperty.TBL_NAME, null, values);
//        Log.e("test", "已插入");
//        //db.close();
//    }

    public Cursor query(int type) {
        if (db == null) {
            SQLiteDatabase db = getWritableDatabase();
            this.db = db;
        }
        try {
            db.query(Constant.DBProperty.TBL_NAME, null, "typeId=?", new String[]{String.valueOf(type)}, null, null, "newId desc", "0,10");
        } catch (Exception e) {
            e.printStackTrace();
            db = getWritableDatabase();
        }
        Cursor c = db.query(Constant.DBProperty.TBL_NAME, null, "typeId=?", new String[]{String.valueOf(type)}, null, null, "newId desc", "0,10");
        //db.close();
        return c;
    }

    //TODO 未测试
    public void deleteById(int id) {
        if (db == null) {
            SQLiteDatabase db = getWritableDatabase();
            this.db = db;
        }
        try {
            Log.e("TEST", "########!!DELETE:" + id + "!!!!!!!!!!!!!!!!!!!!!############");
            db.execSQL("DELETE FROM news WHERE ID = '" + id + "'");
        } catch (Exception e) {
            e.printStackTrace();
            db = getWritableDatabase();
        }
        db.execSQL("DELETE FROM news WHERE newId = '" + id + "'");
        Log.e("TEST", "########!!DELETE:" + id + "!!!!!!!!!!!!!!!!!!!!!############");
        //db.close();
    }

    /*public Cursor queryAll() {
        // SQLiteDatabase db = getWritableDatabase();
        Cursor c = db.query(Constant.DBProperty.TBL_NAME, null,null,null, null, null, null);
        return c;
    }*/
    public Cursor findNewsById(String NewsId) {
        if (db == null) {
            SQLiteDatabase db = getWritableDatabase();
            this.db = db;
        }
        try {
            db.query(Constant.DBProperty.TBL_NAME, null, "newId=?", new String[]{NewsId}, null, null, null);
        } catch (Exception e) {
            e.printStackTrace();
            db = getWritableDatabase();
        }
        Cursor c = db.query(Constant.DBProperty.TBL_NAME, null, "newId=?", new String[]{NewsId}, null, null, null);
        /*Cursor c = db.rawQuery("SELECT * from "+Constant.DBProperty.TBL_NAME+
                        " WHERE newId =" +NewsId,null);*/
        try {
            c.moveToFirst();
            c.getString(0);
            //db.close();
        } catch (Exception e) {
            Log.e("TEST", "########!!FROM:findNewsById!!!!!!!!!!!!!!!!!!!!!############");
            e.printStackTrace();
            //db.close();
            return null;
        }
        //db.close();
           /* c = db.query(Constant.DBProperty.TBL_NAME, new String[]{"newId", "typeId", "adminId"
                    , "newsContent", "newsTitle", "newsTime", "seeNum"},"newId=?", new String[]{NewsId},null, null, null,null);*/
        return c;
    }

    public Cursor findNewsByIdAndType(String NewsId, int type) {
        if (db == null) {
            SQLiteDatabase db = getWritableDatabase();
            this.db = db;
        }
        try {
            db.query(Constant.DBProperty.TBL_NAME, null, "newId=? and typeId =?", new String[]{NewsId, String.valueOf(type)}, null, null, null);
        } catch (Exception e) {
            e.printStackTrace();
            db = getWritableDatabase();
        }
        Log.e("TEST", "findNewsByIdAndTyp:########!!!!!" + NewsId + "!!!!!!!!!!!!!" + type + "!!!!!!!!!!!!!############");
        Cursor c = db.query(Constant.DBProperty.TBL_NAME, null, "newId=? and typeId =?", new String[]{NewsId, String.valueOf(type)}, null, null, null);
        /*Cursor c = db.rawQuery("SELECT * from "+Constant.DBProperty.TBL_NAME+
                        " WHERE newId =" +NewsId,null);*/
        try {
            c.moveToFirst();
            db.close();
            Log.e("TEST", "findNewsByIdAndTyp:##########################" + c.getString(0) + "##########################################");
        } catch (Exception e) {
            //Log.e("TEST","########!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!############");
            e.printStackTrace();
            //db.close();
            return null;
        }
        //db.close();
           /* c = db.query(Constant.DBProperty.TBL_NAME, new String[]{"newId", "typeId", "adminId"
                    , "newsContent", "newsTitle", "newsTime", "seeNum"},"newId=?", new String[]{NewsId},null, null, null,null);*/
        return c;
    }

    /*public void del(int id) {
        SQLiteDatabase db = getWritableDatabase();
        db.delete(Constant.DBProperty.TBL_NAME, "newId=?", new String[] { String.valueOf(id) });
        db.close();
    }*/
    public void close() {
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