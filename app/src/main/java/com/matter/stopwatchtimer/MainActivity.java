package com.matter.stopwatchtimer;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.ObjectAnimator;
import android.content.Intent;
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

    private long startTimeInMillis;

    private CountDownTimer countDownTimer;
    private boolean timerRunning;

    private long timeLeftInMillis;


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
                if (timerRunning) {
                    pauseTimer();
                } else {
                    startTimer();
                    countdownProgressBar.setVisibility(View.VISIBLE);
                    countdownTextView.setVisibility(View.VISIBLE);
                    hoursPicker.setVisibility(View.INVISIBLE);
                    minutesPicker.setVisibility(View.INVISIBLE);
                    secondsPicker.setVisibility(View.INVISIBLE);
                }
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
    }

    private void startTimer() {
        int hours = hoursPicker.getValue();
        int minutes = minutesPicker.getValue();
        int seconds = secondsPicker.getValue();

        timeLeftInMillis = (hours * 60 * 60 + minutes * 60 + seconds) * 1000;
        startTimeInMillis = timeLeftInMillis;

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
        startButton.setText("Pause");
    }

    private void pauseTimer() {
        countDownTimer.cancel();
        timerRunning = false;
        startButton.setText("Start");
    }

    private void updateCountDown() {
        int hours = (int) (timeLeftInMillis / 1000) / 3600;
        int minutes = (int) (timeLeftInMillis / 1000) / 60;
        int seconds = (int) (timeLeftInMillis / 1000) % 60;
        String timeLeftFormatted = String.format(Locale.getDefault(), "%02d:%02d:%02d", hours, minutes, seconds);
        countdownTextView.setText(timeLeftFormatted);

        int progress = (int) (((float) timeLeftInMillis / (float) startTimeInMillis) * 100);
        
        countdownProgressBar.setProgress(progress);
    }


}