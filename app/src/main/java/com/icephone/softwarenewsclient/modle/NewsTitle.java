package com.icephone.softwarenewsclient.modle;

/**
 * Created by 温程元 on 13-12-10.
 */
public class NewsTitle {
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
}
