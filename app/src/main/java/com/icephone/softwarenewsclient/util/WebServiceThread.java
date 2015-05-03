package com.icephone.softwarenewsclient.util;

import android.content.Context;
import android.util.Log;

import com.icephone.softwarenewsclient.db.DBHelper;
import com.icephone.softwarenewsclient.util.Constant.WebserviceMethod;

import java.util.List;
import java.util.Map;

/**
 * SoftwareNewsClient
 * Created by 温程元 on 2015/4/20.
 */
public class WebServiceThread extends Thread {
    private WebserviceMethod webserviceMethod;
    private Map<String, Object> parameter;
    private Context c;
    private DBHelper dbHelper;

    public WebServiceThread(
            WebserviceMethod outWebserviceMethod, Map<String, Object> outParameter, Context context) {
        webserviceMethod = outWebserviceMethod;
        parameter = outParameter;
        c = context;
        dbHelper = new DBHelper(c);
    }

    @Override
    public void run() {
        switch (webserviceMethod) {
            case SearchNewsByTitle:
                Log.i("SearchNewsByTitle", parameter.get("key").toString());
                List newsList = WebService.SearchNewsByTitle(parameter.get("key").toString());
                if (newsList != null && newsList.size() > 0) {
                    dbHelper.insertListDetail(newsList);
                }
                break;
            case GetNewsDetail:
                WebService.GetNewsDetail(650);
                break;
            case GetCategoryName:
                for (int i = 0; i < 30; i++) {
                    Log.i("GetCategoryName", i + ":" + WebService.GetCategoryName(i));
                }
                break;
            case GetOutlineName:
                for (int i = 0; i < 5; i++) {
                    Log.i("GetOutlineName", i + ":" + WebService.GetOutlineName(i));
                }
                break;
            case GetSingleOutlineNewsListWithPageNumber:
                Log.i("outlineId", parameter.get("outlineId").toString());
                WebService.GetSingleOutlineNewsListWithPageNumber(
                        Integer.parseInt(parameter.get("outlineId").toString()));
            case GetSingleCategoryNewsListWithPageNumber:
                for (int i = 1; i < 15; i++) {
                    Log.i("CategoryId", i + "");
                    WebService.GetSingleCategoryNewsListWithPageNumber(i);
                }
                break;
            default:
                break;
        }
    }
}
