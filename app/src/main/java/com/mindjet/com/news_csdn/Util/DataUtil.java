package com.mindjet.com.news_csdn.Util;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * @author Mindjet
 * @date 2016/6/30
 */
public class DataUtil {

    public static String doGet(String urlStr) {
        StringBuffer stringBuffer = new StringBuffer();

        try {
            URL url = new URL(urlStr);
            HttpURLConnection conn = (HttpURLConnection)url.openConnection();
            conn.setRequestMethod("GET");
            conn.setConnectTimeout(5000);
            conn.setDoInput(true);
            conn.setDoOutput(true);
            if(conn.getResponseCode() == 200) {
                InputStream is = conn.getInputStream();
                InputStreamReader isr = new InputStreamReader(is, "UTF-8");
                boolean len = false;
                char[] buf = new char[1024];

                int len1;
                while((len1 = isr.read(buf)) != -1) {
                    stringBuffer.append(new String(buf, 0, len1));
                }

                is.close();
                isr.close();
            }
        } catch (Exception var8) {
            ;
        }

        return stringBuffer.toString();
    }

}
