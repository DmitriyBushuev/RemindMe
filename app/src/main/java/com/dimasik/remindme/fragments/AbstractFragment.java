package com.dimasik.remindme.fragments;


import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dimasik.remindme.AddingActivity;
import com.dimasik.remindme.DB.DBHelper;
import com.dimasik.remindme.R;
import com.dimasik.remindme.adapter.RemindListAdapter;

public abstract class AbstractFragment extends Fragment {

    RemindListAdapter adapter;
    RecyclerView recyclerView;
    FloatingActionButton fab;
    Cursor cursor;
    String title;
    Context context;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_layout, container, false);

        recyclerView = (RecyclerView) rootView.findViewById(R.id.recyclerView);

        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        cursor = getCursor();
        adapter = new RemindListAdapter(cursor);
        recyclerView.setAdapter(adapter);

        fab = (FloatingActionButton) getActivity().findViewById(R.id.floating_button);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), AddingActivity.class);
                intent.putExtra("type",getTitle());
                startActivity(intent);
            }
        });

        return rootView;
    }

    public Cursor getCursor() {

        DBHelper helper = new DBHelper(getContext());
        SQLiteDatabase db = helper.getWritableDatabase();
        String selection = "type = ?";
        String[] selectionArgs = new String[]{getTitle()};
        Cursor data = db.query("notifications", null, selection, selectionArgs, null, null, null);

        return data;
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d("qwe", "onResume");
        adapter.swapData(getCursor());
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title){
        this.title = title;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }
}
