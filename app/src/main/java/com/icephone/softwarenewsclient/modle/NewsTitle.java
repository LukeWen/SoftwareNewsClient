package com.icephone.softwarenewsclient.modle;

import java.util.Date;

/**
 * Created by 温程元 on 13-12-10.
 */
public class NewsTitle {
    /**
     * 新闻id
     */
    private int id;
    /**
     * 新闻标题
     */
    private String title;
    /**
     * 新闻时间
     */
    private Date update_time;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getUpdate_time() {
        return update_time;
    }

    public void setUpdate_time(Date update_time) {
        this.update_time = update_time;
    }
}
