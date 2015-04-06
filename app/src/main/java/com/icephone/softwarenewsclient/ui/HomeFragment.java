package com.icephone.softwarenewsclient.ui;

import android.os.Handler;
import android.os.Parcelable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.icephone.softwarenewsclient.R;
import com.icephone.softwarenewsclient.util.Constant;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Created by 温程元 on 2015/4/6.
 */
public class HomeFragment extends Fragment implements ViewPager.OnPageChangeListener {
    private ViewPager viewPager; // android-support-v4中的滑动组件
    private List<ImageView> imageViews; // 滑动的图片集合

    private String[] titles; // 图片标题
    private int[] imageResId; // 图片ID
    private List<View> dots; // 图片标题正文的那些点

    private TextView tv_title;
    private int currentItem = 0; // 当前图片的索引号
    // An ExecutorService that can schedule commands to run after a given delay,
    // or to execute periodically.
    private ScheduledExecutorService scheduledExecutorService;

    // 切换当前显示的图片
    private Handler handler = new Handler() {
        public void handleMessage(android.os.Message msg) {
            viewPager.setCurrentItem(currentItem);// 切换当前显示的图片
        };
    };
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        imageResId = new int[] { R.mipmap.h1, R.mipmap.h2, R.mipmap.h3, R.mipmap.h4, R.mipmap.h5 };
        titles = new String[imageResId.length];
        titles[0] = "标题1";
        titles[1] = "标题2";
        titles[2] = "标题3";
        titles[3] = "标题4";
        titles[4] = "标题5";

        imageViews = new ArrayList<>();

        // 初始化图片资源
        for (int i = 0; i < imageResId.length; i++) {
            ImageView imageView = new ImageView(getActivity());
            imageView.setImageResource(imageResId[i]);
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageViews.add(imageView);
        }


        dots = new ArrayList<>();
        dots.add(getView().findViewById(R.id.v_dot0));
        dots.add(getView().findViewById(R.id.v_dot1));
        dots.add(getView().findViewById(R.id.v_dot2));
        dots.add(getView().findViewById(R.id.v_dot3));
        dots.add(getView().findViewById(R.id.v_dot4));

        tv_title = (TextView) getView().findViewById(R.id.tv_title);
        tv_title.setText(titles[0]);

        viewPager = (ViewPager) getView().findViewById(R.id.vp);
        viewPager.setAdapter(new MyAdapter());// 设置填充ViewPager页面的适配器
        // 设置一个监听器，当ViewPager中的页面改变时调用
        viewPager.setOnPageChangeListener(new MyPageChangeListener());
        viewPager.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                int action = event.getAction();
                switch (action)
                {
                    case MotionEvent.ACTION_DOWN:
                        scheduledExecutorService.shutdown();
                        break;
                    case MotionEvent.ACTION_CANCEL:
                    case MotionEvent.ACTION_UP:
                        scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
                        // 当Activity显示出来后，每3秒钟切换一次图片显示
                        scheduledExecutorService.scheduleAtFixedRate(
                                new ScrollTask(),
                                Constant.INITIAL_DELAY,
                                Constant.PERIOD,
                                Constant.UNIT);
                        break;

                }
                return false;
            }

        });
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);
    }


    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int arg) {

    }


    @Override
    public void onPageScrollStateChanged(int state) {

    }
    @Override
    public void onStart() {
        scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
        // 当Activity显示出来后，每3秒钟切换一次图片显示
        scheduledExecutorService.scheduleAtFixedRate(
                new ScrollTask(),
                Constant.INITIAL_DELAY,
                Constant.PERIOD,
                Constant.UNIT);
        super.onStart();
    }

    @Override
    public void onStop() {
        // 当Activity不可见的时候停止切换
        scheduledExecutorService.shutdown();
        super.onStop();
    }
    /**
     * 换行切换任务
     *
     * @author Administrator
     *
     */
    private class ScrollTask implements Runnable {

        public void run() {
            synchronized (viewPager) {
//                System.out.println("currentItem: " + currentItem);
                currentItem = (currentItem + 1) % imageViews.size();
                handler.obtainMessage().sendToTarget(); // 通过Handler切换图片
            }
        }

    }
    /**
     * 当ViewPager中页面的状态发生改变时调用
     *
     * @author Administrator
     *
     */
    private class MyPageChangeListener implements ViewPager.OnPageChangeListener {
        private int oldPosition = 0;

        /**
         * This method will be invoked when a new page becomes selected.
         * position: Position index of the new selected page.
         */
        public void onPageSelected(int position) {
            currentItem = position;
            tv_title.setText(titles[position]);
            dots.get(oldPosition).setBackgroundResource(R.drawable.dot_normal);
            dots.get(position).setBackgroundResource(R.drawable.dot_focused);
            oldPosition = position;
        }

        public void onPageScrollStateChanged(int arg0) {

        }

        public void onPageScrolled(int arg0, float arg1, int arg2) {

        }
    }

    /**
     * 填充ViewPager页面的适配器
     *
     * @author Administrator
     *
     */
    private class MyAdapter extends PagerAdapter {

        @Override
        public int getCount() {
            return imageResId.length;
        }

        @Override
        public Object instantiateItem(View arg0, int arg1) {
            ((ViewPager) arg0).addView(imageViews.get(arg1));
            return imageViews.get(arg1);
        }

        @Override
        public void destroyItem(View arg0, int arg1, Object arg2) {
            ((ViewPager) arg0).removeView((View) arg2);
        }

        @Override
        public boolean isViewFromObject(View arg0, Object arg1) {
            return arg0 == arg1;
        }

        @Override
        public void restoreState(Parcelable arg0, ClassLoader arg1) {

        }

        @Override
        public Parcelable saveState() {
            return null;
        }

        @Override
        public void startUpdate(View arg0) {

        }

        @Override
        public void finishUpdate(View arg0) {

        }
    }
}
