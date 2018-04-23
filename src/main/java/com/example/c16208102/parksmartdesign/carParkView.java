package com.example.c16208102.parksmartdesign;

import android.content.Intent;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.TextView;
import android.widget.Toast;

public class carParkView extends AppCompatActivity {

    private Button mStartButton;
    private Button mPauseButton;
    private Button mResetButton;
    private Chronometer mChronometer;

    private long lastPause;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car_park_view);

        TextView name = (TextView) findViewById(R.id.carParkViewNameID);
        TextView price = (TextView) findViewById(R.id.carParkViewPriceID);
        TextView type = (TextView) findViewById(R.id.carParkTypeID);
        TextView desc = (TextView) findViewById(R.id.carParkViewDescID);
        TextView location = (TextView) findViewById(R.id.carParkLocationID);

        if(getIntent().getStringExtra("price").matches("€0.0")){
            price.setText("FREE!");
        }
        else{
            price.setText(getIntent().getStringExtra("price"));
        }

        name.setText(getIntent().getStringExtra("name"));
        type.setText(getIntent().getStringExtra("type"));
        desc.setText(getIntent().getStringExtra("desc"));
        location.setText(getIntent().getStringExtra("location"));

        mStartButton = (Button) findViewById(R.id.start);
        mPauseButton = (Button) findViewById(R.id.pause);
        mResetButton = (Button) findViewById(R.id.reset);
        mChronometer = (Chronometer) findViewById(R.id.chronometer2);

        mStartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (lastPause != 0) {
                    mChronometer.setBase(mChronometer.getBase() + SystemClock.elapsedRealtime() - lastPause);
                } else {
                    mChronometer.setBase(SystemClock.elapsedRealtime());
                }
                mChronometer.start();
                mStartButton.setEnabled(false);
                mPauseButton.setEnabled(true);
            }
        });

        mPauseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lastPause = SystemClock.elapsedRealtime();
                mChronometer.stop();
                mPauseButton.setEnabled(false);
                mStartButton.setEnabled(true);
            }
        });

        mResetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mChronometer.stop();
                mChronometer.setBase(SystemClock.elapsedRealtime());
                lastPause=0;
                mStartButton.setEnabled(true);
                mPauseButton.setEnabled(false);
            }
        });
    }
}
