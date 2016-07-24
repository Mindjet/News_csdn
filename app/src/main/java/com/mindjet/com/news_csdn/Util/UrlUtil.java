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
                    urlStr = NEWS_LIST_URL_YEJIE;
                    break;
                case Constraint.NEWS_TYPE_YIDONG:
                    urlStr = NEWS_LIST_URL_YIDONG;
                    break;
                case Constraint.NEWS_TYPE_YANFA:
                    urlStr = NEWS_LIST_URL_YANFA;
                    break;
                case Constraint.NEWS_TYPE_CHENGXUYUAN:
                    urlStr = NEWS_LIST_URL_CHENGXUYUAN;
                    break;
                case Constraint.NEWS_TYPE_YUNJISUAN:
                    urlStr = NEWS_LIST_URL_YUNJISUAN;
                    break;
                default:
                    urlStr = NEWS_LIST_URL;
            }

            urlStr = urlStr + "/" + currentPage;
            return urlStr;
        }


    }
