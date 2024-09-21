package com.example.helloworld;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class SettingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);  // Points to the settings layout file

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Enable the "Up" button
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);  // Shows the Up button in the toolbar
            getSupportActionBar().setTitle("Settings");      // Set a title for the toolbar
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
        inflater.inflate(R.menu.settings_menu, menu);  // Inflate the menu_main.xml
        return true;
    }

    // Handle toolbar menu item clicks
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            // Handle Settings option click
            return true;
        }

        return super.onOptionsItemSelected((android.view.MenuItem) item);
    }

}

