package com.example.a9suggestedapps;

import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Chronometer;
import android.widget.Toast;

public class Stopwatch extends AppCompatActivity {

    private long pauseOffset;
    private Chronometer chronometer;
    private boolean running;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stopwatch);

        chronometer = findViewById(R.id.chronometer);
        chronometer.setFormat("Time: %s");
        chronometer.setBase(SystemClock.elapsedRealtime());

        chronometer.setOnChronometerTickListener(new Chronometer.OnChronometerTickListener() {
            @Override
            public void onChronometerTick(Chronometer chronometer) {
                if ((SystemClock.elapsedRealtime() - chronometer.getBase()) >= 100000) {
                    chronometer.setBase(SystemClock.elapsedRealtime());
                    Toast.makeText(Stopwatch.this, "Bing!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void startChronometer(View v) {

        if(!running){
            chronometer.setBase(SystemClock.elapsedRealtime() - pauseOffset);
            chronometer.start();
            running = true;
        }

    }

    public void pauseChronometer(View v) {

        if(running){
            chronometer.stop();
            pauseOffset = SystemClock.elapsedRealtime() - chronometer.getBase();
            running = false;
        }

    }

    public void resetChronometer(View v) {

        chronometer.setBase(SystemClock.elapsedRealtime());
        pauseOffset = 0;
    }
}
