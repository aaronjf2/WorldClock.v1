package com.example.cs125finalprojectv1;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.TargetApi;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextClock;
import android.widget.TextView;


import java.time.Clock;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;

@TargetApi(Build.VERSION_CODES.O)



public class MainActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // UI elements
        final String[] locations = {"SELECT", "San Francisco", "Los Angeles", "Chicago", "Toronto", "New York", "London", "Paris", "Berlin", "Rome", "Johannesburg", "Shanghai", "Hong Kong", "Taipei", "Tokyo", "Sydney"};
        final String[] zones = {"SELECT", "America/Los_Angeles", "America/Los_Angeles", "America/Chicago", "America/Toronto", "America/New_York", "Europe/London", "Europe/Paris", "Europe/Berlin", "Europe/Rome", "Africa/Johannesburg", "Asia/Shanghai", "Asia/Hong_Kong", "Asia/Taipei", "Asia/Tokyo", "Australia/Sydney"};
        ArrayAdapter<String> input = new ArrayAdapter<String>(this,R.layout.spinner_layout, locations); //was: android.R.layout.simple_spinner_item
        input.setDropDownViewResource(R.layout.spinner_dropdown); //was: android.R.layout.simple_spinner_dropdown_item
        Spinner spinner = findViewById(R.id.selectLocation);
        spinner.setAdapter(input);

        final AdapterView.OnItemSelectedListener selectedLocation = new OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                TextView locationTime = findViewById(R.id.locationTime);
                if (locations[i].equals("SELECT")) {
                    String resultText = "Please select a location";
                    locationTime.setText(resultText);
                } else {
                    String resultText = "The time in " + locations[i] + " is";
                    locationTime.setText(resultText);

                    TextView date = findViewById(R.id.date);


                    ZoneId zoneId = ZoneId.of(zones[i]);
                    Clock clock1 = Clock.system(zoneId);
                    Instant instant = clock1.instant();
                    ZonedDateTime finalTime = instant.atZone(clock1.getZone());
                    String finalfinaltime = finalTime.toString();
                    String[] s = finalfinaltime.split("T");

                    String display2 = s[0];

                    date.setText(display2);

                    TextClock simpleDigitalClock = findViewById(R.id.simpleDigitalClock);
                    simpleDigitalClock.setTimeZone(zones[i]);


                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        };
        spinner.setOnItemSelectedListener(selectedLocation);
    }

}







