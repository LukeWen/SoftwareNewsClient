package com.icephone.softwarenewsclient.util;

import com.icephone.softwarenewsclient.service.WebService;
import com.icephone.softwarenewsclient.util.Constant.WebserviceMethod;

import java.util.Map;

/**
 * Created by 温程元 on 2015/4/20.
 */
public class WebServiceThread extends Thread {
    private WebserviceMethod webserviceMethod;
    private Map<String, Object> parameter;

    public WebServiceThread(
            WebserviceMethod outWebserviceMethod, Map<String, Object> outParameter) {
        webserviceMethod = outWebserviceMethod;
        parameter = outParameter;
    }

    @Override
    public void run() {
        switch (webserviceMethod) {
            case SearchNewsByTitle:
                WebService.SearchNewsByTitle(parameter.get("str").toString());
                break;
            case GetNewsDetail:
                WebService.GetNewsDetail(650);
            default:
                break;
        }
    }
}
