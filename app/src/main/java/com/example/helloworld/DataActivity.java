package com.example.helloworld;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DataActivity extends AppCompatActivity {

    // Memebers

    protected TextView counterName1;
    protected TextView counterName2;
    protected TextView counterName3;
    protected TextView maxCount;
    protected SharedPreferenceHelper DataSPH;
    protected TableLayout eventTable;

    protected List<String> eventDisplayList;
    protected List<String> eventList;
    protected int toggleFlag = 0;

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
        eventTable = findViewById(R.id.event_table);
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

        Collections.reverse(eventList);
        // Loop through the list of strings and add each as a row
        for (String event : eventList) {
            // Create a new TableRow
            TableRow tableRow;
            tableRow = new TableRow(this);

            // Create a new TextView for the event string
            TextView textView = new TextView(this);
            textView.setText(event);

            textView.setPadding(16, 16, 16, 16);  // Set padding for better readability

            // Add the TextView to the TableRow
            tableRow.addView(textView);

            // Optionally, you can set a background or other styling for each row
            tableRow.setBackgroundResource(android.R.drawable.dialog_holo_light_frame); // Optional styling

            // Add the TableRow to the TableLayout
            eventTable.addView(tableRow);
        }
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
        if(toggleFlag == 0)
        {
            toggleFlag = 1; // Setting this mode to 1

            for (String name: eventDisplayList)
            {
                if (name.equals(DataSPH.getProfileName1()))
                {
                    counterName1.setText("Counter 1: "  + String.valueOf(DataSPH.getButtonOneCount()) + " events");
                }
                else if (name.equals(DataSPH.getProfileName2()))
                {
                    counterName2.setText("Counter 2: " + String.valueOf(DataSPH.getButtonTwoCount()) + " events");
                }
                else if (name.equals(DataSPH.getProfileName3()))
                {
                    counterName3.setText("Counter 3: " + String.valueOf(DataSPH.getButtonThreeCount()) + " events");
                }
            }

            // Loop through all rows of the TableLayout
            for (int i = 0; i < eventTable.getChildCount(); i++) {
                // Get the current TableRow
                TableRow row = (TableRow) eventTable.getChildAt(i);

                // Check if the TableRow contains a TextView
                if (row.getChildCount() > 0) {
                    TextView textView = (TextView) row.getChildAt(0);

                    String name = (String) textView.getText();
                    if (name.equals(DataSPH.getProfileName1()))
                    {
                        textView.setText("Counter 1");
                    }
                    else if (name.equals(DataSPH.getProfileName2()))
                    {
                        textView.setText("Counter 2");
                    }
                    else
                    {
                        textView.setText("Counter 3");
                    }
                }
            }
        }
        else
        {
            toggleFlag = 0;

            for (String name: eventDisplayList)
            {
                if (name.equals(DataSPH.getProfileName1()))
                {
                    counterName1.setText(DataSPH.getProfileName1() + ": " + String.valueOf(DataSPH.getButtonOneCount()) + " events");
                }
                else if (name.equals(DataSPH.getProfileName2()))
                {
                    counterName2.setText(DataSPH.getProfileName2()  + ": " + String.valueOf(DataSPH.getButtonTwoCount()) + " events");
                }
                else if (name.equals(DataSPH.getProfileName3()))
                {
                    counterName3.setText(DataSPH.getProfileName3() + ": " + String.valueOf(DataSPH.getButtonThreeCount()) + " events");
                }
            }

            // Loop through all rows of the TableLayout
            for (int i = 0; i < eventTable.getChildCount(); i++) {
                // Get the current TableRow
                TableRow row = (TableRow) eventTable.getChildAt(i);

                // Check if the TableRow contains a TextView
                if (row.getChildCount() > 0) {
                    TextView textView = (TextView) row.getChildAt(0);

                    String name = (String) textView.getText();
                    if (name.equals("Counter 1"))
                    {
                        textView.setText(DataSPH.getProfileName1());
                    }
                    else if (name.equals("Counter 2"))
                    {
                        textView.setText(DataSPH.getProfileName2());
                    }
                    else
                    {
                        textView.setText(DataSPH.getProfileName3());
                    }
                }
            }

        }

    }
}

