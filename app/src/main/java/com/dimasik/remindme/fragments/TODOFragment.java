package com.dimasik.remindme.fragments;

import android.content.Context;
import android.os.Bundle;

import com.dimasik.remindme.R;

public class TODOFragment extends AbstractFragment {

    public static TODOFragment getInstance(Context context)
    {
        TODOFragment fragment = new TODOFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        fragment.setContext(context);
        fragment.setTitle(context.getString(R.string.tab_todo));

        return fragment;
    }

}
