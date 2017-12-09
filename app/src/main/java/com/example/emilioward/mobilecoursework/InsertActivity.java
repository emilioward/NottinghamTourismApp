package com.example.emilioward.mobilecoursework;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.kosalgeek.genasync12.AsyncResponse;
import com.kosalgeek.genasync12.PostResponseAsyncTask;

import java.util.HashMap;

public class InsertActivity extends AppCompatActivity implements View.OnClickListener {

    final String LOG = "InsertActivity";

    private EditText txt_name, txt_price, txt_time, txt_description, txt_location, txt_imageurl;
    private Button btnCreate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        txt_name = (EditText)findViewById(R.id.txt_name);
        txt_price = (EditText)findViewById(R.id.txt_price);
        txt_time = (EditText)findViewById(R.id.txt_time);
        txt_description = (EditText)findViewById(R.id.txt_description);
        txt_location = (EditText)findViewById(R.id.txt_location);
        txt_imageurl = (EditText)findViewById(R.id.txt_imageurl);
        btnCreate = (Button)findViewById(R.id.btnCreate);
        btnCreate.setOnClickListener(this);

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
                Intent i = new Intent(InsertActivity.this, Main.class);
                startActivity(i);
                return false;
            }
        });
        return true;
    }

    @Override
    public void onClick(View v) {

        HashMap postData = new HashMap();
        postData.put("name",txt_name.getText().toString());
        postData.put("time",txt_time.getText().toString());
        postData.put("location",txt_location.getText().toString());
        postData.put("price",txt_price.getText().toString());
        postData.put("description",txt_description.getText().toString());
        postData.put("imageurl",txt_imageurl.getText().toString());
        postData.put("mobile","android");
        PostResponseAsyncTask taskInsert = new PostResponseAsyncTask(InsertActivity.this, postData, new AsyncResponse() {
            @Override
            public void processFinish(String s) {
                Log.d(LOG,s);
                if(s.contains("success")){
                    Toast.makeText(InsertActivity.this, "Event Created Successfully", Toast.LENGTH_LONG).show();
                    Intent in = new Intent(InsertActivity.this, Activities.class);
                    startActivity(in);
                }
            }
        });
        taskInsert.execute("http://192.168.44.236/courseworkproject/create_activity.php");
    }
}
