package com.example.emilioward.mobilecoursework;

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
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.kosalgeek.genasync12.AsyncResponse;
import com.kosalgeek.genasync12.PostResponseAsyncTask;

import java.util.HashMap;

public class UserProfile extends AppCompatActivity implements View.OnClickListener {
    final String LOG = "UserProfile";
    EditText UpName,UpAge,UpGender,UpPassword,UpUsername;
    Button btn_ApplyChange,btn_DeleteProfile;
    private UserData user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);

        UpUsername = (EditText) findViewById(R.id.UpUsername);
        UpName = (EditText) findViewById(R.id.UpName);
        UpAge = (EditText) findViewById(R.id.UpAge);
        UpGender = (EditText) findViewById(R.id.UpGender);
        UpPassword = (EditText) findViewById(R.id.UpPassword);
        btn_ApplyChange = (Button) findViewById(R.id.btn_ApplyChange);
        btn_DeleteProfile = (Button) findViewById(R.id.btn_DeleteProfile);

        btn_ApplyChange.setOnClickListener(this);

        btn_DeleteProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HashMap postData = new HashMap();
                postData.put("username",UpUsername.getText().toString());
                postData.put("mobile","android");
                PostResponseAsyncTask taskDelete = new PostResponseAsyncTask(UserProfile.this, postData, new AsyncResponse() {
                    @Override
                    public void processFinish(String s) {
                        Log.d(LOG,s);
                        if(s.contains("success")){
                            Toast.makeText(UserProfile.this, "Account Deleted", Toast.LENGTH_LONG).show();
                            Intent in = new Intent(UserProfile.this, LoginActivity.class);
                            startActivity(in);
                        }
                        else{
                            Toast.makeText(UserProfile.this, "There was an error deleting the account",Toast.LENGTH_LONG).show();
                        }
                    }
                });
                taskDelete.execute("http://192.168.44.236/courseworkproject/delete_account.php");
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
                Intent i = new Intent(UserProfile.this, Main.class);
                startActivity(i);
                return false;
            }
        });
        return true;
    }

    @Override
    public void onClick(View v) {

        HashMap postData = new HashMap();
        postData.put("username",UpUsername.getText().toString());
        postData.put("name",UpName.getText().toString());
        postData.put("age",UpAge.getText().toString());
        postData.put("gender",UpGender.getText().toString());
        postData.put("password",UpPassword.getText().toString());
        postData.put("mobile","android");
        PostResponseAsyncTask taskUpdate = new PostResponseAsyncTask(UserProfile.this, postData, new AsyncResponse() {
            @Override
            public void processFinish(String s) {
                Log.d(LOG,s);
                if(s.contains("success")){
                    Toast.makeText(UserProfile.this, "Account Details Updated Successfully", Toast.LENGTH_LONG).show();
                    Intent in = new Intent(UserProfile.this, Main.class);
                    startActivity(in);
                }
                else{
                    Toast.makeText(UserProfile.this, "There was an error updating your details",Toast.LENGTH_LONG).show();
                }
            }
        });
        taskUpdate.execute("http://192.168.44.236/courseworkproject/update_account.php");
    }
}

