package com.icephone.softwarenewsclient.util;

import android.util.Log;

import com.icephone.softwarenewsclient.modle.News;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;


/**
 * Created by 温程元 on 13-12-3.This is not a service
 */
public class WebService {
    private static final DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ", Locale.ENGLISH);
    private static final DateFormat dateFormatUnbelievable = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ", Locale.ENGLISH);

    /**
     * Soap request.
     *
     * @param methodName the method name
     * @param content    the content
     * @return the soap object
     */
    private static SoapObject SoapRequest(String methodName, Map<String, Object> content) {
        SoapObject request = new SoapObject(Constant.NAMESPACE, methodName);//调用webservice的方法
        int contentSize = content.size();
        String key;
        Object[] keys = content.keySet().toArray();

        for (int i = 0; i < contentSize; i++) {
            key = keys[i].toString();
            request.addProperty(key, content.get(key));//向findUserByName传入name参数
        }
//        Log.d("SoapRequest", request.toString());

        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
                SoapEnvelope.VER12);// 版本

        envelope.bodyOut = request;
        envelope.dotNet = true;
        envelope.setOutputSoapObject(request);

        HttpTransportSE trans = new HttpTransportSE(Constant.URL);
        //trans.debug = true ;// 使用调试功能
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

    /**
     * Data set trans.
     *
     * @param soapObject the soap object
     * @return the soap object
     */
    private static SoapObject DataSetTrans(SoapObject soapObject) {
        SoapObject result = null;
        if (soapObject != null && soapObject.getPropertyCount() > 0) {
            result = (SoapObject) soapObject.getProperty(0);
            Log.i("soapObject", soapObject.toString());
        }
        if (result != null && result.getPropertyCount() > 0) {
            result = (SoapObject) result.getProperty(1);
            Log.i("result", result.toString());
        }
        if (result != null && result.getPropertyCount() > 0) {
            result = (SoapObject) result.getProperty(0);
        }
        return result;
    }

    /**
     * String trans.
     *
     * @param soapObject the soap object
     * @return the string
     */
    private static String StringTrans(SoapObject soapObject) {
        String result = null;
        if (soapObject != null && soapObject.getPropertyCount() > 0) {
            result = soapObject.getPropertyAsString(0);
        }
        return result;
    }

    /**
     * Integer trans.
     *
     * @param soapObject the soap object
     * @return the integer
     */
    private static int IntTrans(SoapObject soapObject) {
        int result = -3;
        if (soapObject != null && soapObject.getPropertyCount() > 0) {
            result = Integer.parseInt(soapObject.getProperty(0).toString());
        }
        return result;
    }


    /**
     * Search news by title.
     * 根据标题搜索新闻
     *
     * @param key the keyword
     * @return the news list
     */
    public static List SearchNewsByTitle(String key) {
        if (null == key || key.length() < 1) {
            return null;
        }
        int pages = GetNewsSizeByTitle(key, Constant.PAGE_SIZE);
        List<News> newsList = null;
        for (int j = 0; j < pages; j++) {
            Map<String, Object> content = new HashMap<>();
            content.put("str", key);
            content.put("pageSize", Constant.PAGE_SIZE);
            content.put("pageRequested", j);
            SoapObject result = DataSetTrans(SoapRequest("SearchNewsByTitle", content));

            if (result != null) {
                int count = result.getPropertyCount();
                newsList = new ArrayList<>(count);
                for (int i = 0; i < count; i++) {
                    SoapObject soap = (SoapObject) result.getProperty(i);
                    News news = new News();
                    news.setId(Integer.parseInt(soap.getProperty("id").toString()));
                    news.setTitle(soap.getProperty("title").toString());
                    try {
                        news.setUpdate_time(dateFormat.parse(soap.getProperty("update_time").toString()));
                    } catch (ParseException e) {
                        try {
                            news.setUpdate_time(dateFormatUnbelievable.parse(soap.getProperty("update_time").toString()));
                        } catch (ParseException e1) {
                            e1.printStackTrace();
                            return null;
                        }
                    }
                    newsList.add(news);
                }
            }
        }
        return newsList;
    }

