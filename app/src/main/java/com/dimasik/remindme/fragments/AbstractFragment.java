package com.dimasik.remindme.fragments;


import android.content.Context;
import android.support.v4.app.Fragment;

public abstract class AbstractFragment extends Fragment {

    String title;
    Context context;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title){
        this.title = title;
    }
}
