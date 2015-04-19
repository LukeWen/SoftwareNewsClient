package com.icephone.softwarenewsclient.util;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.icephone.softwarenewsclient.service.WebService;

/**
 * Created by 温程元 on 2015/4/19.
 */
public class ServiceAvailableTestThread extends Thread {
    private Context context;

    public ServiceAvailableTestThread(Context context) {
        super();
        this.context = context;
    }

    @Override
    public void run() {
        String test = WebService.test();
        Intent intent = new Intent();
        intent.setAction(Constant.SERVICE_STATE);
        if (test != null && test.equals("Hello World")) {
            Log.i("ServiceState", "Service Available");
            Constant.IS_SERVICE_WORKING = true;
            intent.putExtra("ServiceState", true);
        } else {
            Log.i("ServiceState", "Service Unavailable");
            Constant.IS_SERVICE_WORKING = false;
            intent.putExtra("ServiceState", false);
        }
        this.context.sendBroadcast(intent);
    }
}
