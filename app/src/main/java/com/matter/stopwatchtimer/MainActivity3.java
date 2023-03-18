package com.matter.stopwatchtimer;

import androidx.appcompat.app.AppCompatActivity;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.widget.TextView;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class MainActivity3 extends AppCompatActivity {
    private TextView currentTimeView;
    private TextView textView6;
    private TextView textView9;
    private TextView textView12;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        overridePendingTransition(0, 0);
        setContentView(R.layout.activity_main3);

        currentTimeView = findViewById(R.id.currentTimeView);
        textView6 = findViewById(R.id.textView6);
        textView9 = findViewById(R.id.textView9);
        textView12 = findViewById(R.id.textView12);

        TextView timeZoneView = findViewById(R.id.timeZoneView);
        TextView textView5 = findViewById(R.id.textView5);
        TextView textView8 = findViewById(R.id.textView8);
        TextView textView11 = findViewById(R.id.textView11);

        currentTimeView.setTypeface(null, Typeface.BOLD);

        TextView timerView = findViewById(R.id.timerView);
        TextView stopwatchView = findViewById(R.id.stopwatchView);
        TextView worldClockView = findViewById(R.id.worldClockView);

        stopwatchView.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity3.this, MainActivity2.class);
            startActivity(intent);
        });

        timerView.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity3.this, MainActivity.class);
            startActivity(intent);
        });

        worldClockView.setPaintFlags(worldClockView.getPaintFlags() |
                Paint.UNDERLINE_TEXT_FLAG | Paint.FAKE_BOLD_TEXT_FLAG);

        timeZoneView.setText(Calendar.getInstance().getTimeZone().getDisplayName());

        @SuppressLint("SimpleDateFormat") SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
        Handler handler = new Handler();
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                String currentTime = dateFormat.format(new Date());
                currentTimeView.setText(currentTime);
                handler.postDelayed(this, 1000);
            }
        };

        textView5.setText(TimezoneDifference.getDifference("Europe/London"));
        Runnable runnable2 = new Runnable() {
            @Override
            public void run() {
                @SuppressLint("SimpleDateFormat") SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
                sdf.setTimeZone(TimeZone.getTimeZone("Europe/London"));
                textView6.setText(sdf.format(new Date()));
                handler.postDelayed(this, 1000);
            }
        };

        textView8.setText(TimezoneDifference.getDifference("Asia/Tokyo"));
        Runnable runnable3 = new Runnable() {
            @Override
            public void run() {
                @SuppressLint("SimpleDateFormat") SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
                sdf.setTimeZone(TimeZone.getTimeZone("Asia/Tokyo"));
                textView9.setText(sdf.format(new Date()));
                handler.postDelayed(this, 1000);
            }
        };

        textView11.setText(TimezoneDifference.getDifference("America/New_York"));
        Runnable runnable4 = new Runnable() {
            @Override
            public void run() {
                @SuppressLint("SimpleDateFormat") SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
                sdf.setTimeZone(TimeZone.getTimeZone("America/New_York"));
                textView12.setText(sdf.format(new Date()));
                handler.postDelayed(this, 1000);
            }
        };

        handler.post(runnable);
        handler.post(runnable2);
        handler.post(runnable3);
        handler.post(runnable4);
    }
}