    /**
     * Search news by article.
     * 根据内容搜索新闻
     *
     * @param key the keyword
     * @return the news list
     */
    public static List SearchNewsByArticle(String key) {
        if (null == key || key.length() < 1) {
            return null;
        }
        int pages = GetNewsSizeByArticle(key, Constant.PAGE_SIZE);
        List<News> newsList = null;
        for (int j = 0; j < pages; j++) {
            Map<String, Object> content = new HashMap<>();
            content.put("str", key);
            content.put("pageSize", Constant.PAGE_SIZE);
            content.put("pageRequested", j);
            SoapObject result = DataSetTrans(SoapRequest("SearchNewsByArticle", content));

            if (result != null) {
                int count = result.getPropertyCount();
                newsList = new ArrayList<>(count);
                for (int i = 0; i < count; i++) {
                    SoapObject soap = (SoapObject) result.getProperty(i);
                    News news = new News();
                    news.setId(Integer.parseInt(soap.getProperty("id").toString()));
                    news.setTitle(soap.getProperty("title").toString());
                    try {
                        news.setUpdate_time(dateFormat.parse(soap.getProperty("update_time").toString()));
                    } catch (ParseException e) {
                        try {
                            news.setUpdate_time(dateFormatUnbelievable.parse(soap.getProperty("update_time").toString()));
                        } catch (ParseException e1) {
                            e1.printStackTrace();
                            return null;
                        }
                    }
                    newsList.add(news);
                }
            }
        }
        return newsList;
    }

    /**
     * Search news by title and article.
     * 根据标题和内容搜索新闻
     *
     * @param key the keyword
     * @return the news list
     */
    public static List SearchNewsByTitleAndArticle(String key) {
        if (null == key || key.length() < 1) {
            return null;
        }
        int pages = GetNewsSizeByTitleAndArticle(key, Constant.PAGE_SIZE);
        List<News> newsList = null;
        for (int j = 0; j < pages; j++) {
            Map<String, Object> content = new HashMap<>();
            content.put("str", key);
            content.put("pageSize", Constant.PAGE_SIZE);
            content.put("pageRequested", j);
            SoapObject result = DataSetTrans(SoapRequest("SearchNewsByTitleAndArticle", content));

            if (result != null) {
                int count = result.getPropertyCount();
                newsList = new ArrayList<>(count);
                for (int i = 0; i < count; i++) {
                    SoapObject soap = (SoapObject) result.getProperty(i);
                    News news = new News();
                    news.setId(Integer.parseInt(soap.getProperty("id").toString()));
                    news.setTitle(soap.getProperty("title").toString());
                    try {
                        news.setUpdate_time(dateFormat.parse(soap.getProperty("update_time").toString()));
                    } catch (ParseException e) {
                        try {
                            news.setUpdate_time(dateFormatUnbelievable.parse(soap.getProperty("update_time").toString()));
                        } catch (ParseException e1) {
                            e1.printStackTrace();
                            return null;
                        }
                    }
                    newsList.add(news);
                }
            }
        }
        return newsList;
    }

    /**
     * Get news detail.
     * 获取新闻详情
     *
     * @param newsId the news id
     * @return the news
     */
    public static News GetNewsDetail(int newsId) {
        if (1 > newsId) {
            return null;
        }
        News news = null;
        Map<String, Object> content = new HashMap<>();
        content.put("newsId", newsId);
        SoapObject result = DataSetTrans(SoapRequest("GetNewsDetail", content));

        if (result != null) {
            for (int i = 0; i < result.getPropertyCount(); i++) {
                SoapObject soap = (SoapObject) result.getProperty(i);
                Log.i("soap", soap.toString());
                news = new News();
                news.setId(Integer.parseInt(soap.getProperty("id").toString()));
                news.setCategory_id(Integer.parseInt(soap.getProperty("category_id").toString()));
                news.setTitle(soap.getProperty("title").toString());
                news.setArticle(soap.getProperty("article").toString());
                try {
                    news.setUpdate_time(dateFormat.parse(soap.getProperty("update_time").toString()));
                } catch (ParseException e) {
                    try {
                        news.setUpdate_time(dateFormatUnbelievable.parse(soap.getProperty("update_time").toString()));
                    } catch (ParseException e1) {
                        e1.printStackTrace();
                        return null;
                    }
                }
                news.setPage_view(Integer.parseInt(soap.getProperty("page_view").toString()));
                news.setSupervisor_id(Integer.parseInt(soap.getProperty("supervisor_id").toString()));
                news.setAlias(soap.getProperty("alias").toString());
            }
        }
        return news;
    }

