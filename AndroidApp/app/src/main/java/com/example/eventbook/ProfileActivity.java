package com.example.eventbook;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;


import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class ProfileActivity extends AppCompatActivity implements View.OnClickListener {


    private TextView textViewUsername, textViewUserEmail;
    private Button buttonEvents;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);


        if(!SharedPrefManager.getInstance(this).isLoggedIn()){
            finish();
            startActivity(new Intent(this, LoginActivity.class));
        }


        textViewUsername = (TextView) findViewById(R.id.textViewUsername);
        textViewUserEmail = (TextView) findViewById(R.id.textViewUserEmail);

        buttonEvents = (Button) findViewById(R.id.buttonEvents);
        buttonEvents.setOnClickListener(this);

        textViewUserEmail.setText(SharedPrefManager.getInstance(this).getUserEmail());
        textViewUsername.setText(SharedPrefManager.getInstance(this).getName());
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

    @Override
    public void onClick(View v) {
        if(v == buttonEvents)
            startActivity(new Intent(this, EventsActivity.class));
    }
}
