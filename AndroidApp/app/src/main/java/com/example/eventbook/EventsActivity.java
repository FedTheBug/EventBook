package com.example.eventbook;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.eventbook.models.Event;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.sql.Array;
import java.util.ArrayList;

public class EventsActivity extends AppCompatActivity {

    ListView listViewEvents;
    ArrayList<Event> arrayList;
    ArrayAdapter<Event> adapter;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_evnets);


        // initializing variables
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading...");
        listViewEvents = (ListView) findViewById(R.id.listViewEvents);
        arrayList = new ArrayList<Event>();

        fetchAllEvents();


        adapter = new ArrayAdapter<Event>(EventsActivity.this, android.R.layout.	simple_expandable_list_item_1, arrayList);
        listViewEvents.setAdapter(adapter);

        // action listener for each item in the listViewEvents
        listViewEvents.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                 // goes to competition details screen
                Intent intent = new Intent(getApplicationContext(), EventsActivity.class);
                Event event= arrayList.get(position);
                // putting an object as an intent extra
                intent.putExtra("Event",(Serializable) event);
                startActivity(intent);

            }
        });

    }

    // fetch all Events from the database and add them to the arraylist
    private void fetchAllEvents() {
        progressDialog.show();
        StringRequest stringRequest = new StringRequest(Request.Method.GET,
                Constants.URL_ALL_EVENTS,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            progressDialog.dismiss();
                            // response is a JSONArray
                            JSONArray jArray = new JSONArray(response);

                            // extracting JSONObjects from each element of JSONArray
                            for (int i = 0; i < jArray.length(); i++) {
                                JSONObject jb = jArray.getJSONObject(i);
                                int id = jb.getInt("id");
                                String name = jb.getString("name");
                                String venue = jb.getString("venue");
                                String event_date = jb.getString("event_date");
                                String reg_deadline = jb.getString("reg_deadline");
                                String description = jb.getString("description");
                                int organizer_id = jb.getInt("organizer_id");

                                // creating a new Event object
                                Event event = new Event(id, name, venue, event_date,
                                        reg_deadline, description, organizer_id);

                                // add object to the array list
                                arrayList.add(event);
                                // notifying the adapter for the change
                                adapter.notifyDataSetChanged();

                            }
                        } catch (JSONException e) {
                            Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        progressDialog.hide();
                        Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_LONG).show();
                    }
                }) {

        };
        RequestHandler.getInstance(this).addToRequestQueue(stringRequest);
    }

}