package com.mindjet.com.news_csdn.Biz;

import com.mindjet.com.news_csdn.Util.DataUtil;
import com.mindjet.com.news_csdn.ItemBean.NewsItem;
import com.mindjet.com.news_csdn.Util.UrlUtil;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Mindjet
 * @date 2016/6/30
 */
public class NewsItemBiz {

    public NewsItemBiz() {
    }

    public List<NewsItem> getNewsItems(int newsType, int currentPage) {
        ArrayList newsItemList = new ArrayList();
        String urlStr = UrlUtil.generateUrl(newsType, currentPage);
        String htmlStr = DataUtil.doGet(urlStr);
        Document doc = Jsoup.parse(htmlStr);
        Elements units = doc.getElementsByClass("unit");

        for(int i = 0; i < units.size(); ++i) {
            NewsItem item = new NewsItem();
            Element unit_ele = (Element)units.get(i);
            Element h1_ele = (Element)unit_ele.getElementsByTag("h1").get(0);
            Element h1_a_ele = h1_ele.child(0);
            String title = h1_a_ele.text();
            String href = h1_a_ele.attr("href");
            item.setTitle(title);
            item.setLink(href);
            Element h4_ele = (Element)unit_ele.getElementsByTag("h4").get(0);
            Element h4_ago_ele = (Element)h4_ele.getElementsByClass("ago").get(0);
            String date = h4_ago_ele.text();
            item.setDate(date);
            Element dl_ele = (Element)unit_ele.getElementsByTag("dl").get(0);
            Element dl_dt_ele = dl_ele.child(0);

            Element content_ele;
            String content;
            try {
                content_ele = dl_dt_ele.child(0);
                content = content_ele.child(0).attr("src");
                item.setImgLink(content);
            } catch (IndexOutOfBoundsException var22) {
                ;
            }

            content_ele = dl_ele.child(1);
            content = content_ele.text();
            item.setContent(content);
            newsItemList.add(item);
        }

        return newsItemList;
    }
}
