package com.icephone.softwarenewsclient.ui;

import android.app.SearchManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.widget.TextView;

import com.icephone.softwarenewsclient.R;

/**
 * Created by 温程元 on 2015/4/8.
 */
public class SearchResultActvity extends ActionBarActivity {
    private TextView tv = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub

        super.onCreate(savedInstanceState);
        setContentView(R.layout.basic_test);
        tv = (TextView) findViewById(R.id.textViewId);
        tv.setText("");

        showInfo("onCreate() is called");
        doSearchQuery(getIntent());
    }


    @Override
    protected void onNewIntent(Intent intent) {
        // TODO Auto-generated method stub
        showInfo("onNewIntent() is called");
        super.onNewIntent(intent);
        doSearchQuery(intent);
    }


    private void doSearchQuery(Intent intent) {
        showInfo(" doSearchQuery() is called");
        if (intent == null)
            return;

        String queryAction = intent.getAction();
        if (Intent.ACTION_SEARCH.equals(queryAction)) {
            String queryString = intent.getStringExtra(SearchManager.QUERY);
            showInfo("搜索内容：" + queryString);
        }
    }

    private void showInfo(String s) {
        tv.setText(s + "\n" + tv.getText());
        Log.d(getLocalClassName(), s);
    }
}
