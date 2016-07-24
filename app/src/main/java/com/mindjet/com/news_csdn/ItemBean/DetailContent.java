package com.mindjet.com.news_csdn.ItemBean;

/**
 * @author Mindjet
 * @date 2016/7/22
 */
public class DetailContent {

    //content can store title, brief, image link, content.
    private String content;
    private int type;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "[ content = "+content+", type = "+type+" ]";
    }
}
