package com.mindjet.com.news_csdn.Utils;

import com.mindjet.com.news_csdn.Bean.CommonException;

import java.io.InputStream;
import java.net.ConnectException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * @author Mindjet
 * @date 2016/6/29
 */
public class DataUtil {


    /**
     * 返回对应链接的 html 数据
     * @param urlStr 目标链接
     * @return  stringBuffer  html码
     * @throws CommonException
     */

    public static String doGet(String urlStr) throws CommonException{

        StringBuffer stringBuffer = new StringBuffer();

        try {

            URL url = new URL(urlStr);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setConnectTimeout(5000);
            conn.setDoInput(true);
            conn.setDoOutput(true);

            if (conn.getResponseCode() == 200){

                InputStream is = conn.getInputStream();
                int len = 0;
                byte[] buf = new byte[1024];

                while ((len=is.read(buf))!=-1){

                    stringBuffer.append(new String(buf, 0, len, "UTF-8"));

                }

                is.close();

            }else {

                throw new CommonException("访问网络失败");

            }

        } catch (Exception e) {

            throw new CommonException("访问网络失败");

        }

        return stringBuffer.toString();
    }


}
