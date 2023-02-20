package com.matter.stopwatchtimer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        overridePendingTransition(0, 0);
        setContentView(R.layout.activity_main2);

        TextView timerTextView = findViewById(R.id.timerView);
        TextView stopwatchTextView = findViewById(R.id.stopwatchView);

        timerTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity2.this, MainActivity.class);
                startActivity(intent);
            }
        });

        stopwatchTextView.setPaintFlags(stopwatchTextView.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
    }
}