package com.icephone.softwarenewsclient.service;


import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.icephone.softwarenewsclient.modle.News;
import com.icephone.softwarenewsclient.util.Constant;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.util.List;


/**
 * Created by 温程元 on 13-12-3.
 */
public class WebService {
    private static Gson gson;

    public static String getNewById(int id) {
        // TODO Auto-generated method stub
        SoapObject request = new SoapObject(Constant.NAMESPACE, Constant.GET_NEWS);//调用webservice的方法
        request.addProperty("newsId", id);//向findUserByName传入name参数
        request.addProperty("phone", 1);//向findUserByName传入name参数
        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
                SoapEnvelope.VER11);// 版本

        envelope.bodyOut = request;
        envelope.dotNet = true;
        envelope.setOutputSoapObject(request);

        HttpTransportSE trans = new HttpTransportSE(Constant.URL);
        //trans.debug = true ;	// 使用调试功能
        try {
            trans.call(Constant.NAMESPACE + Constant.GET_NEWS, envelope);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        }

        String json = null;
        try {
            if (envelope.bodyIn != null) {

                SoapObject result = (SoapObject) envelope.bodyIn;//调用webservice的返回结果
                json = result.getProperty(0).toString();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return json;
    }

    public static String getNewsByIdAndType(int id, int type) {
        // TODO Auto-generated method stub
        SoapObject request = new SoapObject(Constant.NAMESPACE, Constant.GET_TYPE_NEWS);//调用webservice的方法
        request.addProperty("newsId", id);//向findUserByName传入name参数
        request.addProperty("type", type);//向findUserByName传入name参数
        request.addProperty("phone", 1);//向findUserByName传入name参数
        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
                SoapEnvelope.VER11);// 版本

        envelope.bodyOut = request;
        envelope.dotNet = true;
        envelope.setOutputSoapObject(request);

        HttpTransportSE trans = new HttpTransportSE(Constant.URL);
        //trans.debug = true ;	// 使用调试功能
        try {
            trans.call(Constant.NAMESPACE + Constant.GET_TYPE_NEWS, envelope);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        }

        String json = null;
        try {
            if (envelope.bodyIn != null) {

                SoapObject result = (SoapObject) envelope.bodyIn;//调用webservice的返回结果
                json = result.getProperty(0).toString();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return json;
    }

    /* public static String getNewerNewsByIdAndType(int id,int type) {
         Log.e("————发出的消息——————", id+"       "+type);
         // 1、指定webservice的命名空间和调用的方法名
         SoapObject request = new SoapObject(Constant.NAMESPACE, Constant.GET_TYPE_NEWERNEWS);
         request.addProperty("newestId", (id+""));//向findUserByName传入name参数
         request.addProperty("type", type);//向findUserByName传入name参数
         request.addProperty("phone", 1);//向findUserByName传入name参数
         // 3、生成调用Webservice方法的SOAP请求信息。该信息由SoapSerializationEnvelope对象描述
         SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
                 SoapEnvelope.VER12);
         envelope.bodyOut = request;
         // c#写的应用程序必须加上这句
         envelope.dotNet = true;
         HttpTransportSE ht = new HttpTransportSE(Constant.URL);
         // 使用call方法调用WebService方法
         try {
             ht.call(null, envelope);
         } catch (HttpResponseException e) {
             e.printStackTrace();
         } catch (IOException e) {
             e.printStackTrace();
         } catch (XmlPullParserException e) {
             e.printStackTrace();
         }
         try {
             final SoapPrimitive result = (SoapPrimitive) envelope.getResponse();
             if (result != null) {
                 Log.e("----收到的回复----", result.toString());
                 return result.toString();
             }
         } catch (SoapFault e) {
             Log.e("----发生错误---", e.getMessage());
             e.printStackTrace();
         }
         return null;
     }*/
    public static String getNewerNewsByIdAndType(int id, int type) {
        // TODO Auto-generated method stub
        SoapObject request = new SoapObject(Constant.NAMESPACE, Constant.GET_TYPE_NEWERNEWS);//调用webservice的方法
        request.addProperty("newestId", id);//向findUserByName传入name参数
        request.addProperty("type", type);//向findUserByName传入name参数
        request.addProperty("phone", 1);//向findUserByName传入name参数
        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
                SoapEnvelope.VER11);// 版本

        envelope.bodyOut = request;
        envelope.dotNet = true;
        envelope.setOutputSoapObject(request);

        HttpTransportSE trans = new HttpTransportSE(Constant.URL);
        //trans.debug = true ;	// 使用调试功能
        try {
            trans.call(Constant.NAMESPACE + Constant.GET_TYPE_NEWERNEWS, envelope);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        }

        String json = null;
        try {
            if (envelope.bodyIn != null) {

                SoapObject result = (SoapObject) envelope.bodyIn;//调用webservice的返回结果
                json = result.getProperty(0).toString();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return json;
    }

    public static String subAdvice(String title, String content, String email) {
        // TODO Auto-generated method stub
        SoapObject request = new SoapObject(Constant.NAMESPACE, Constant.GET_ADVICE);//调用webservice的方法
        request.addProperty("title", title);//向findUserByName传入name参数
        request.addProperty("content", content);//向findUserByName传入name参数
        request.addProperty("email", email);//向findUserByName传入name参数
        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
                SoapEnvelope.VER11);// 版本

        envelope.bodyOut = request;
        envelope.dotNet = true;
        envelope.setOutputSoapObject(request);

        HttpTransportSE trans = new HttpTransportSE(Constant.URL);
        //trans.debug = true ;	// 使用调试功能
        try {
            trans.call(Constant.NAMESPACE + Constant.GET_ADVICE, envelope);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        }

        String json = null;
        try {
            if (envelope.bodyIn != null) {

                SoapObject result = (SoapObject) envelope.bodyIn;//调用webservice的返回结果
                json = result.getProperty(0).toString();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return json;
    }

    public static String getCurrentNews(int type) {
        // TODO Auto-generated method stub
        SoapObject request = new SoapObject(Constant.NAMESPACE, Constant.METHOD_NAME);//调用webservice的方法
        request.addProperty("typeId", type);//向findUserByName传入name参数
        request.addProperty("phone", 1);//向findUserByName传入name参数
        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
                SoapEnvelope.VER12);// 版本

        envelope.bodyOut = request;
        //   envelope.dotNet = false;
        envelope.dotNet = true;
        envelope.setOutputSoapObject(request);

        HttpTransportSE trans = new HttpTransportSE(Constant.URL);
        //trans.debug = true ;	// 使用调试功能
        try {
            trans.call(Constant.NAMESPACE + Constant.METHOD_NAME, envelope);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        }

        String json = null;
        try {
            if (envelope.bodyIn != null) {
                SoapObject result = (SoapObject) envelope.bodyIn;//调用webservice的返回结果
                json = result.getProperty(0).toString();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return json;
    }
    /*public static String getCurrentNews(int type) {
        // 1、指定webservice的命名空间和调用的方法名

        SoapObject request = new SoapObject(Constant.NAMESPACE, Constant.METHOD_NAME);
        request.addProperty("typeId", (type+""));//向findUserByName传入name参数
        request.addProperty("phone", 1);//向findUserByName传入name参数
        // 3、生成调用Webservice方法的SOAP请求信息。该信息由SoapSerializationEnvelope对象描述
        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
                SoapEnvelope.VER12);
        envelope.bodyOut = request;
        // c#写的应用程序必须加上这句
        envelope.dotNet = true;
        HttpTransportSE ht = new HttpTransportSE(Constant.URL);
        // 使用call方法调用WebService方法
        try {
            ht.call(null, envelope);
        } catch (HttpResponseException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        }
        try {
            final SoapPrimitive result = (SoapPrimitive) envelope.getResponse();
            if (result != null) {
                Log.e("----收到的回复----", result.toString());
                return result.toString();
            }
        } catch (SoapFault e) {
            Log.e("----发生错误---", e.getMessage());
            e.printStackTrace();
        }
        return null;
    }*/
   /* */

    /**
     * 调用WebService
     *
     * @return WebService的返回值
     *//*
    public String CallWebService(String MethodName, Map<String, String> Params) {
        // 1、指定webservice的命名空间和调用的方法名

        SoapObject request = new SoapObject(Constant.NAMESPACE, MethodName);
        // 2、设置调用方法的参数值，如果没有参数，可以省略，
        if (Params != null) {
            Iterator iter = Params.entrySet().iterator();
            while (iter.hasNext()) {
                Map.Entry entry = (Map.Entry) iter.next();
                request.addProperty((String) entry.getKey(),
                        (String) entry.getValue());
            }
        }
        // 3、生成调用Webservice方法的SOAP请求信息。该信息由SoapSerializationEnvelope对象描述
        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
                SoapEnvelope.VER12);
        envelope.bodyOut = request;
        // c#写的应用程序必须加上这句
        envelope.dotNet = true;
        HttpTransportSE ht = new HttpTransportSE(Constant.URL);
        // 使用call方法调用WebService方法
        try {
            ht.call(null, envelope);
        } catch (HttpResponseException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        }
        try {
            final SoapPrimitive result = (SoapPrimitive) envelope.getResponse();
            if (result != null) {
                Log.d("----收到的回复----", result.toString());
                return result.toString();
            }

        } catch (SoapFault e) {
            Log.e("----发生错误---", e.getMessage());
            e.printStackTrace();
        }
        return null;
    }*/
    public static String searchContant(String key) {
        SoapObject request = new SoapObject(Constant.NAMESPACE, Constant.SEARCH_CONTANT);//调用webservice的方法
        request.addProperty("key", key);//向findUserByName传入name参数
        request.addProperty("phone", 1);//向findUserByName传入name参数
        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
                SoapEnvelope.VER11);// 版本

        envelope.bodyOut = request;
        envelope.dotNet = true;
        envelope.setOutputSoapObject(request);

        HttpTransportSE trans = new HttpTransportSE(Constant.URL);
        //trans.debug = true ;	// 使用调试功能
        try {
            trans.call(Constant.NAMESPACE + Constant.SEARCH_CONTANT, envelope);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        }

        String json = null;
        try {
            if (envelope.bodyIn != null) {
                SoapObject result = (SoapObject) envelope.bodyIn;//调用webservice的返回结果
                json = result.getProperty(0).toString();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return json;
    }

    public static String searchTitle(String key) {
        SoapObject request = new SoapObject(Constant.NAMESPACE, Constant.SEARCH_TITLE);//调用webservice的方法
        request.addProperty("key", key);//向findUserByName传入name参数
        request.addProperty("phone", 1);//向findUserByName传入name参数
        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
                SoapEnvelope.VER11);// 版本

        envelope.bodyOut = request;
        envelope.dotNet = true;
        envelope.setOutputSoapObject(request);

        HttpTransportSE trans = new HttpTransportSE(Constant.URL);
        //trans.debug = true ;	// 使用调试功能
        try {
            trans.call(Constant.NAMESPACE + Constant.SEARCH_TITLE, envelope);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        }

        String json = null;
        try {
            if (envelope.bodyIn != null) {
                SoapObject result = (SoapObject) envelope.bodyIn;//调用webservice的返回结果
                json = result.getProperty(0).toString();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return json;
    }

    public static News returnNews(String json) {
        try {
            if (json.equals("null") || json == null) return null;
            gson = new Gson();
            return gson.fromJson(json, News.class);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static List<News> returnNewses(String json) {
        try {
            if (json.equals("null") || json == null) return null;
            gson = new Gson();
            return gson.fromJson(json, new TypeToken<List<News>>() {
            }.getType());
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    /*public static boolean getImage(String imgStr)
    {//对字节数组字符串进行Base64解码并生成图片
        if (imgStr == null) //图像数据为空
            return false;
        BASE64Decoder decoder = new BASE64Decoder();
        try
        {
            //Base64解码
            byte[] b = decoder.decodeBuffer(imgStr);
            for(int i=0;i<b.length;++i)
            {
                if(b[i]<0)
                {//调整异常数据
                    b[i]+=256;
                }
            }
            //生成jpeg图片
            String imgFilePath = "d:\\222.jpg";//新生成的图片
            OutputStream out = new FileOutputStream(imgFilePath);
            out.write(b);
            out.flush();
            out.close();
            return true;
        }
        catch (Exception e)
        {
            return false;
        }
    }*/
}
