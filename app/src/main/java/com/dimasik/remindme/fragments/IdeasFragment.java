package com.dimasik.remindme.fragments;

import android.content.Context;
import android.os.Bundle;

import com.dimasik.remindme.R;

public class IdeasFragment extends AbstractFragment {

    public static IdeasFragment getInstance(Context context) {
        IdeasFragment fragment = new IdeasFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        fragment.setContext(context);
        fragment.setTitle(context.getString(R.string.tab_ideas));
        return fragment;
    }

    @Override
    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
