package com.mindjet.com.news_csdn.Util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


import com.mindjet.com.news_csdn.ItemBean.DetailContent;
import com.mindjet.com.news_csdn.ItemBean.DetailPartType;
import com.mindjet.com.news_csdn.ItemBean.NewsDetail;
import com.mindjet.com.news_csdn.ItemBean.NewsDetailPart;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class DecodeUtil {
	
	public static List<DetailContent> getNews(String url){
		
//		List<NewsDetailPart> parts = new ArrayList<>();
		List<DetailContent> contents = new ArrayList<>();

		Document document = null;
		try {
			document = Jsoup.connect(url).userAgent("Mozilla/4.0 (compatible; MSIE 9.0; Windows NT 6.1; Trident/5.0)").timeout(5000).get();
		} catch (IOException e) {
			e.printStackTrace();
		}

		if (document!=null){

			Element wrapper = document.getElementsByClass("detail").get(0);

			//title
			String title = wrapper.child(0).text();
//			NewsDetailPart part1 = new NewsDetailPart();
//			part1.setTitle(title);
//			parts.add(part1);
			DetailContent content_title = new DetailContent();
			content_title.setContent(title);
			content_title.setType(DetailPartType.TITLE);
			contents.add(content_title);

			//brief
			Element brief_container = wrapper.child(3);
			String brief = brief_container.child(0).attr("value");
//			NewsDetailPart part2 = new NewsDetailPart();
//			part2.setBrief(brief);
//			parts.add(part2);
			DetailContent content_brief = new DetailContent();
			content_brief.setContent(brief);
			content_brief.setType(DetailPartType.BRIEF);
			contents.add(content_brief);

			try {
				//content
				Element paragrah_container = wrapper.child(4);
				Elements paragraph = paragrah_container.getElementsByTag("p");

				//paragraph
//				NewsDetailPart part;
				DetailContent content_p;
				for (Element element : paragraph) {

					content_p = new DetailContent();

					if (element.childNodeSize()>=1){

						if (element.childNodeSize()==2&&element.child(0).nodeName().equals("b")){

							content_p.setContent(element.text());
							content_p.setType(DetailPartType.BOLD);

						}else if (element.childNodeSize()==2&&element.child(0).nodeName().equals("img")){

							content_p.setContent(element.child(0).attr("src"));
							content_p.setType(DetailPartType.IMAGELINK);

						}else {

							content_p.setContent(element.text());
							content_p.setType(DetailPartType.CONTENT);

						}

					}

					contents.add(content_p);
				}
			}catch (IndexOutOfBoundsException e){
				e.printStackTrace();
				System.out.println("=========================数据抓取失败=========================");
			}
		}

		return contents;
		
	}

}
