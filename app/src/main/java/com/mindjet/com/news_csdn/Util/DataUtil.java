package com.mindjet.com.news_csdn.Util;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/** Get the html code by using the url
 * @author Mindjet
 * @date 2016/6/30
 */
public class DataUtil {

    public static String doGet(String urlStr) {
//        StringBuilder stringBuilder = new StringBuilder();
//        InputStream is = null;
//        InputStreamReader isr = null;
//
//        System.out.println(urlStr);
//
//        try {
//            URL url = new URL(urlStr);
//            HttpURLConnection conn = (HttpURLConnection)url.openConnection();
//            conn.setRequestMethod("GET");
//            conn.setConnectTimeout(5000);
//            is = conn.getInputStream();
//            isr = new InputStreamReader(is, "UTF-8");
//            char[] buf = new char[1024];
//
//            int len = 0;
//            while((len = isr.read(buf)) != -1) {
//                String str = new String(buf, 0, len);
//                stringBuilder.append(str);
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }finally {
//            try {
//                if (isr != null) {
//                    isr.close();
//                }
//                if (is != null) {
//                    is.close();
//                }
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//
//        }
//
//        return stringBuilder.toString();

        StringBuilder builder = new StringBuilder();
        InputStream in = null;
        InputStreamReader isr = null;

        try {
            URL myurl = new URL(urlStr);
            HttpURLConnection conn = (HttpURLConnection) myurl.openConnection();
            conn.setRequestMethod("GET");
            conn.setConnectTimeout(5000);

            in = conn.getInputStream();
            isr = new InputStreamReader(in,"utf-8");

            char[] buf = new char[1024];
            int len = 0;

            while ((len = isr.read(buf))!=-1) {
                String str = new String(buf, 0, len);
                builder.append(str);
            }

        } catch (MalformedURLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }finally{
            try {
                isr.close();
                in.close();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

        }


        return builder.toString();


    }

}
