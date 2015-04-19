package com.icephone.softwarenewsclient.service;

import android.util.Log;

import com.icephone.softwarenewsclient.util.Constant;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


/**
 * Created by 温程元 on 13-12-3.
 */
public class WebService {
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
        for (int i = 0; i < result.getPropertyCount(); i++) {
            SoapObject soapChilds = (SoapObject) result.getProperty(i);
            Log.d("SearchNewsByTitle" + i, soapChilds.toString());
            //list.add(soapChilds.getProperty("price20GP").toString());
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
