package com.dimasik.remindme.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.dimasik.remindme.fragments.AbstractFragment;
import com.dimasik.remindme.fragments.BirthdaysFragment;
import com.dimasik.remindme.fragments.HistoryFragment;
import com.dimasik.remindme.fragments.IdeasFragment;
import com.dimasik.remindme.fragments.TODOFragment;

import java.util.HashMap;
import java.util.Map;


public class TabPagerFragmentAdapter extends FragmentPagerAdapter {

    private Map<Integer,AbstractFragment> tabs;
    private Context context;

    public TabPagerFragmentAdapter(Context context, FragmentManager fm) {
        super(fm);
        this.context = context;
        tabs = new HashMap<>();
        tabs.put(0, HistoryFragment.getInstance(context));
        tabs.put(1, IdeasFragment.getInstance(context));
        tabs.put(2, TODOFragment.getInstance(context));
        tabs.put(3, BirthdaysFragment.getInstance(context));
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return  tabs.get(position).getTitle();
    }

    @Override
    public Fragment getItem(int position) {

       if(!tabs.containsKey(position))
           throw new IllegalArgumentException("Something wrong with tab's number!");

        return tabs.get(position);
    }

    @Override
    public int getCount() {
        return tabs.size();
    }
}
