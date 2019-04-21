package com.example.eventbook;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.util.SparseArray;
import android.view.Menu;
import android.view.MenuItem;
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

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.sql.Array;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class EventsActivity extends AppCompatActivity {

    ListView listViewEvents;
    ArrayList<Event> arrayList;
    ArrayAdapter<Event> adapter;
    private ProgressDialog progressDialog;
    SearchView searchView;
    FloatingActionButton SeachWithPhoto;
    Context context;
    String mCurrentPhotoPath;

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
        context = this;
        SeachWithPhoto = findViewById(R.id.SeachWithPhoto);

        fetchAllEvents();

        adapter = new ArrayAdapter<Event>(EventsActivity.this, android.R.layout.	simple_expandable_list_item_1, arrayList);
        listViewEvents.setAdapter(adapter);

        // Action Listener For Image Floating Button
        SeachWithPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectImage();
            }
        });

        // action listener for each item in the listViewEvents
        listViewEvents.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Goes To Event Details Activity
                Intent intent = new Intent(getApplicationContext(), EventDetailsActivity.class);
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
                Intent intent = new Intent(getApplicationContext(), EventDetailsActivity.class);
                // putting an object as an intent extra
                intent.putExtra("Event",(Serializable) event);
                startActivity(intent);


            }
        }
        if(flag == false){
            Toast.makeText(this, "Not found!", Toast.LENGTH_SHORT).show();
        }
    }

    // Options For Image Search
    private void selectImage() {
        final CharSequence[] options = { "Take Photo", "Choose from Gallery","Cancel" };
        AlertDialog.Builder builder = new AlertDialog.Builder(EventsActivity.this);

        builder.setTitle("Search Events By Photo");

        builder.setItems(options, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int item) {
                if(options[item].equals("Take Photo")){
                    Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    if (intent.resolveActivity(getPackageManager()) != null) {
                        File photoFile = null;
                        try {
                            photoFile = createImageFile();
                        } catch (IOException ex) {

                        }
                        if (photoFile != null) {
                            Uri photoURI = FileProvider.getUriForFile(context,
                                    "com.example.android.fileprovider",
                                    photoFile);
                            intent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                            startActivityForResult(intent, 1);
                        }
                    }
                }
                else if(options[item].equals("Choose from Gallery")) {
                    Intent intent = new Intent();
                    intent.setType("image/*");
                    intent.setAction(Intent.ACTION_GET_CONTENT);
                    startActivityForResult(Intent.createChooser(intent, "Select File"),2);
                }
                else if(options[item].equals("Cancel")) {
                    dialog.dismiss();
                }
            }
        });
        builder.show();
    }

    // Action After Choosing Option For 'Take a photo'
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == 1) {
                BitmapFactory.Options bmOptions = new BitmapFactory.Options();
                bmOptions.inJustDecodeBounds = true;
                BitmapFactory.decodeFile(mCurrentPhotoPath, bmOptions);
                int photoW = bmOptions.outWidth;
                int photoH = bmOptions.outHeight;

                bmOptions.inJustDecodeBounds = false;
                bmOptions.inPurgeable = true;

                Bitmap bitmap = BitmapFactory.decodeFile(mCurrentPhotoPath, bmOptions);
                String txt = getTextFromImage(bitmap);
                searchByPhoto(txt);
            }else if(requestCode == 2){
                Bitmap bm=null;
                if (data != null) {
                    try {
                        bm = MediaStore.Images.Media.getBitmap(getApplicationContext().getContentResolver(), data.getData());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                String txt =  getTextFromImage(bm);
                searchByPhoto(txt);
            }
        }
    }

    // Create A Temporary File
    private File createImageFile() throws IOException {
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = null;
        try {
            image = File.createTempFile(
                    imageFileName,  /* prefix */
                    ".jpg",         /* suffix */
                    storageDir      /* directory */
            );
        } catch (IOException e) {
            e.printStackTrace();
        }
        mCurrentPhotoPath = image.getAbsolutePath();
        return image;
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){

            // Action After Logout
            case R.id.menuLogout:
                SharedPrefManager.getInstance(this).logout();
                finish();
                startActivity(new Intent(this, MainActivity.class));
                break;

            // Action on Click Settings
            case R.id.menuSettings:
                Toast.makeText(this, "You clicked settings", Toast.LENGTH_LONG).show();
                break;

            // Action on Click Events
            case R.id.menuEvents:
                startActivity(new Intent(this, EventsActivity.class));
                break;

            // Action on Click Dashboard
            case R.id.menuDashboard:
                startActivity(new Intent(this, ProfileActivity.class));
                break;
        }
        return true;
    }
}