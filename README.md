#News_csdn
  >Fetch the news from the www.csdn.net and group it by types.

##Introduction


####Architecture

	User Interface
		|__ XListView
		|__ ViewPagerIndicator
		|__ Fragment
	
	News Resources
		|__ DataUtil
		|__ UrlUtil
		|__ DecodeUtil
		|__ AsyncTask

	Storage System
		|__ SQLite
		|__ SQLiteOpenHelper  
		|__ NewsItemDao
	
	Adapter
		|__ TabAdapter
		|__ NewsItemAdapter
		|__ NewsDetailAdapter

	Bean
		|__ NewsItem
		|__ NewsDetail
		|__ NewsDetailPart
		


####Functions

	*XListView*: provides the pull-to-refresh and pull-to-loadmore function.
	*ViewPagerIndicator*: an rubost plugin to provide smooth switch from fragment to fragment.

	*UrlUtil*: return the url according to the news type.
	*DataUtil*: use url to fetch html code and decode the news which consists of title, content, image link, link, date, news type.
	*AsyncTask*: create working threads to access csdn.net and fetch data.

	*NewsItemDao*: provide methods to add/delete/search/update items to dababase.

	*TabAdapter*: attach fragments to the ViewPagerIndicator.
	*NewsItemAdapter*: attach NewsItem to the XListView.
	*NewsDetailAdapter*: attach NewsDetailPart to the XListView in DetailActivity.

	*NewsItem*: store the title, content, image link, link, date, news type of news.
	*NewsDetailPart*: store the title, content, image link of every paragrah.


#### Screenshots

* Homepage
</br>
<img src="screenshots/homepage1.png" width="250">

* Homepage
</br>
<img src="screenshots/homepage2.png" width="250">

* Detailpage
</br>
<img src="screenshots/detailpage.png" width="250">


