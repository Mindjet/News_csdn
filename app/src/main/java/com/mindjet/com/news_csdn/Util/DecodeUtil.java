package com.mindjet.com.news_csdn.Util;

import java.util.ArrayList;
import java.util.List;


import com.mindjet.com.news_csdn.ItemBean.NewsDetail;
import com.mindjet.com.news_csdn.ItemBean.NewsDetailPart;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.select.Elements;
import org.jsoup.select.Evaluator;

public class DecodeUtil {
	
	public static NewsDetail getNews(String url){
		
		NewsDetail newsDetail = new NewsDetail();
		List<NewsDetailPart> parts = new ArrayList<>();
		
		String html = DataUtil.doGet(url);

		Document document = Jsoup.parse(html);

		System.out.println(document);

		Element wrapper = document.getElementsByClass("wrapper").get(0);


		//title
		String title = wrapper.child(0).text();
		NewsDetailPart part1 = new NewsDetailPart();
		part1.setTitle(title);
		parts.add(part1);

		//brief
		Element brief_container = document.getElementsByClass("share").get(0);
		String brief = brief_container.child(0).attr("title");
		NewsDetailPart part2 = new NewsDetailPart();
		part2.setBrief(brief);
		parts.add(part2);

		try {
			//content
			Element contents = wrapper.child(2);
			Elements paragraph = contents.getElementsByTag("p");

			//paragraph
			NewsDetailPart part;
			for (Element element : paragraph) {

				part = new NewsDetailPart();
				System.out.println(element.childNodeSize());

				if (element.childNodeSize() == 1){

					if (element.childNode(0).nodeName().equals("b"))
						part.setType(NewsDetailPart.PartType.BOLD);
					part.setContent(element.text());

				}else if (element.childNodeSize() == 2){

					if (element.childNode(0).nodeName().equals("img"))
						part.setImageLink(element.childNode(0).attr("src"));
					else if (element.childNode(0).nodeName().equals("b")){
						part.setContent(element.text());
						part.setType(NewsDetailPart.PartType.BOLD);
					}

				}else if (element.childNodeSize() == 3){
					part.setContent(element.text());
				}

				parts.add(part);
			}
		}catch (IndexOutOfBoundsException e){
			e.printStackTrace();
			System.out.println("===========================数据抓取失败========================");
		}

		newsDetail.setList(parts);
		
		return newsDetail;
		
	}

}
