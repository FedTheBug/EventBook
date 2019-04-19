package com.example.eventbook;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.util.SparseArray;
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
import com.google.android.gms.vision.Frame;
import com.google.android.gms.vision.text.TextBlock;
import com.google.android.gms.vision.text.TextRecognizer;

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
    SearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_evnets);


        // initializing variables
        searchView = (SearchView) findViewById(R.id.search);
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

        // Find Event From Event List
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                boolean found = false;
                for(int i=0; i<listViewEvents.getCount(); i++){
                    if(listViewEvents.getAdapter().getItem(i).toString().toLowerCase().contains(query.toLowerCase())){
                        adapter.getFilter().filter(query);
                        found = true;
                    }
                    if(!found){
                        Toast.makeText(EventsActivity.this, "No Match found",Toast.LENGTH_SHORT).show();
                    }
                }
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

    }

    // Fetch All Events From The Database and Add Them To The Array List
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


    // Text From Image
    private String getTextFromImage(Bitmap bitmap){
        String str= "";
        TextRecognizer tr = new TextRecognizer.Builder(getApplicationContext()).build();
        if(!tr.isOperational())
            Log.e("ERROR", "Detector dependencies are not yet available");
        else{
            Frame frame =  new Frame.Builder().setBitmap(bitmap).build();
            SparseArray<TextBlock> items = tr.detect(frame);

            for(int i=0; i<items.size(); i++){
                TextBlock item = items.valueAt(i);
                String s = item.getValue();
                str += s;
            }
        }
        return str;
    }

    // Searching Event Name From The Extracted Text From The Image
    public void searchByPhoto(String string){
        boolean flag = false;
        for(int i=0; i<arrayList.size(); i++){
            Event event = arrayList.get(i);

            if(string.contains(event.getName())){
                flag = true;
                // Goes To Event Details Activity
                Intent intent = new Intent(getApplicationContext(), ProfileActivity.class);
                // putting an object as an intent extra
                intent.putExtra("Event",(Serializable) event);
                startActivity(intent);
            }
        }
        if(flag == false){
            Toast.makeText(this, "Not found!", Toast.LENGTH_SHORT).show();
        }
    }

}