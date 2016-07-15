package com.mindjet.com.news_csdn.ItemBean;

public class NewsDetailPart {
	
	private String title;
	private String brief;
	
	private String content;
	private String imageLink;
	
	private int type;
	
	public static interface PartType{
		
		public static int TITLE = 1;
		public static int BRIEF = 2;
		public static int CONTENT = 3;
		public static int IMAGELINK = 4;
		public static int BOLD = 5;
		
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
		this.type = PartType.TITLE;
	}

	public String getBrief() {
		return brief;
	}

	public void setBrief(String brief) {
		this.brief = brief;
		this.type = PartType.BRIEF;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
		this.type = PartType.CONTENT;
	}

	public String getImageLink() {
		return imageLink;
	}

	public void setImageLink(String imageLink) {
		this.imageLink = imageLink;
		this.type = PartType.IMAGELINK;
	}
	
	public void setType(int type){
		this.type = type;
	}
	
	public int getType(){
		
		return this.type;
		
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "title = "+title+", content = "+content+", imageLink = "+imageLink+", brief = "+brief;
	}

}
