package com.icephone.softwarenewsclient.db;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.icephone.softwarenewsclient.modle.News;
import com.icephone.softwarenewsclient.util.Constant;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 温程元 on 13-12-3.
 */
public class DBManager {
    private DBHelper helper;
    private SQLiteDatabase db;

    public DBManager(Context context) {
        helper = new DBHelper(context);
        //因为getWritableDatabase内部调用了mContext.openOrCreateDatabase(mName, 0, mFactory);
        //所以要确保context已初始化,我们可以把实例化DBManager的步骤放在Activity的onCreate里
        db = helper.getWritableDatabase();
    }

    public void addNews(News news) {
        SQLiteDatabase db = helper.getWritableDatabase();
        if (db != null) {
            db.execSQL("INSERT INTO news VALUES( ?, ?, ?, ?, ?, ?, ?, ?, ?)", new Object[]{
                    news.getNewId(), news.getTypeId(), news.getAdminId(), news.getNewsTitle(),
                    news.getNewsContent(), news.getNewsTime(), news.getSeeNum(), news.getThumbnail(), news.getSubType()});
        }
        //db.close();
    }

    public void delNews(int id) {
        if (db == null)
            db = helper.getWritableDatabase();
        db.delete(Constant.DBProperty.TBL_NAME, "newId=?", new String[]{String.valueOf(id)});
    }

    /**
     * add newses
     *
     * @param newses
     */
    public void addNewses(List<News> newses) {
        if (db != null) {
            db.beginTransaction();    //开始事务
        } else return;
        try {
            for (News news : newses) {
                db.execSQL("INSERT INTO news VALUES( ?, ?, ?, ?, ?, ?, ?, ?, ?)", new Object[]{
                        news.getNewId(), news.getTypeId(), news.getAdminId(), news.getNewsTitle(),
                        news.getNewsContent(), news.getNewsTime(), news.getSeeNum(), news.getThumbnail(), news.getSubType()});
            }
            db.setTransactionSuccessful();    //设置事务成功完成
        } finally {
            db.endTransaction();    //结束事务
        }
    }

    /**
     * update person's age
     * @param person
     */
    /*public void updateAge(Person person) {
        ContentValues cv = new ContentValues();
        cv.put("age", person.age);
        db.update("person", cv, "name = ?", new String[]{person.name});
    }*/

    /**
     * delete old person
     * @param person
     */
    /*public void deleteOldPerson(Person person) {
        db.delete("person", "age >= ?", new String[]{String.valueOf(person.age)});
    }*/

    /**
     * query all persons, return list
     *
     * @return List<Person>
     */
    public List<News> query() {
        /*SQLiteDatabase db = helper.getWritableDatabase();
        Cursor c = db.query(Constant.DBProperty.TBL_NAME, null, null, null, null, null, null);
        return c;*/
        ArrayList<News> newses = new ArrayList<News>();
        Cursor c = queryTheCursor();
        while (c.moveToNext()) {
            News news = new News();
            news.setTypeId(c.getInt(c.getColumnIndex("typeId")));
            news.setAdminId(c.getInt(c.getColumnIndex("adminId")));
            news.setNewsTitle(c.getString(c.getColumnIndex("newsTitle")));
            news.setNewsContent(c.getString(c.getColumnIndex("newsContent")));
            news.setNewsTime(c.getString(c.getColumnIndex("newsTime")));
            news.setSeeNum(c.getInt(c.getColumnIndex("seeNum")));
            news.setThumbnail(c.getString(c.getColumnIndex("thumbnail")));
            news.setNewId(c.getInt(c.getColumnIndex("newId")));
            news.setSubType(c.getInt(c.getColumnIndex("subType")));
            newses.add(news);
        }
        c.close();
        return newses;
    }

    /**
     * query all persons, return cursor
     *
     * @return Cursor
     */
    public Cursor queryTheCursor() {
        Cursor c = db.rawQuery("SELECT * FROM news", null);
        return c;
    }

    /**
     * close database
     */
    public void closeDB() {
        db.close();
    }
}
