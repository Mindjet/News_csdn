package com.mindjet.com.news_csdn.ItemBean;

import java.util.List;

public class NewsDetail {
	
	private String nextPageUrl;
	private List<NewsDetailPart> list;
	
	public String getNextPageUrl() {
		return nextPageUrl;
	}
	public void setNextPageUrl(String nextPageUrl) {
		this.nextPageUrl = nextPageUrl;
	}
	public List<NewsDetailPart> getList() {
		return list;
	}
	public void setList(List<NewsDetailPart> list) {
		this.list = list;
	}
	
	
	

}
