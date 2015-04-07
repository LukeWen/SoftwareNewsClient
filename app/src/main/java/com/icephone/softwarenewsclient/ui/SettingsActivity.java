package com.icephone.softwarenewsclient.ui;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;

/**
 * Created by 温程元 on 2015/4/7.
 */
public class SettingsActivity extends ActionBarActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getFragmentManager().beginTransaction().replace(android.R.id.content, new SettingsFragment()).commit();
        //setContentView(R.layout.activity_settings);
    }
}
