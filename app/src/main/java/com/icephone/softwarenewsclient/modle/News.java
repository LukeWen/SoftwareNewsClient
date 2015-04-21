package com.icephone.softwarenewsclient.modle;

import java.util.Date;

/**
 * @author shen
 *         news表，学院新闻表
 */
public class News {
    /**
     * 新闻id
     */
    private int id;
    /**
     * 小类别id
     */
    private int category_id;
    /**
     * 大类别id
     */
    private int outline_id;
    /**
     * 新闻标题
     */
    private String title;
    /**
     * 新闻内容
     */
    private String article;
    /**
     * 新闻时间
     */
    private Date update_time;
    /**
     * 新闻浏览量
     */
    private int page_view;
    /**
     * 管理员id
     */
    private int supervisor_id;
    /**
     * 管理者别名
     */
    private String alias;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCategory_id() {
        return category_id;
    }

    public void setCategory_id(int category_id) {
        this.category_id = category_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getArticle() {
        return article;
    }

    public void setArticle(String article) {
        this.article = article;
    }

    public Date getUpdate_time() {
        return update_time;
    }

    public void setUpdate_time(Date update_time) {
        this.update_time = update_time;
    }

    public int getPage_view() {
        return page_view;
    }

    public void setPage_view(int page_view) {
        this.page_view = page_view;
    }

    public int getSupervisor_id() {
        return supervisor_id;
    }

    public void setSupervisor_id(int supervisor_id) {
        this.supervisor_id = supervisor_id;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public int getOutline_id() {
        return outline_id;
    }

    public void setOutline_id(int outline_id) {
        this.outline_id = outline_id;
    }
}
