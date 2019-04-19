package com.example.eventbook;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class EventDetailsActivity extends AppCompatActivity {

    private TextView textViewEventName, textViewVenue,
            textViewEventDate, textViewRegDeadline, textViewDescription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_details);

        textViewEventName = (TextView) findViewById(R.id.textViewEventName);
        textViewVenue = (TextView) findViewById(R.id.textViewVenue);
        textViewEventDate = (TextView) findViewById(R.id.textViewEventDate);
        textViewRegDeadline = (TextView) findViewById(R.id.textViewRegDeadline);
        textViewDescription = (TextView) findViewById(R.id.textViewDescription);

    }


