package com.example.helloworld;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class SettingsActivity extends AppCompatActivity {

    protected Button button_save;
    protected EditText counterName1;
    protected EditText counterName2;
    protected EditText counterName3;
    protected EditText maxCount;
    protected Settings settingsSetUp;

    SharedPreferenceHelper settingsSPH;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);  // Points to the settings layout file

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Enable the "Up" button
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);  // Shows the Up button in the toolbar
            getSupportActionBar().setTitle("Settings");      // Sets a title for the toolbar
        }

        // Setting up buttons and text input
        button_save = findViewById(R.id.save_button);
        counterName1 = findViewById(R.id.name_counter1);
        counterName2 = findViewById(R.id.name_counter2);
        counterName3 = findViewById(R.id.name_counter3);
        maxCount = findViewById(R.id.max_count);

        // Set an OnClickListener to handle Settings button click
        button_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                handleInput();
                // Display Mode only
                counterName1.setEnabled(false);
                counterName2.setEnabled(false);
                counterName3.setEnabled(false);
                maxCount.setHint(String.valueOf(settingsSetUp.getTotalCount()));
                maxCount.setEnabled(false);
                button_save.setVisibility(View.GONE);

            }
        });
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
            counterName1.setEnabled(true);
            counterName2.setEnabled(true);
            counterName3.setEnabled(true);
            maxCount.setEnabled(true);
            button_save.setVisibility(View.VISIBLE);
        }

        return super.onOptionsItemSelected((android.view.MenuItem) item);
    }
    private void handleInput()
    {
        String name1;
        String name2;
        String name3;
        int number;

        //With the use of a try-catch, it allows the application to not crash if the user inputs nothing.
        try {

            settingsSetUp = new Settings();
            name1 = counterName1.getText().toString();
            name2 = counterName2.getText().toString();
            name3 = counterName3.getText().toString();
            number = Integer.parseInt(maxCount.getText().toString());

            //If the user inputs a number < 5 or > 200, it will make an error and display a message to try again so that the user puts an appropriate number
            if (number < 5 || number > 200){
                Toast.makeText(this,"Invalid, please insert a number between 5 and 200", Toast.LENGTH_SHORT).show();
                return;
            }

            //If the user inputs an empty name in one of the three names, it will make an error and display a message to try again.
            if (name1.isEmpty() || name2.isEmpty() || name3.isEmpty()){
                Toast.makeText(this, "Invalid, please insert names", Toast.LENGTH_SHORT).show();
                return;
            }

            //With the Settings class, it sets the names the maximum number of counts.
            settingsSetUp.setName1(name1);
            settingsSetUp.setName2(name2);
            settingsSetUp.setName3(name3);
            settingsSetUp.setTotalCount(number);

            //With the sharedPreferenceHelper class, it saves and the names and the maximum number of counts from the Settings class
            settingsSPH = new SharedPreferenceHelper(getApplicationContext());
            settingsSPH.saveProfileName1(settingsSetUp.getName1());
            settingsSPH.saveProfileName2(settingsSetUp.getName2());
            settingsSPH.saveProfileName3(settingsSetUp.getName3());
            settingsSPH.saveTotalCount(settingsSetUp.getTotalCount());

            //Once the user presses the save button, it shows this message that the content is saved.
            Toast.makeText(this,"The names of each event and the maximum total count are now saved!", Toast.LENGTH_SHORT).show();

        }
        //If the user inputs nothing and presses the save button, it will make an error and display a message to put content in the names and the maximum number of counts.
        catch (NumberFormatException e){
            Toast.makeText(this,"Please insert names and the maximum number", Toast.LENGTH_SHORT).show();
            return;
        }

    }

}

