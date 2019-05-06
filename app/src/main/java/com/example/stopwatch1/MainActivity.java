package com.example.stopwatch1;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    TextView hText;
    TextView Timertext;
    Button startbtn;
    Button stopbtn;
    Button resetBtn;
    boolean isRunning;
    int totalSec = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        hText = (TextView) findViewById(R.id.textView3);
        Timertext = (TextView) findViewById(R.id.textView5);


        if (savedInstanceState != null) {

            totalSec = savedInstanceState.getInt("TotalSeconds");
        }

        startbtn = (Button) findViewById(R.id.startBtn);
        stopbtn = (Button) findViewById(R.id.stopbtn);
        resetBtn = (Button) findViewById(R.id.reset);


        startbtn.setOnClickListener(new View.OnClickListener() {

            @Override

            public void onClick(View v) {

                if (isRunning) {
                    return;
                }

                isRunning = true;
                runTimer();
            }

        });

        stopbtn.setOnClickListener(new View.OnClickListener() {

            @Override

            public void onClick(View v) {

                isRunning = false;
            }

        });

        resetBtn.setOnClickListener(new View.OnClickListener() {

            @Override

            public void onClick(View v) {

                totalSec = 0;
                isRunning=false;

            }

        });
    }

    @Override
    protected void onStart() {

        super.onStart();
        Log.e("StopWatch", "onStart");

    }

    private void runTimer() {

        // Runnable Aesa Code jo run hosakta ho
        Runnable runnable = new Runnable() {    // Ek interface hai
            @Override

            public void run() {

                int hours = totalSec / 3600;
                int minutes = (totalSec % 3600) / 60;
                int seconds = totalSec % 60;

                if (!isRunning) {

                    return;
                }

                String time = String.format(Locale.getDefault(),
                        "%d:%02d:%02d", hours, minutes, seconds);
                Timertext.setText(time);
                Log.e("StopWatch", time);
                totalSec++;
                Handler handler = new Handler();
                handler.postDelayed(this, 1000);
            }
        };
        Handler handler = new Handler();
        handler.post(runnable);

    }
}

