package com.example.helloworld;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    // All Buttons used
    protected Button button_settings;
    protected Button button_eventA;
    protected Button button_eventB;
    protected Button button_eventC;
    protected Button button_showCounts;
    protected TextView totalCount;
    protected List<String> eventTrigList;

    // Shared Preference to keep track of changes
    protected SharedPreferenceHelper sharedPreferenceHelper;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sharedPreferenceHelper = new SharedPreferenceHelper(MainActivity.this);
        eventTrigList = new ArrayList<>();

        // Find Buttons/TextViews
        button_settings = findViewById(R.id.button_settings);
        button_eventA = findViewById(R.id.button_counter1);
        button_eventB = findViewById(R.id.button_counter2);
        button_eventC = findViewById(R.id.button_counter3);
        button_showCounts = findViewById(R.id.button_data);
        totalCount = findViewById(R.id.view_counts);

        // Init totalcount
        totalCount.setText("Total Count: " + String.valueOf(sharedPreferenceHelper.getTotalCount()));

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

        // Increment counts
        //Increments and updates the count of the upper button
        button_eventA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                eventTrigList.add(sharedPreferenceHelper.getProfileName1());
                // Update the list the holds the button clicks logs
                sharedPreferenceHelper.saveProfileNames(eventTrigList);
                // Update button
                sharedPreferenceHelper.saveButtonOneCount(sharedPreferenceHelper.getButtonOneCount() + 1);
                updateSharedPref();
            }
        });

        //Increments and updates the count of the middle button
        button_eventB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                eventTrigList.add(sharedPreferenceHelper.getProfileName2());
                // Update the list the holds the button clicks logs
                sharedPreferenceHelper.saveProfileNames(eventTrigList);
                // Update button
                sharedPreferenceHelper.saveButtonTwoCount(sharedPreferenceHelper.getButtonTwoCount() + 1);
                updateSharedPref();
            }
        });

        //Increments and updates the count of the lower button
        button_eventC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                eventTrigList.add(sharedPreferenceHelper.getProfileName3());
                // Update the list the holds the button clicks logs
                sharedPreferenceHelper.saveProfileNames(eventTrigList);
                // Update Button
                sharedPreferenceHelper.saveButtonThreeCount(sharedPreferenceHelper.getButtonThreeCount() + 1);
                updateSharedPref();
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();

        String name1 = sharedPreferenceHelper.getProfileName1();
        String name2 = sharedPreferenceHelper.getProfileName2();
        String name3 = sharedPreferenceHelper.getProfileName3();

        if(name1 == null && name2 == null && name3 == null)
        {
            goToSettingsActivity();
        }
        else
        {
            button_eventA.setText(name1);
            button_eventB.setText(name2);
            button_eventC.setText(name3);
        }

        if (sharedPreferenceHelper.getTotalCount() < sharedPreferenceHelper.getMaxCount()){
            button_eventA.setEnabled(true);
            button_eventB.setEnabled(true);
            button_eventC.setEnabled(true);
        }

    }
    // This method updates the content of the shared preference to sync
    private void updateSharedPref()
    {
        sharedPreferenceHelper.saveTotalCount();
        totalCount.setText("Total Count: " + String.valueOf(sharedPreferenceHelper.getTotalCount()));

        //The three buttons are disabled after reaching to the maximum number of counts set by the user. A message is also shown.
        if (sharedPreferenceHelper.getTotalCount() >= sharedPreferenceHelper.getMaxCount()){
            button_eventA.setEnabled(false);
            button_eventB.setEnabled(false);
            button_eventC.setEnabled(false);
            Toast.makeText(this,"You have reached the maximum number of events", Toast.LENGTH_SHORT).show();
        }

    }
    //This method goes to the SettingsActivity Page
    private void goToSettingsActivity ()
    {
        Intent intent = new Intent(MainActivity.this, SettingsActivity.class);
        startActivity(intent);
    }
    //This method goes to the DataActivity Page
    private void goToDataActivity(){
        Intent intent = new Intent(this, DataActivity.class);
        startActivity(intent);
    }


}