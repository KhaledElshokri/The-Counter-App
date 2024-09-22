package com.example.helloworld;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


public class MainActivity extends AppCompatActivity {

    // All Buttons used
    protected Button button_settings;
    protected Button button_eventA;
    protected Button button_eventB;
    protected Button button_eventC;
    protected Button button_showCounts;
    protected TextView totalCount;

    // Shared Preference to keep track of changes
    protected SharedPreferenceHelper sharedPreferenceHelper;

    // Counters
    int count1;
    int count2;
    int count3;
    int countertotal;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sharedPreferenceHelper = new SharedPreferenceHelper(MainActivity.this);

        // Find Buttons/TextViews
        button_settings = findViewById(R.id.button_settings);
        button_eventA = findViewById(R.id.button_counter1);
        button_eventB = findViewById(R.id.button_counter2);
        button_eventC = findViewById(R.id.button_counter3);
        button_showCounts = findViewById(R.id.button_data);
        totalCount = findViewById(R.id.view_counts);

        // Init totalcount
        totalCount.setText("Total Count: " + String.valueOf(countertotal));

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
                count1++;
                updateSharedPref();
            }
        });

        //Increments and updates the count of the middle button
        button_eventB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                count2++;
                updateSharedPref();
            }
        });

        //Increments and updates the count of the lower button
        button_eventC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                count3++;
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

    }
    // This method updates the content of the shared preference to sync
    private void updateSharedPref()
    {
        sharedPreferenceHelper.saveButtonOneCount(count1);
        sharedPreferenceHelper.saveButtonTwoCount(count2);
        sharedPreferenceHelper.saveButtonThreeCount(count3);

        countertotal = count1 + count2 + count3;
        totalCount.setText("Total Count: " + String.valueOf(countertotal));

        //The three buttons are disabled after reaching to the maximum number of counts set by the user. A message is also shown.
        if (countertotal == sharedPreferenceHelper.getTotalCount()){
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