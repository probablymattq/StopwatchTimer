package com.matter.stopwatchtimer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity2 extends AppCompatActivity {
    private TextView mMinutesDisplay;
    private TextView mSecondsDisplay;
    private TextView mMiliSecondsDisplay;
    private Button mStartPauseButton;

    private CountDownTimer mCountDownTimer;
    private boolean mTimerRunning;
    private long mTimeElapsed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        overridePendingTransition(0, 0);
        setContentView(R.layout.activity_main2);

        TextView timerTextView = findViewById(R.id.timerView);
        TextView stopwatchTextView = findViewById(R.id.stopwatchView);
        TextView worldClockTextView = findViewById(R.id.worldClockView);

        mMinutesDisplay = findViewById(R.id.minutes_display);
        mSecondsDisplay = findViewById(R.id.seconds_display);
        mMiliSecondsDisplay = findViewById(R.id.mseconds_display);
        mStartPauseButton = findViewById(R.id.start_stop_button);
        Button mResetButton = findViewById(R.id.reset_button);

        timerTextView.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity2.this, MainActivity.class);
            startActivity(intent);
        });

        worldClockTextView.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity2.this, MainActivity3.class);
            startActivity(intent);
        });

        stopwatchTextView.setPaintFlags(stopwatchTextView.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG | Paint.FAKE_BOLD_TEXT_FLAG);

        mStartPauseButton.setOnClickListener(v -> {
            if (mTimerRunning) pauseTimer();
            else startTimer();
        });

        mResetButton.setOnClickListener(v -> resetTimer());
    }

    private void startTimer() {
        mCountDownTimer = new CountDownTimer(Long.MAX_VALUE, 1) {
            @Override
            public void onTick(long millisUntilFinished) {
                mTimeElapsed += 10;
                updateDisplay();
            }

            @Override
            public void onFinish() {
            }
        }.start();

        mTimerRunning = true;
        mStartPauseButton.setText("Pause");
    }

    private void pauseTimer() {
        mCountDownTimer.cancel();
        mTimerRunning = false;
        mStartPauseButton.setText("Resume");
    }

    private void resetTimer() {
        if (mTimerRunning || mTimeElapsed > 0) {
            mCountDownTimer.cancel();
            mTimeElapsed = 0;
            updateDisplay();
            mTimerRunning = false;
            mStartPauseButton.setText("Start");
        }
    }

    private void updateDisplay() {
        int minutes = (int) (mTimeElapsed / 1000) / 60;
        int seconds = (int) (mTimeElapsed / 1000) % 60;
        int mseconds = (int) (mTimeElapsed % 1000) / 10;

        mMinutesDisplay.setText(String.format("%02d", minutes));
        mSecondsDisplay.setText(String.format("%02d", seconds));
        mMiliSecondsDisplay.setText(String.format("%02d", mseconds));
    }
}