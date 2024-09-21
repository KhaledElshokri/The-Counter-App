package com.example.helloworld;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;


public class MainActivity extends AppCompatActivity {

    protected Button button_settings;
    protected Button button_eventA;
    protected Button button_eventB;
    protected Button button_eventC;
    protected Button button_showCounts;

    protected TextView totalCount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Find Buttons
        button_settings = findViewById(R.id.button_settings);
        button_eventA = findViewById(R.id.button_counter1);
        button_eventB = findViewById(R.id.button_counter2);
        button_eventC = findViewById(R.id.button_counter3);
        button_showCounts = findViewById(R.id.button_data);

        // Set an OnClickListener to handle Settings button click
        button_settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create an Intent to start the SecondActivity
                Intent intent = new Intent(MainActivity.this, SettingsActivity.class);
                startActivity(intent);
            }
        });

        // Set an OnClickListener to handle Settings button click
        button_showCounts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create an Intent to start the SecondActivity
                Intent intent = new Intent(MainActivity.this, DataActivity.class);
                startActivity(intent);
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        // Refresh data, start animations, or other UI updates
    }

}