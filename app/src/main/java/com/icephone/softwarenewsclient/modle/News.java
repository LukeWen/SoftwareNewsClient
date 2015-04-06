package com.icephone.softwarenewsclient.modle;

import java.io.Serializable;

/**
 *
 * @author shen
 * news表，学院新闻表
 */
public class News implements Serializable{
    private static final long serialVersionUID = 1;
    /**
     * 物理主键
     */
    private int newId;
    /**
     * 类型 ：
     * 0 最新新闻
     * 3 热点新闻
     * 4 通知公告
     * 5 非新闻类
     */
    private int typeId;
    /**
     * 作者，默认全部为0
     */
    private int adminId;
    /**
     * 新闻标题
     */
    private String newsTitle;
    /**
     * 新闻内容
     */
    private String newsContent;
    /**
     * 新闻时间
     */
    private String newsTime;
    /**
     * 访问次数
     */
    private int seeNum;
    /**
     *
     */
    private String thumbnail;
    /**
     * 细节类型 ：
     * 0 普通新闻
     * 14 奖助学金公示
     * 17 招生简章
     * 22 通知，公示
     * 23 俱乐部招聘
     * 25 招聘
     * 5 教师信息
     */
    private int subType;
    //private List<>
    public int getNewId() {
        return newId;
    }
    public void setNewId(int newId) {
        this.newId = newId;
    }
    public int getTypeId() {
        return typeId;
    }
    public void setTypeId(int typeId) {
        this.typeId = typeId;
    }
    public int getAdminId() {
        return adminId;
    }
    public void setAdminId(int adminId) {
        this.adminId = adminId;
    }
    public String getNewsContent() {
        return newsContent;
    }
    public void setNewsContent(String newsContent) {
        this.newsContent = newsContent;
    }
    public String getNewsTitle() {
        return newsTitle;
    }
    public void setNewsTitle(String newsTitle) {
        this.newsTitle = newsTitle;
    }
    public String getNewsTime() {
        return newsTime;
    }
    public void setNewsTime(String newsTime) {
        this.newsTime = newsTime;
    }
    public int getSeeNum() {
        return seeNum;
    }
    public void setSeeNum(int seeNum) {
        this.seeNum = seeNum;
    }
    public String getThumbnail() {
        return thumbnail;
    }
    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }
    public int getSubType() {
        return subType;
    }
    public void setSubType(int subType) {
        this.subType = subType;
    }
}