    /**
     * Get category name.
     * 获取小类名称
     *
     * @param categoryId the category id
     * @return the category name
     */
    public static String GetCategoryName(int categoryId) {
        if (1 > categoryId) {
            return null;
        }
        Map<String, Object> content = new HashMap<>();
        content.put("categoryId", categoryId);
        return StringTrans(SoapRequest("GetCategoryName", content));
    }

    /**
     * Get outline name.
     * 获取大类名称
     *
     * @param outlineId the outline id
     * @return the outline name
     */
    public static String GetOutlineName(int outlineId) {
        if (1 > outlineId) {
            return null;
        }
        Map<String, Object> content = new HashMap<>();
        content.put("outlineId", outlineId);
        return StringTrans(SoapRequest("GetOutlineName", content));
    }

    /**
     * Get information name.
     * 获取学院名称
     *
     * @param informationId the information id
     * @return the information name.
     * @deprecated Is it useful?
     */
    public static String GetInformationName(int informationId) {
        if (1 > informationId) {
            return null;
        }
        Map<String, Object> content = new HashMap<>();
        content.put("informationId", informationId);
        return StringTrans(SoapRequest("GetInformationName", content));
    }

    /**
     * Get news size by article.
     * 获取根据内容搜索新闻的页数量
     *
     * @param key      the keyword
     * @param pageSize the page size
     * @return the pages number
     */
    public static int GetNewsSizeByArticle(String key, int pageSize) {
        Map<String, Object> content = new HashMap<>();
        content.put("str", key);
        content.put("pageSize", pageSize);
        return IntTrans(SoapRequest("GetNewsSizeByArticle", content));
    }

    /**
     * Get news size by title.
     * 获取根据标题搜索新闻的页数量
     *
     * @param key      the keyword
     * @param pageSize the page size
     * @return the pages number
     */
    public static int GetNewsSizeByTitle(String key, int pageSize) {
        Map<String, Object> content = new HashMap<>();
        content.put("str", key);
        content.put("pageSize", pageSize);
        return IntTrans(SoapRequest("GetNewsSizeByTitle", content));
    }

    /**
     * Get news size by title and article.
     * 获取根据标题和内容搜索新闻的页数量
     *
     * @param key      the keyword
     * @param pageSize the page size
     * @return the pages number
     */
    public static int GetNewsSizeByTitleAndArticle(String key, int pageSize) {
        Map<String, Object> content = new HashMap<>();
        content.put("str", key);
        content.put("pageSize", pageSize);
        return IntTrans(SoapRequest("GetNewsSizeByTitleAndArticle", content));
    }

    /**
     * Get news page count outline.
     * 获取某一大类按输入的页面大小确定的总页数
     *
     * @param outlineId the outline id
     * @param pageSize  the page size
     * @return the pages number
     */
    public static int GetNewsPageCountOutline(int outlineId, int pageSize) {
        if (1 > outlineId || 1 > pageSize) {
            return 0;
        }
        Map<String, Object> content = new HashMap<>();
        content.put("outlineId", outlineId);
        content.put("pageSize", pageSize);
        return IntTrans(SoapRequest("GetNewsPageCountOutline", content));
    }

