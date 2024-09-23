package com.example.helloworld;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class DataActivity extends AppCompatActivity {

    // Memebers

    protected TextView counterName1;
    protected TextView counterName2;
    protected TextView counterName3;
    protected TextView maxCount;
    protected SharedPreferenceHelper DataSPH;
    protected RecyclerView eventTable;

    protected List<String> eventDisplayList;
    protected List<String> eventList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data);  // Points to the data layout file


        Toolbar toolbar = findViewById(R.id.toolbar_data);
        setSupportActionBar(toolbar);

        // Enable the "Up" button
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);  // Shows the Up button in the toolbar
            getSupportActionBar().setTitle("Data Activity");      // Set a title for the toolbar
        }

        counterName1 = findViewById(R.id.view_data_count1);
        counterName2 = findViewById(R.id.view_data_count2);
        counterName3 = findViewById(R.id.view_data_count3);
        maxCount = findViewById(R.id.view_data_total);
        DataSPH = new SharedPreferenceHelper(getApplicationContext());

        counterName1.setText( DataSPH.getProfileName1() + ": " + String.valueOf(DataSPH.getButtonOneCount()) + " events");
        counterName2.setText( DataSPH.getProfileName2() + ": " +  String.valueOf(DataSPH.getButtonTwoCount()) + " events");
        counterName3.setText( DataSPH.getProfileName3() + ": " +  String.valueOf(DataSPH.getButtonThreeCount()) + " events");
        maxCount.setText("Total Events: " + String.valueOf(DataSPH.getButtonOneCount() + DataSPH.getButtonTwoCount() + DataSPH.getButtonThreeCount()));

        eventDisplayList = new ArrayList<>();

        // Setting up the list to be displayed on the data page
        eventDisplayList.add(DataSPH.getProfileName1());
        eventDisplayList.add(DataSPH.getProfileName2());
        eventDisplayList.add(DataSPH.getProfileName3());

        // Setting up the list for the logged Clicks
        eventList = DataSPH.getProfileNames();



    }
    // Handle "Up" button behavior
    @Override
    public boolean onSupportNavigateUp() {
        // Finish current activity and go back to the previous one
        finish();
        return true;
    }
    // Inflate the menu; this adds items to the action bar if present
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.data_menu, menu);  // Inflate the data_menu.xml
        return true;
    }

    // Handle toolbar menu item clicks
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here
        int id = item.getItemId();

        if (id == R.id.action_data) {
            // Handle data option click
            toggleMode();
        }

        return super.onOptionsItemSelected((android.view.MenuItem) item);
    }

    private void toggleMode()
    {
        for (String name: eventDisplayList)
        {
            if (name.equals(DataSPH.getProfileName1()))
            {
                counterName1.setText("Counter 1: "  + String.valueOf(DataSPH.getButtonOneCount()) + " events");
            }
            else if (name.equals(DataSPH.getProfileName2()))
            {
                counterName1.setText("Counter 2: " + String.valueOf(DataSPH.getButtonTwoCount()) + " events");
            }
            else if (name.equals(DataSPH.getProfileName3()))
            {
                counterName1.setText("Counter 3: " + String.valueOf(DataSPH.getButtonThreeCount()) + " events");
            }
        }
    }
}

