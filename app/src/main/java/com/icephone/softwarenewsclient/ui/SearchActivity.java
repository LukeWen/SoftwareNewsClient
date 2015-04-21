package com.icephone.softwarenewsclient.ui;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v4.widget.SimpleCursorAdapter;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.Menu;
import android.widget.ListView;
import android.widget.Toast;

import com.icephone.softwarenewsclient.R;
import com.icephone.softwarenewsclient.util.Constant;
import com.icephone.softwarenewsclient.util.WebServiceThread;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by 温程元 on 2015/4/8.
 */
public class SearchActivity extends ActionBarActivity {
    private SearchView mSearchView;
    private ListView mListView;
    private SimpleCursorAdapter mAdapter;
    private Cursor mCursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        final ActionBar actionBar = getSupportActionBar();
        actionBar.show();
        mAdapter = new SimpleCursorAdapter(this,
                android.R.layout.simple_list_item_1, mCursor,
                new String[]{ContactsContract.RawContacts.DISPLAY_NAME_PRIMARY}, new int[]{android.R.id.text1},
                0);
        mListView = (ListView) findViewById(android.R.id.list);
        mListView.setAdapter(mAdapter);

        mSearchView = (SearchView) findViewById(R.id.search_view);
        mSearchView.setIconifiedByDefault(true);
        mSearchView.onActionViewExpanded();
        mSearchView.setFocusable(false);
        mSearchView.clearFocus();
//      mSearchView.setIconifiedByDefault(true);
        mSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextChange(String queryText) {
                showInfo("Textting:" + queryText);
                if (queryText != null && queryText.length() > 0) {
                    Map<String, Object> content = new HashMap<>();
                    content.put("key", queryText);
                    WebServiceThread webServiceThread =
                            new WebServiceThread(Constant.WebserviceMethod.SearchNewsByTitle, content, SearchActivity.this);
                    webServiceThread.start();
                }
                return true;
            }

            @Override
            public boolean onQueryTextSubmit(String queryText) {
                if (mSearchView != null) {
                    showInfo("Submit:" + queryText);
                }
                return true;
            }
        });

        showInfo("onCreate() is called");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_search, menu);
//        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
//        MenuItem searchItem = menu.findItem(R.id.action_search_view);
//        mSearchView = (SearchView) MenuItemCompat.getActionView(searchItem);
//
//        mSearchView.setSearchableInfo(searchManager
//                .getSearchableInfo(getComponentName()));
//
//        mSearchView.setBaselineAligned(false);
//        mSearchView=(SearchView) menu.findItem(R.id.action_search_view).getActionView();
//        mSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
//            @Override
//            public boolean onQueryTextSubmit(String string) {
//                Toast.makeText(SearchActivity.this, "查询:" + string, Toast.LENGTH_SHORT).show();
//                tv.setText(string);
//                return false;
//            }
//
//            @Override
//            public boolean onQueryTextChange(String string) {
//                return true;
//            }
//        });

        return true;
    }


    @Override
    protected void onNewIntent(Intent intent) {
        // TODO Auto-generated method stub
        showInfo("onNewIntent() is called");
        super.onNewIntent(intent);
    }


    private void showInfo(String s) {
        Toast.makeText(this, s, Toast.LENGTH_SHORT).show();
        Log.d(getLocalClassName(), s);
    }
}
