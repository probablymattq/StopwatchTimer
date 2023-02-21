package com.matter.stopwatchtimer;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity2 extends AppCompatActivity {
    private TextView mMinutesDisplay;
    private TextView mSecondsDisplay;
    private Button mStartPauseButton;
    private Button mResetButton;

    private CountDownTimer mCountDownTimer;
    private boolean mTimerRunning;
    private long mTimeElapsed;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        overridePendingTransition(0, 0);
        setContentView(R.layout.activity_main2);


        mMinutesDisplay = findViewById(R.id.minutes_display);
        mSecondsDisplay = findViewById(R.id.seconds_display);
        mStartPauseButton = findViewById(R.id.start_stop_button);
        mResetButton = findViewById(R.id.reset_button);

        mMinutesDisplay.setText("00");
        mSecondsDisplay.setText("00");

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

        mStartPauseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mTimerRunning) {
                    pauseTimer();
                } else {
                    startTimer();
                }
            }
        });

        mResetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetTimer();
            }
        });
    }

    @SuppressLint("SetTextI18n")
    private void startTimer() {
        mCountDownTimer = new CountDownTimer(Long.MAX_VALUE, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                mTimeElapsed += 1000;
                updateDisplay();
            }

            @Override
            public void onFinish() {
                // Do nothing
            }
        }.start();

        mTimerRunning = true;
        mStartPauseButton.setText("Pause");
    }

    @SuppressLint("SetTextI18n")
    private void pauseTimer() {
        mCountDownTimer.cancel();
        mTimerRunning = false;
        mStartPauseButton.setText("Resume");
    }

    @SuppressLint("SetTextI18n")
    private void resetTimer() {
        if(mTimerRunning || mTimeElapsed > 0) {
            mCountDownTimer.cancel();
            mTimeElapsed = 0;
            updateDisplay();
            mTimerRunning = false;
            mStartPauseButton.setText("Start");
        }
    }

    @SuppressLint("DefaultLocale")
    private void updateDisplay() {
        int minutes = (int) (mTimeElapsed / 1000) / 60;
        int seconds = (int) (mTimeElapsed / 1000) % 60;

        mMinutesDisplay.setText(String.format("%02d", minutes));
        mSecondsDisplay.setText(String.format("%02d", seconds));
    }
}