    /**
     * Get news page count category.
     * 获取某一小类按输入的页面大小确定的总页数
     *
     * @param categoryId the category id
     * @param pageSize   the page size
     * @return the pages number
     */
    public static int GetNewsPageCountCategory(int categoryId, int pageSize) {
        if (1 > categoryId || 1 > pageSize) {
            return 0;
        }
        Map<String, Object> content = new HashMap<>();
        content.put("categoryId, ", categoryId);
        content.put("pageSize", pageSize);
        return IntTrans(SoapRequest("GetNewsPageCountCategory", content));
    }

    /**
     * Get single outline news list with page number.
     * 获取某一指定大类的指定页码的新闻列表
     *
     * @param outlineId the outline id
     * @return the news list
     */
    public static List GetSingleOutlineNewsListWithPageNumber(int outlineId) {
        if (1 > outlineId) {
            return null;
        }
        int pages = GetNewsPageCountOutline(outlineId, Constant.PAGE_SIZE);
        Log.i("pages", pages + "");
        List<News> newsList = null;
        for (int j = 0; j < pages; j++) {
            Map<String, Object> content = new HashMap<>();
            content.put("outlineId", outlineId);
            content.put("pageSize", Constant.PAGE_SIZE);
            content.put("pageRequested", j);
            Log.i("pageRequested", j + "");
            SoapObject result = DataSetTrans(SoapRequest("GetSingleOutlineNewsListWithPageNumber", content));

            if (result != null) {
                int count = result.getPropertyCount();
                newsList = new ArrayList<>(count);
                for (int i = 0; i < count; i++) {
                    SoapObject soap = (SoapObject) result.getProperty(i);
                    News news = new News();
                    news.setId(Integer.parseInt(soap.getProperty("id").toString()));
                    news.setTitle(soap.getProperty("title").toString());
                    news.setOutline_id(outlineId);
                    try {
                        news.setUpdate_time(dateFormat.parse(soap.getProperty("update_time").toString()));
                    } catch (ParseException e) {
                        try {
                            news.setUpdate_time(dateFormatUnbelievable.parse(soap.getProperty("update_time").toString()));
                        } catch (ParseException e1) {
                            e1.printStackTrace();
                            return null;
                        }
                    }
                    newsList.add(news);
                }
            }
        }
        return newsList;
    }

    /**
     * Get single category news list with page number.
     * 获取某一指定小类的指定页码的新闻列表
     *
     * @param categoryId the category id
     * @return the news list
     */
    public static List GetSingleCategoryNewsListWithPageNumber(int categoryId) {
        if (1 > categoryId) {
            return null;
        }
        int pages = GetNewsPageCountCategory(categoryId, Constant.PAGE_SIZE);
        List<News> newsList = null;
        for (int j = 0; j < pages; j++) {
            Map<String, Object> content = new HashMap<>();
            content.put("categoryId", categoryId);
            content.put("pageSize", Constant.PAGE_SIZE);
            content.put("pageRequested", j);
            SoapObject result = DataSetTrans(SoapRequest("GetSingleCategoryNewsListWithPageNumber", content));

            if (result != null) {
                int count = result.getPropertyCount();
                newsList = new ArrayList<>(count);
                for (int i = 0; i < count; i++) {
                    SoapObject soap = (SoapObject) result.getProperty(i);
                    News news = new News();
                    news.setId(Integer.parseInt(soap.getProperty("id").toString()));
                    news.setTitle(soap.getProperty("title").toString());
                    news.setCategory_id(categoryId);
                    try {
                        news.setUpdate_time(dateFormat.parse(soap.getProperty("update_time").toString()));
                    } catch (ParseException e) {
                        try {
                            news.setUpdate_time(dateFormatUnbelievable.parse(soap.getProperty("update_time").toString()));
                        } catch (ParseException e1) {
                            e1.printStackTrace();
                            return null;
                        }
                    }
                    newsList.add(news);
                }
            }
        }
        return newsList;
    }

    /**
     * Test service
     *
     * @return the service respose SHOULD BE "Hello World"
     */
    public static String test() {
        Map<String, Object> content = new HashMap<>();
        return StringTrans(SoapRequest(Constant.TEST, content));
    }
}
