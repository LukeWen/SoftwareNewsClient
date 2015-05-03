package com.icephone.softwarenewsclient.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;
import android.view.KeyEvent;

import com.icephone.softwarenewsclient.db.DBHelper;

import java.util.concurrent.TimeUnit;

public final class Constant {

    /**
     * SOAP相关
     */
    public static final String NAMESPACE = "http://tempuri.org/";
    public static final String URL = "http://software.hitwh.edu.cn/ServiceNews.asmx?wsdl";
    public static final String TEST = "HelloWorld";
    public static final int PAGE_SIZE = 10;

    public static long INITIAL_DELAY = 1;
    public static long PERIOD = 3;
    public static int FRAGMENT_NUMBER_OF_MAIN = 4;
    public static TimeUnit UNIT = TimeUnit.SECONDS;
    public static boolean IS_SERVICE_WORKING = false;
    public static String SERVICE_STATE = "SERVICE_STATE";
    public static boolean IS_NETWORK_WORKING = false;
    private static int counter = 0;

    public static int countStr(String str1, String str2) {
        if (str1.indexOf(str2) == -1) {
            return 0;
        } else if (str1.indexOf(str2) != -1) {
            counter++;
            countStr(str1.substring(str1.indexOf(str2) +
                    str2.length()), str2);
            return counter;
        }
        return 0;
    }


    public static Boolean DBInjectionAttack(String str) {
        String s = str.replaceAll("(?u)drop", "DROP");
        s = s.replaceAll("(?u)delete", "DELETE");
        s = s.replaceAll("(?u)insert", "INSERT");
        s = s.replaceAll("(?u)create", "CREATE");
        return countStr(s, "\"") > 0 || countStr(s, "'") > 0 || countStr(s, "DROP") > 0 || countStr(s, "news") > 0 || countStr(s, "DELETE") > 0 || countStr(s, "INSERT") > 0 || countStr(s, "CREATE") > 0;
    }


    public static boolean isWifiConnected(Context context) {
        if (context != null) {
            ConnectivityManager mConnectivityManager = (ConnectivityManager) context
                    .getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo mWiFiNetworkInfo = mConnectivityManager
                    .getNetworkInfo(ConnectivityManager.TYPE_WIFI);
            if (mWiFiNetworkInfo != null) {
                return mWiFiNetworkInfo.isAvailable();
            }
        }
        return false;
    }


    public static boolean isNetworkAvailable(Context context) {
        ConnectivityManager connectivity = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivity == null) {
            Constant.IS_NETWORK_WORKING = false;
            Log.i("NetWorkState", "NetWork Unavailable");
            return false;
        } else {
            NetworkInfo[] info = connectivity.getAllNetworkInfo();
            if (info != null) {
                for (NetworkInfo anInfo : info) {
                    if (anInfo.getState() == NetworkInfo.State.CONNECTED) {
                        Log.i("NetWorkState", "NetWork Available");
                        Constant.IS_NETWORK_WORKING = true;
                        ServiceAvailableTestThread serviceAvailableTestThread = new ServiceAvailableTestThread(context);
                        serviceAvailableTestThread.start();
                        return true;
                    }
                }
            }
        }
        Constant.IS_NETWORK_WORKING = false;
        Log.i("NetWorkState", "NetWork Unavailable");
        return false;
    }


    public static boolean FINSH(int keyCode, KeyEvent event, Context c) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
            DBHelper helper = new DBHelper(c);
            helper.close();
            System.exit(0);
            android.os.Process.killProcess(android.os.Process.myPid());
            return true;
        } else
            return false;
    }

    public enum WebserviceMethod {
        GetCategoryName, GetInformation, GetInformationName, GetNewsDetail, GetNewsPageCountCategory,
        GetNewsPageCountOutline, GetNewsSizeByArticle, GetNewsSizeByTitle, GetNewsSizeByTitleAndArticle,
        GetOutlineName, GetResourcesPageCount, GetResourcesWithPageNumber,
        GetSingleCategoryNewsListWithPageNumber, GetSingleOutlineNewsListWithPageNumber,
        SearchNewsByArticle, SearchNewsByTitle, SearchNewsByTitleAndArticle
    }

    public static final class DBProperty {
        /**
         * 数据库名称
         */
        public static final String DB_NAME = "icephone.db";//数据库名称

        /**
         * 数据库版本
         */
        public static final int DB_VERSION = 1;

        /**
         * 数据库表名称
         */
        public static final String TBL_NAME = "news";
        /**
         * 创建news数据库sql
         */
        public static final String CREATE_TBL = " create table "
                + " news(id integer primary key not null,outlineId int,category_id int,title varchar(150) not null,article text"
                + ",update_time datetime not null,page_view int,supervisor_id int,alias varchar(100));";
    }
}
