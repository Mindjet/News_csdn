package com.mindjet.com.news_csdn.Util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * @author Mindjet
 * @date 2016/7/1
 */
public class NetUtil {

    public static boolean checkNet(Context context){

        boolean isWifiConnected = isWifiConnected(context);
        boolean isCelluarConnected = isCelluarConnected(context);

        return isCelluarConnected || isWifiConnected;
    }

    private static boolean isCelluarConnected(Context context) {

        ConnectivityManager manager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = manager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
        return info != null && info.isConnected();

    }

    private static boolean isWifiConnected(Context context) {

        ConnectivityManager manager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = manager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        return info != null && info.isConnected();

    }

}
