package com.matter.stopwatchtimer;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Interpolator;
import android.view.animation.LinearInterpolator;
import android.widget.Button;
import android.widget.NumberPicker;
import android.widget.TextView;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private NumberPicker hoursPicker;
    private NumberPicker minutesPicker;
    private NumberPicker secondsPicker;
    private TextView countdownTextView;
    private RoundProgressBar countdownProgressBar;
    private Button startButton;
    private Button pauseButton;
    private Button resetButton;

    private long startTimeInMillis;

    private CountDownTimer countDownTimer;
    private boolean timerRunning;

    private long timeLeftInMillis;
    private int progress;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        overridePendingTransition(0, 0);
        setContentView(R.layout.activity_main);

        hoursPicker = findViewById(R.id.hoursPicker);
        minutesPicker = findViewById(R.id.minutesPicker);
        secondsPicker = findViewById(R.id.secondsPicker);
        countdownTextView = findViewById(R.id.countdownTextView);
        countdownProgressBar = findViewById(R.id.countdownProgressBar);
        startButton = findViewById(R.id.startButton);
        pauseButton = findViewById(R.id.pauseButton);
        resetButton = findViewById(R.id.resetButton);

        countdownProgressBar.setVisibility(View.INVISIBLE);
        countdownTextView.setVisibility(View.INVISIBLE);

        hoursPicker.setMinValue(0);
        hoursPicker.setMaxValue(24);

        minutesPicker.setMinValue(0);
        minutesPicker.setMaxValue(60);

        secondsPicker.setMinValue(0);
        secondsPicker.setMaxValue(60);


        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startTimer();
                countdownProgressBar.setVisibility(View.VISIBLE);
                countdownTextView.setVisibility(View.VISIBLE);
                hoursPicker.setVisibility(View.INVISIBLE);
                minutesPicker.setVisibility(View.INVISIBLE);
                secondsPicker.setVisibility(View.INVISIBLE);
                pauseButton.setVisibility(View.VISIBLE);
                resetButton.setVisibility(View.VISIBLE);

            }
        });

        pauseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(timerRunning) {
                    pauseTimer();
                } else {
                    resumeTimer();
                }
            }
        });

        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetTimer();
            }
        });


        TextView timerView = findViewById(R.id.TimerView);
        TextView stopwatchView = findViewById(R.id.StopwatchView);

        stopwatchView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MainActivity2.class);
                startActivity(intent);
            }
        });

        timerView.setPaintFlags(timerView.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
    }

    private void startTimer() {
        int hours = hoursPicker.getValue();
        int minutes = minutesPicker.getValue();
        int seconds = secondsPicker.getValue();

        timeLeftInMillis = ((long) hours * 60 * 60 + minutes * 60L + seconds) * 1000;
        startTimeInMillis = timeLeftInMillis;

        countDownTimer = new CountDownTimer(timeLeftInMillis, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                timeLeftInMillis = millisUntilFinished;
                progress = (int) (((float) timeLeftInMillis / (float) startTimeInMillis) * 100);
                countdownProgressBar.setProgress(progress);
                updateCountDown();
            }

            @Override
            public void onFinish() {
                timerRunning = false;
                startButton.setText("Start");
                resetTimer();
            }
        }.start();

        timerRunning = true;
        startButton.setVisibility(View.INVISIBLE);
    }

    private void pauseTimer() {
        countDownTimer.cancel();
        timerRunning = false;
        pauseButton.setText("Resume");
    }

    private void resumeTimer() {
        countDownTimer = new CountDownTimer(timeLeftInMillis, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                timeLeftInMillis = millisUntilFinished;
                updateCountDown();
            }

            @Override
            public void onFinish() {
                timerRunning = false;
                startButton.setText("Start");
            }
        }.start();

        timerRunning = true;
        pauseButton.setText("Pause");
    }

    private void resetTimer() {
        timerRunning = false;
        countDownTimer.cancel();

        countdownProgressBar.setVisibility(View.INVISIBLE);
        countdownTextView.setVisibility(View.INVISIBLE);
        hoursPicker.setVisibility(View.VISIBLE);
        minutesPicker.setVisibility(View.VISIBLE);
        secondsPicker.setVisibility(View.VISIBLE);

        startButton.setVisibility(View.VISIBLE);
        startButton.setText("Start");

        resetButton.setVisibility(View.INVISIBLE);
        pauseButton.setVisibility(View.INVISIBLE);
    }

    private void updateCountDown() {
        int hours = (int) (timeLeftInMillis / 1000) / 3600;
        int minutes = (int) (timeLeftInMillis / 1000) / 60;
        int seconds = (int) (timeLeftInMillis / 1000) % 60;
        String timeLeftFormatted = String.format(Locale.getDefault(), "%02d:%02d:%02d", hours, minutes, seconds);
        countdownTextView.setText(timeLeftFormatted);
    }


}