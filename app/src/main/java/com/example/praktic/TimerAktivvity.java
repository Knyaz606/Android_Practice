package com.example.praktic;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.TextView;

import java.util.Locale;
import java.util.concurrent.Delayed;

public class TimerAktivvity extends AppCompatActivity {

    private int second =0;
    private boolean isRunning = false;
    private TextView textViewTimer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timer_aktivvity);
        textViewTimer = findViewById(R.id.textViewTimer1);

        if (savedInstanceState != null) {
            second = savedInstanceState.getInt("second");
            isRunning = savedInstanceState.getBoolean("isRunning");
        }

        runTimer();
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {//чтобы переворот экрана неломал таймер
        super.onSaveInstanceState(outState);
        outState.putInt("second",second);
        outState.putBoolean("isRunning", isRunning);
    }


    public void onClickExitTime(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void onClickStart(View view) {
        isRunning = true;
    }

    public void onClickStop(View view) {
        isRunning = false;
    }

    public void onClickReset(View view) {
        isRunning = false;
        second = 0;
    }

    private void runTimer(){
        final Handler handler =new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {
                int hours = second / 3600;
                int minutes = (second % 3600) / 60;
                int secs =second % 60;
                String time = String.format(Locale.getDefault(), "%d:%02d:%02d", hours, minutes,secs);
                textViewTimer.setText(time);
                if (isRunning){
                    second++;
                }
               handler.postDelayed(this,1000);
            }
        });

    }
}