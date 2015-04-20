package com.icephone.softwarenewsclient.service;

import android.util.Log;

import com.icephone.softwarenewsclient.modle.News;
import com.icephone.softwarenewsclient.modle.NewsTitle;
import com.icephone.softwarenewsclient.util.Constant;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;


/**
 * Created by 温程元 on 13-12-3.
 */
public class WebService {
    private static final DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ", Locale.CHINA);
    public static SoapObject SoapRequest(String methodName, Map<String, Object> content) {
        SoapObject request = new SoapObject(Constant.NAMESPACE, methodName);//调用webservice的方法
        int contentSize = content.size();
        String key;
        Object[] keys = content.keySet().toArray();
        for (int i = 0; i < contentSize; i++) {
            key = keys[i].toString();
//            Log.d(key, content.get(key).getClass().toString());
            request.addProperty(key, content.get(key));//向findUserByName传入name参数
        }
//        Log.d("SoapRequest", request.toString());

        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
                SoapEnvelope.VER12);// 版本

        envelope.bodyOut = request;
        envelope.dotNet = true;
        envelope.setOutputSoapObject(request);

        HttpTransportSE trans = new HttpTransportSE(Constant.URL);
        //trans.debug = true ;	// 使用调试功能
        try {
            trans.call(Constant.NAMESPACE + methodName, envelope);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        } catch (XmlPullParserException e) {
            e.printStackTrace();
            return null;
        }

        SoapObject result = null;
        try {
            if (envelope.bodyIn != null) {
                result = (SoapObject) envelope.bodyIn;//调用webservice的返回结果
                result = (SoapObject) result.getProperty(0);
                result = (SoapObject) result.getProperty(1);
                result = (SoapObject) result.getProperty(0);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return result;
    }

    public static String SearchNewsByTitle(String key) {
        Map<String, Object> content = new HashMap<>();
        content.put("str", key);
        content.put("pageSize", 10);
        content.put("pageRequested", 1);
        SoapObject result = SoapRequest("SearchNewsByTitle", content);
        if (result != null) {

            for (int i = 0; i < result.getPropertyCount(); i++) {
                SoapObject soap = (SoapObject) result.getProperty(i);
                Log.i("soap", soap.toString());
                NewsTitle newsTitle = new NewsTitle();
                newsTitle.setId(Integer.parseInt(soap.getProperty("id").toString()));
                newsTitle.setTitle(soap.getProperty("title").toString());

                try {
                    newsTitle.setUpdate_time(dateFormat.parse(soap.getProperty("update_time").toString()));
                    Log.i("newsTitle.Update_time", newsTitle.getUpdate_time().toString());
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
        }

        return "";
    }

    public static String GetNewsDetail(int newsId) {
        Map<String, Object> content = new HashMap<>();
        content.put("newsId", newsId);
        SoapObject result = SoapRequest("GetNewsDetail", content);
        if (result != null) {
            for (int i = 0; i < result.getPropertyCount(); i++) {
                SoapObject soap = (SoapObject) result.getProperty(i);
                Log.i("soap", soap.toString());
                News news = new News();
                news.setId(Integer.parseInt(soap.getProperty("id").toString()));
                news.setCategory_id(Integer.parseInt(soap.getProperty("category_id").toString()));
                news.setTitle(soap.getProperty("title").toString());
                news.setArticle(soap.getProperty("article").toString());
                try {
                    news.setUpdate_time(dateFormat.parse(soap.getProperty("update_time").toString()));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                news.setPage_view(Integer.parseInt(soap.getProperty("page_view").toString()));
                news.setSupervisor_id(Integer.parseInt(soap.getProperty("supervisor_id").toString()));
                news.setAlias(soap.getProperty("alias").toString());
            }
        }
        return "";
    }


    public static String test() {
        SoapObject request = new SoapObject(Constant.NAMESPACE, Constant.TEST);//调用webservice的方法
        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
                SoapEnvelope.VER12);// 版本

        envelope.bodyOut = request;
        envelope.dotNet = true;
        envelope.setOutputSoapObject(request);

        HttpTransportSE trans = new HttpTransportSE(Constant.URL);
        //trans.debug = true ;// 使用调试功能
        try {
            trans.call(Constant.NAMESPACE + Constant.TEST, envelope);
        } catch (IOException e) {
            e.printStackTrace();
            return "ERROR:IOException";
        } catch (XmlPullParserException e) {
            e.printStackTrace();
            return "ERROR:XmlPullParserException";
        }

        String response = null;
        try {
            if (envelope.bodyIn != null) {

                SoapObject result = (SoapObject) envelope.bodyIn;//调用webservice的返回结果
                response = result.getProperty(0).toString();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "ERROR:Exception";
        }
        return response;
    }


}
