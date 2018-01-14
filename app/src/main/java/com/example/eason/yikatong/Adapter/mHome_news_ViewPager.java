package com.example.eason.yikatong.Adapter;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by Eason on 2017/12/30.
 */

public class mHome_news_ViewPager extends PagerAdapter {
    List<View> list;

    inews_SetEvent event;

    public mHome_news_ViewPager(List<View> list, inews_SetEvent event) {
        this.list = list;
        this.event = event;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View v = list.get(position);
        event.newsSetEvent(v, position);
        container.addView(v);
        return v;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }
}
