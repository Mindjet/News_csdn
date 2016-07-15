package com.mindjet.com.news_csdn.Util;

import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.select.Elements;

import com.mindjet.com.news_csdn.ItemBean.NewsDetail;
import com.mindjet.com.news_csdn.ItemBean.NewsDetailPart;

public class DecodeUtil {
	
	public static NewsDetail getNews(String url){
		
		NewsDetail newsDetail = new NewsDetail();
		List<NewsDetailPart> parts = new ArrayList<>();
		
		String html = DataUtil.doGet(url);
		Document document = Jsoup.parse(html);
		
		Element wrapper = document.getElementsByClass("detail").get(0);
		
		//title
		String title = wrapper.child(0).text();
		NewsDetailPart part1 = new NewsDetailPart();
		part1.setTitle(title);
		parts.add(part1);
		
		//brief
		String brief = wrapper.child(3).text();
		NewsDetailPart part2 = new NewsDetailPart();
		part2.setBrief(brief);
		parts.add(part2);
		
		//content
		Element contents = wrapper.child(4);
		Elements paragraph = contents.getElementsByTag("p");
		//paragraph
		NewsDetailPart part = null;
		Node child0 = null;
		for (Element element : paragraph) {
			
			part = new NewsDetailPart();
			
			if (element.childNodeSize()==1) {
				
				Node node = element.childNode(0);
				if (node.nodeName().equals("b")) {
					part.setContent(element.text());
					part.setType(NewsDetailPart.PartType.BOLD);
				}else if (node.nodeName().equals("img")) {
					part.setImageLink(node.attr("src"));
				}else{
					part.setContent(element.text());
				}
				
			}
			parts.add(part);
		}
		
		newsDetail.setList(parts);
		
		return newsDetail;
		
	}

}
