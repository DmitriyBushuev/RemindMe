package com.dimasik.remindme;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;

import com.dimasik.remindme.DB.DBHelper;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class AddingActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText title;
    private EditText description;
    private DatePicker date;
    private TimePicker time;
    private Button save;
    private Button cancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        DisplayMetrics dm = getResources().getDisplayMetrics();
        int screenWidth = (int) (dm.widthPixels * 0.8);
        super.setContentView(R.layout.add_notification_layout);
        getWindow().setLayout(screenWidth, ActionBar.LayoutParams.WRAP_CONTENT);

        initViews();
        save.setOnClickListener(this);
        cancel.setOnClickListener(this);
    }

    private void initViews() {
        title = (EditText) findViewById(R.id.add_title);
        description = (EditText) findViewById(R.id.add_desc);
        date = (DatePicker) findViewById(R.id.add_date);
        time = (TimePicker) findViewById(R.id.add_time);
        time.setIs24HourView(true);
        save = (Button) findViewById(R.id.btnSave);
        cancel = (Button) findViewById(R.id.btnCancel);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnSave:
                DBHelper dbHelper = new DBHelper(this);
                SQLiteDatabase database = dbHelper.getWritableDatabase();
                ContentValues cv = new ContentValues();
                cv.put("title", title.getText().toString());
                cv.put("descr", description.getText().toString());
                cv.put("date", getFullNotificationTime());
                cv.put("type", getIntent().getStringExtra("type"));
                database.insert("notifications",null,cv);
                database.close();
                dbHelper.close();
                finish();
                break;
            case R.id.btnCancel:
                finish();
                break;
            default:
                break;
        }
    }

    private long getFullNotificationTime() {

        long fullDateLong = 0L;
        long fullTimeLong = 0L;

        int month = date.getMonth();
        int day = date.getDayOfMonth();
        int year = date.getYear();
        String fullDateStr = month + "." + day + "." + year;

        int hour;
        int minute;

        if (Build.VERSION.SDK_INT >= 23) {
            hour = time.getHour();
            minute = time.getMinute();
        } else {
            hour = time.getCurrentHour();
            minute = time.getCurrentMinute();
        }

        String fullTimeStr = hour + ":" + minute;


        try {
            SimpleDateFormat sp = new SimpleDateFormat("MM.dd.yyyy");
            fullDateLong = sp.parse(fullDateStr).getTime();

            sp = new SimpleDateFormat("HH:mm");
            fullTimeLong = sp.parse(fullTimeStr).getTime();
        } catch (ParseException e) {
            Log.d("qwe", e.getMessage());
        }
        Log.d("qwe",String.valueOf(fullDateLong + fullTimeLong));

        return fullTimeLong + fullDateLong;
    }
}
