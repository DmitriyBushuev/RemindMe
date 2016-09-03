package com.dimasik.remindme.fragments;

import android.content.Context;
import android.os.Bundle;

import com.dimasik.remindme.R;

public class BirthdaysFragment extends AbstractFragment {

    public static BirthdaysFragment getInstance(Context context)
    {
        BirthdaysFragment fragment = new BirthdaysFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        fragment.setContext(context);
        fragment.setTitle(context.getString(R.string.tab_birthdays));
        return fragment;
    }

}
