package com.icephone.softwarenewsclient.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;
import android.view.KeyEvent;

import com.icephone.softwarenewsclient.db.DBHelper;
import com.icephone.softwarenewsclient.service.WebService;

import java.util.concurrent.TimeUnit;

public final class Constant {

    /**
     * SOAP相关
     */
    public static final String NAMESPACE = "http://tempuri.org/";
    public static final String URL = "http://software.hitwh.edu.cn/ServiceNews.asmx?wsdl";
    public static final String METHOD_NAME = "getCurrentNews";
    public static final String GET_NEWS = "getNewById";
    public static final String GET_TYPE_NEWS = "getNewsByIdAndType";
    public static final String GET_TYPE_NEWERNEWS = "getNewerNewsByIdAndType";
    public static final String SEARCH_TITLE = "searchTitle";
    public static final String SEARCH_CONTANT = "searchContent";
    public static final String GET_ADVICE = "getAdvice";
    public static final String TEST = "HelloWorld";
    public static final int FIRST_LOAD_NUM = 10;
    public static final int SEARCH_TIME = 20;
    /**
     *
     */
    public final static String TAB_TAG_NEWS = "tab_tag_news";
    public final static String TAB_TAG_Notify = "tab_tag_notify";
    public final static String TAB_TAG_CALENDAR = "tab_tag_calendar";
    public final static String TAB_TAG_SEARCH = "tab_tag_search";
    public final static String TAB_TAG_MORE = "tab_tag_more";

    /**
     * 所有表字段
     */
    public static final String KEY_ID = "_id";
    public static final String KEY_TITLE = "newsTitle";
    public static final String KEY_TIME = "newsTime";
    public static final String KEY_SEENUM = "seeNum";
    public static final String KEY_NewsID = "newsId";
    public static long INITIAL_DELAY = 1;
    public static long PERIOD = 3;
    public static int FRAGMENT_NUMBER_OF_MAIN = 4;
    public static TimeUnit UNIT = TimeUnit.SECONDS;
    public static boolean IS_SERVICE_WORKING = false;
    public static boolean IS_NETWORK_WORKING = false;
    public static int HOME_FIRST_LOAD_NUM = 4;
    public static long CLICKTIME = 0;
    public static boolean FLAG = false;
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

    public static boolean isNetworkConnected(Context context) {
        if (context != null) {
            ConnectivityManager mConnectivityManager = (ConnectivityManager) context
                    .getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo mNetworkInfo = mConnectivityManager.getActiveNetworkInfo();
            if (mNetworkInfo != null) {
                return mNetworkInfo.isAvailable();
            }
        }
        return false;
    }

    /**
     * @param context
     * @return
     */
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

    /**
     * @param context
     * @return
     */
    public static boolean isMobileConnected(Context context) {
        if (context != null) {
            ConnectivityManager mConnectivityManager = (ConnectivityManager) context
                    .getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo mMobileNetworkInfo = mConnectivityManager
                    .getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
            if (mMobileNetworkInfo != null) {
                return mMobileNetworkInfo.isAvailable();
            }
        }
        return false;
    }

    public static int getConnectedType(Context context) {
        if (context != null) {
            ConnectivityManager mConnectivityManager = (ConnectivityManager) context
                    .getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo mNetworkInfo = mConnectivityManager.getActiveNetworkInfo();
            if (mNetworkInfo != null && mNetworkInfo.isAvailable()) {
                return mNetworkInfo.getType();
            }
        }
        return -1;
    }

    public static boolean isNetworkAvailable(Context context) {
        ConnectivityManager connectivity = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivity == null) {
            Log.i("NetWorkState", "Unavailable");
            return false;
        } else {
            NetworkInfo[] info = connectivity.getAllNetworkInfo();
            if (info != null) {
                for (int i = 0; i < info.length; i++) {
                    if (info[i].getState() == NetworkInfo.State.CONNECTED) {
                        Log.i("NetWorkState", "Available");
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public static void serviceAvailableTest(Context context) {
        try {
            Thread t = new Thread() {
                @Override
                public void run() {
                    String test = WebService.test();
                    WebService.SearchNewsByTitle("2012");
                    if (test != null && test.equals("Hello World")) {
                        Constant.IS_SERVICE_WORKING = true;
                    }
                }
            };
            t.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static final boolean FINSH(int keyCode, KeyEvent event, Context c) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
            DBHelper helper = new DBHelper(c);
            helper.close();
            System.exit(0);
            android.os.Process.killProcess(android.os.Process.myPid());

            return true;
        } else
            return false;
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
                + " news(newId integer primary key not null,typeId int not null,adminId id not null,newsTitle varchar(50) not null"
                + ",newsContent text not null,newsTime datetime not null,seeNum int,thumbnail varchar(50),subType int);";
        /**
         * 创建文章主题数据库sql
         */
        /*public static final String TabelPicture_CREATE =
                "CREATE TABLE IF NOT EXISTS pictures (_id integer primary key autoincrement, " +
				"picUrl VARCHAR not null, _newsId integer);";*/
    }

    private static class CourseSystem {
        //final static String courseUrl = "http://222.194.15.1:7777/zhxt_bks/zhxt_bks.html";

    }

    /**
     * Webservice的函数
     */
    public final static class WebserviceMethodName {
        /**
         * 一次从webservice获取的数据条数
         */
        public final static int GETNewsNumber = 10;

        /**
         * 查看时候需要更新
         */
        public final static String CheckUpdate = "GetMaxNewId";

        /**
         * 获取更新数据方法
         */
        public final static String GetUpdateNews = "getUpdateNews";

        /**
         * 获取之前的数据
         */
        public final static String getPreviousNew = "getPreviousNew";

        /**
         * 根据id获取新闻
         */
        public final static String getNewsByid = "getNewsByid";

        /**
         * 获取最新的新闻
         */
        public final static String getTopNewsByTypeid = "getTopNewsByTypeid";

    }

    public static class IntentFilterAction {
        public static String DATALOADED = "software.data.loadFinished";
    }
}
