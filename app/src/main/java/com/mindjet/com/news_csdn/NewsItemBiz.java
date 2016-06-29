package com.mindjet.com.news_csdn;

import com.mindjet.com.news_csdn.Bean.CommonException;
import com.mindjet.com.news_csdn.Bean.NewsItem;
import com.mindjet.com.news_csdn.Utils.DataUtil;
import com.mindjet.com.news_csdn.Utils.UrlUtil;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Mindjet
 * @date 2016/6/29
 */
public class NewsItemBiz {

    public List<NewsItem> getNewsItems(int newsType, int currentPage) throws CommonException{

        List<NewsItem> newsItemList = new ArrayList<>();

        String urlStr = UrlUtil.generateUrl(newsType, currentPage);
        String htmlStr = DataUtil.doGet(urlStr);

        Document doc = Jsoup.parse(htmlStr);
        Elements units = doc.getElementsByClass("unit");

        for (int i = 0; i< units.size();i++){

            NewsItem item = new NewsItem();

            Element unit_ele = units.get(i);
            Element h1_ele = unit_ele.getElementsByTag("h1").get(0);
            Element h1_a_ele = h1_ele.child(0);

            String title = h1_a_ele.text();   //获得标题
            String href = h1_a_ele.attr("href");    //获得链接

            item.setTitle(title);
            item.setLink(href);

            Element h4_ele = unit_ele.getElementsByTag("h4").get(0);
            Element h4_ago_ele = h4_ele.getElementsByClass("ago").get(0);
            String date = h4_ago_ele.text();        //获得日期

            item.setDate(date);

            Element dl_ele = unit_ele.getElementsByTag("dl").get(0);
            Element dl_dt_ele = dl_ele.child(0);

            try {

                Element dl_dt_img_ele = dl_dt_ele.child(0);
                String imgLink = dl_dt_img_ele.child(0).attr("src");    //获得图片链接
                item.setImgLink(imgLink);

            }catch (IndexOutOfBoundsException e){

            }

            Element content_ele = dl_ele.child(1);
            String content = content_ele.text();        //获得内容
            item.setContent(content);
            newsItemList.add(item);

        }

        return newsItemList;

    }

}
