#News_csdn
  >Fetch the news from the www.csdn.net and group it by types.

##Introduction
***

####Architecture
***
	User Interface
		|__ XListView
		|__ ViewPagerIndicator
		|__ Fragment
	
	News Resources
		|__ DataUtil
		|__ UrlUtil
		|__ AsyncTask

	Storage System
		|__ SQLite
		|__ SQLiteOpenHelper  
		|__ NewsItemDao
	
	Adapter
		|__ TabAdapter
		|__ NewsItemAdapter

	Bean
		|__ NewsItem


#####Functions
***
	*XListView*: provides the pull-to-refresh and pull-to-loadmore function.
	*ViewPagerIndicator*: an rubost plugin to provide smooth switch from fragment to fragment.

	*UrlUtil*: return the url according to the news type.
	*DataUtil*: use url to fetch html code and decode the news which consists of title, content, image link, link, date, news type.
	*AsyncTask*: create working threads to access csdn.net and fetch data.

	*NewsItemDao*: provide methods to add/delete/search/update items to dababase.

	*TabAdapter*: attach fragments to the ViewPagerIndicator.
	*NewsItemAdapter*: attach NewsItem to the XListView.

	*NewsItem*: store the title, content, image link, link, date, news type of news.


##### Screen shots
***
	Coming soon.