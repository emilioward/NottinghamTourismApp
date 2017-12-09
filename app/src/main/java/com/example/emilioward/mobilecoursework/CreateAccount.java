package com.example.emilioward.mobilecoursework;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.kosalgeek.genasync12.AsyncResponse;
import com.kosalgeek.genasync12.PostResponseAsyncTask;

import java.util.HashMap;

public class CreateAccount extends AppCompatActivity implements View.OnClickListener {

    EditText CrName, CrEmail, CrAge, CrGender, CrUsername, CrPassword;
    Button btnCreateAccount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        CrName = (EditText) findViewById(R.id.CrName);
        CrEmail = (EditText) findViewById(R.id.CrEmail);
        CrAge = (EditText) findViewById(R.id.CrAge);
        CrGender = (EditText) findViewById(R.id.CrGender);
        CrUsername = (EditText) findViewById(R.id.CrUsername);
        CrPassword = (EditText) findViewById(R.id.CrPassword);
        btnCreateAccount = (Button) findViewById(R.id.btnCreateAccount);

        btnCreateAccount.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        HashMap postData = new HashMap();
        postData.put("name",CrName.getText().toString());
        postData.put("age",CrAge.getText().toString());
        postData.put("gender",CrGender.getText().toString());
        postData.put("email",CrEmail.getText().toString());
        postData.put("username",CrUsername.getText().toString());
        postData.put("password",CrPassword.getText().toString());
        postData.put("mobile","android");
        PostResponseAsyncTask taskInsert = new PostResponseAsyncTask(CreateAccount.this, postData, new AsyncResponse() {
            @Override
            public void processFinish(String s) {
                if(s.contains("success")){
                    Toast.makeText(CreateAccount.this, "Account Created Successfully", Toast.LENGTH_LONG).show();
                    Intent in = new Intent(CreateAccount.this, LoginActivity.class);
                    startActivity(in);
                }
                else{
                    Toast.makeText(CreateAccount.this, "There was an error creating your account",Toast.LENGTH_LONG).show();
                }
            }
        });
        taskInsert.execute("http://192.168.44.236/courseworkproject/create_account.php");
    }
}
