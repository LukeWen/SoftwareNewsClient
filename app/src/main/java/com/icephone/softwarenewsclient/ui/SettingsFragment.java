package com.icephone.softwarenewsclient.ui;

import android.os.Bundle;
import android.preference.PreferenceFragment;

import com.icephone.softwarenewsclient.R;

public class SettingsFragment extends PreferenceFragment {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.settings);
    }
}
