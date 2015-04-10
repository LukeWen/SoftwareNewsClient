package com.icephone.softwarenewsclient.ui;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.icephone.softwarenewsclient.R;
import com.icephone.softwarenewsclient.util.Constant;


public class MainActivity extends ActionBarActivity implements ActionBar.TabListener, SearchView.OnQueryTextListener {

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    ViewPager mViewPager;
    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    private SectionsPagerAdapter mSectionsPagerAdapter;
    private Fragment fragmentHome;
    private Fragment fragmentNews;
    private Fragment fragmentNotice;
    private Fragment fragmentRelated;
    private NetWorkReceiver netWorkReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//         Set up the action bar.
        final ActionBar actionBar = getSupportActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.pager);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        // When swiping between different sections, select the corresponding
        // tab. We can also use ActionBar.Tab#select() to do this if we have
        // a reference to the Tab.
        mViewPager.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                actionBar.setSelectedNavigationItem(position);
            }
        });

        // For each of the sections in the app, add a tab to the action bar.
        for (int i = 0; i < mSectionsPagerAdapter.getCount(); i++) {
            // Create a tab with text corresponding to the page title defined by
            // the adapter. Also specify this Activity object, which implements
            // the TabListener interface, as the callback (listener) for when
            // this tab is selected.
            actionBar.addTab(
                    actionBar.newTab()
                            .setText(mSectionsPagerAdapter.getPageTitle(i))
                            .setTabListener(this));
        }
        netWorkReceiver = new NetWorkReceiver();


        registerReceiver(netWorkReceiver,
                new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));
    }

    @Override
    protected void onDestroy() {
        unregisterReceiver(netWorkReceiver);
        super.onDestroy();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        MenuItem actionDisconnectting = menu.findItem(R.id.action_disconnecting);
        MenuItem actionRefresh = menu.findItem(R.id.action_refresh);
        Constant.IS_NETWORK_WORKING = Constant.isNetworkAvailable(this);
        if (Constant.IS_NETWORK_WORKING) {
            actionDisconnectting.setVisible(false);
            actionRefresh.setVisible(true);
        }else{
            actionDisconnectting.setVisible(true);
            actionRefresh.setVisible(false);
        }
        // 获取SearchView对象
//        SearchView searchView = (SearchView) menu.findItem(R.id.action_search).getActionView();
//        if(searchView == null){
//            Log.e("SearchView","Fail to get Search View.");
//            return true;
//        }
//        searchView.setIconifiedByDefault(true); // 缺省值就是true，可能不专门进行设置，false和true的效果图如下，true的输入框更大
//        searchView.setOnQueryTextListener(this);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        switch(id)
        {
            case R.id.action_settings:
                Intent i=new Intent();
                i.setClass(this,SettingsActivity.class);
                startActivity(i);
                return true;
            case R.id.action_disconnecting:
                Toast.makeText(this,getResources().getString(R.string.disconnecting),Toast.LENGTH_SHORT).show();
                return true;
            case R.id.action_refresh:
                Toast.makeText(this,getResources().getString(R.string.refresh),Toast.LENGTH_SHORT).show();
                return true;
            case R.id.action_search:
                Intent intent = new Intent();
                intent.setClass(this, SearchActivity.class);
                startActivity(intent);
                Toast.makeText(this, getResources().getString(R.string.action_search), Toast.LENGTH_SHORT).show();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onTabSelected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
        // When the given tab is selected, switch to the corresponding page in
        // the ViewPager.
        mViewPager.setCurrentItem(tab.getPosition());
    }

    @Override
    public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
    }

    @Override
    public void onTabReselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
    }

    private void refreshActionBar() {
        Constant.serviceAvailableTest(this);
        super.supportInvalidateOptionsMenu();
    }

    @Override
    public boolean onQueryTextSubmit(String s) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String s) {
        return false;
    }

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
           // return PlaceholderFragment.newInstance(position + 1);
            switch (position) {
                case 0:
                    fragmentHome = new HomeFragment();
                    return fragmentHome;
                case 1:
                    fragmentNews = new NewsFragment();
                    return fragmentNews;
                case 2:
                    fragmentNotice = new NoticeFragment();
                    return fragmentNotice;
                case 3:
                    fragmentRelated = new RelatedFragment();
                    return fragmentRelated;
            }
            return null;
        }

        @Override
        public int getCount() {
            // Show 4 total pages.
            return Constant.FRAGMENT_NUMBER_OF_MAIN;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return getString(R.string.title_section1);
                case 1:
                    return getString(R.string.title_section2);
                case 2:
                    return getString(R.string.title_section3);
                case 3:
                    return getString(R.string.title_section4);
            }
            return null;
        }
    }

    public class NetWorkReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {


            Constant.IS_NETWORK_WORKING = Constant.isNetworkAvailable(getApplication());
            if (!Constant.IS_NETWORK_WORKING) {
                Toast.makeText(getApplication(), "网络连接已断开，当前为离线模式！", Toast.LENGTH_SHORT).show();
            }

            refreshActionBar();
        }
    }
}
