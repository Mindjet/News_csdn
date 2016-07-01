package com.mindjet.com.news_csdn.Util;

/**
 * @author Mindjet
 * @date 2016/6/30
 */
public class UrlUtil {

        public static final String NEWS_LIST_URL = "http://www.csdn.net/headlines.html";
        public static final String NEWS_LIST_URL_YEJIE = "http://news.csdn.net/news";
        public static final String NEWS_LIST_URL_YIDONG = "http://mobile.csdn.net/mobile";
        public static final String NEWS_LIST_URL_YANFA = "http://sd.csdn.net/sd";
        public static final String NEWS_LIST_URL_CHENGXUYUAN = "http://programmer.csdn.net/programmer";
        public static final String NEWS_LIST_URL_YUNJISUAN = "http://cloud.csdn.net/cloud";

        public static String generateUrl(int newsType, int currentPage) {
            String urlStr = "";
            currentPage = currentPage > 0?currentPage:0;
            switch(newsType) {
                case Constraint.NEWS_TYPE_YEJIE:
                    urlStr = "http://news.csdn.net/news";
                    break;
                case Constraint.NEWS_TYPE_YIDONG:
                    urlStr = "http://mobile.csdn.net/mobile";
                    break;
                case Constraint.NEWS_TYPE_YANFA:
                    urlStr = "http://sd.csdn.net/sd";
                    break;
                case Constraint.NEWS_TYPE_CHENGXUYUAN:
                    urlStr = "http://programmer.csdn.net/programmer";
                    break;
                case Constraint.NEWS_TYPE_YUNJISUAN:
                    urlStr = "http://cloud.csdn.net/cloud";
                    break;
                default:
                    urlStr = "http://news.csdn.net/news";
            }

            urlStr = urlStr + "/" + currentPage;
            return urlStr;
        }


    }
