package com.dimasik.remindme.fragments;

import android.content.Context;
import android.os.Bundle;

import com.dimasik.remindme.R;

public class HistoryFragment extends AbstractFragment {

    public static HistoryFragment getInstance(Context context) {
        HistoryFragment fragment = new HistoryFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        fragment.setContext(context);
        fragment.setTitle(context.getString(R.string.tab_history));
        return fragment;
    }

}
