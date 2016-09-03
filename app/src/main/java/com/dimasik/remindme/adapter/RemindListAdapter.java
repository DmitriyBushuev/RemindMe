package com.dimasik.remindme.adapter;


import android.database.Cursor;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

import com.dimasik.remindme.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class RemindListAdapter extends RecyclerView.Adapter<RemindListAdapter.RemindViewHolder> {

    // private List<String> listItems;
    private Cursor cursor;

    public RemindListAdapter(Cursor cursor) {
        //listItems = getListWithData(cursor);
        this.cursor = cursor;
    }

//    private List<String> getListWithData(Cursor cursor)
//    {
//        ArrayList<String> list = new ArrayList<>();
//        if(cursor.moveToNext())
//        {
//            while (cursor.moveToNext())
//            {
//                long date = cursor.getLong(cursor.getColumnIndex("date"));
//
//                SimpleDateFormat sp = new SimpleDateFormat("HH : mm");
//                Log.d("qwe",sp.format(date));
//                list.add(sp.format(date));
//            }
//        }else Log.d("qwe","Null cursor");
//        return list;
//    }

    @Override
    public RemindViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_layout, parent, false);
        return new RemindViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RemindViewHolder holder, int position) {
        //holder.title.setText(listItems.get(position));
        if (cursor.moveToFirst()) {
            cursor.moveToPosition(position);
            holder.title.setText(cursor.getString(cursor.getColumnIndex("title")));
            holder.description.setText(cursor.getString(cursor.getColumnIndex("descr")));
            long date = cursor.getLong(cursor.getColumnIndex("date"));
            holder.date.setText(getNormalDate(date));
            holder.type.setText(cursor.getString(cursor.getColumnIndex("type")));

        }
    }

    @Override
    public int getItemCount() {
        return cursor.getCount();
    }

    public static class RemindViewHolder extends RecyclerView.ViewHolder {

        TextView title;
        TextView description;
        TextView date;
        TextView type;

        public RemindViewHolder(View itemView) {
            super(itemView);

            title = (TextView) itemView.findViewById(R.id.notification_title);
            description = (TextView) itemView.findViewById(R.id.notification_description);
            date = (TextView) itemView.findViewById(R.id.notification_date);
            type = (TextView) itemView.findViewById(R.id.notification_type);
        }
    }

    public void swapData(Cursor cursor) {
//        if(listItems!=null)
//        {
//            listItems.clear();
//            listItems = getListWithData(cursor);
//        }
//        else
//            listItems = getListWithData(cursor);
        if (this.cursor != null) {
            this.cursor.close();
            this.cursor = cursor;
        } else
            this.cursor = cursor;

        notifyDataSetChanged();
    }

    private String getNormalDate(long date) {
        SimpleDateFormat sp = new SimpleDateFormat("HH:mm");
        return sp.format(new Date(date));
    }

}
