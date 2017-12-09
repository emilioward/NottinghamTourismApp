package com.example.emilioward.mobilecoursework;

import android.app.Fragment;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class Activities extends AppCompatActivity {

    Button btnViewActivity;
    Button btnNewActivity;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activities);

        // Buttons
        btnViewActivity = (Button) findViewById(R.id.btnViewActivity);
        btnNewActivity = (Button) findViewById(R.id.btnCreateActivity);

        // view activities click event
        btnViewActivity.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                // Launching All products Activity
                Intent i = new Intent(getApplicationContext(), AllActivities.class);
                startActivity(i);

            }
        });

        //Add Activity click event
        btnNewActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent j = new Intent(getApplicationContext(),InsertActivity.class);
                startActivity(j);
            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        MenuItem item=menu.add("Home"); //your desired title here
        item.setIcon(R.drawable.ic_home_white_48dp); //your desired icon here
        item.setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
        item.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {

            @Override
            public boolean onMenuItemClick(MenuItem item) {
                Intent i = new Intent(Activities.this, Main.class);
                startActivity(i);
                return false;
            }
        });
        return true;
    }
}
