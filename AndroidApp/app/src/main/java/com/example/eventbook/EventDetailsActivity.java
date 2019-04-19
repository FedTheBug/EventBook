package com.example.eventbook;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.eventbook.models.Event;

public class EventDetailsActivity extends AppCompatActivity {

    private TextView textViewEventName, textViewVenue,
            textViewEventDate, textViewRegDeadline, textViewDescription;
    int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_details);

        Intent intent = getIntent();
        final Event event = (Event) getIntent().getSerializableExtra("Event");
        // id = SharedPrefManager.getInstance(this).getID();

        String name = event.getName();
        String venue = event.getVenue();
        String eventdate = event.getEvent_date();
        String regDeadine = event.getReg_deadline();
        String description = event.getDescription();

        textViewEventName = (TextView) findViewById(R.id.textViewEventName);
        textViewVenue = (TextView) findViewById(R.id.textViewVenue);
        textViewEventDate = (TextView) findViewById(R.id.textViewEventDate);
        textViewRegDeadline = (TextView) findViewById(R.id.textViewRegDeadline);
        textViewDescription = (TextView) findViewById(R.id.textViewDescription);


        textViewEventName.setText(name);
        textViewVenue.setText(venue);
        textViewEventDate.setText(eventdate);
        textViewRegDeadline.setText(regDeadine);
        textViewDescription.setText(description);






    }

}


