package com.example.eason.yikatong.Adapter;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by Eason on 2018/1/1.
 */

public class mnews_Main4_ViewPager extends PagerAdapter {
    List<View> viewList;

    inews_Main4_SetEvent event;

    public mnews_Main4_ViewPager(List<View> viewList, inews_Main4_SetEvent event) {
        this.viewList = viewList;
        this.event = event;
    }

    @Override
    public int getCount() {
        return viewList.size();
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View view = viewList.get(position);
        event.main4_setView(view, position);
        container.addView(view);
        return view;
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
