package com.mindjet.com.news_csdn.ItemBean;

/**
 * @author Mindjet
 * @date 2016/6/30
 */
public class NewsItem {

    private int id;
    private String title;
    private String link;
    private String date;
    private String imgLink;
    private String content;
    private int newsType;

    public NewsItem() {
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLink() {
        return this.link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getDate() {
        return this.date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getImgLink() {
        return this.imgLink;
    }

    public void setImgLink(String imgLink) {
        this.imgLink = imgLink;
    }

    public String getContent() {
        return this.content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getNewsType() {
        return this.newsType;
    }

    public void setNewsType(int newsType) {
        this.newsType = newsType;
    }

    public String toString() {
        return "NewsItem [id=" + this.id + ", title=" + this.title + ", link=" + this.link + ", date=" + this.date + ", imgLink=" + this.imgLink + ", content=" + this.content + ", newsType=" + this.newsType + "]";
    }

}
