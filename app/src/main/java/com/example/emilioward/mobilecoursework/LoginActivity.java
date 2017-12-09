package com.example.emilioward.mobilecoursework;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.common.data.DataHolder;
import com.kosalgeek.genasync12.AsyncResponse;
import com.kosalgeek.genasync12.PostResponseAsyncTask;

import java.util.HashMap;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    final String LOG = "LoginActivity";

    Button btnSignin;
    Button btnCreateAccount, skipbutton;
    EditText etUsername, etPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
  

        etUsername = (EditText) findViewById(R.id.etUsername);
        etPassword = (EditText) findViewById(R.id.etPassword);
        btnSignin = (Button) findViewById(R.id.btnSignin);
        btnCreateAccount = (Button) findViewById(R.id.btnCreateAccount);
        skipbutton = (Button) findViewById(R.id.skipbutton);

        btnSignin.setOnClickListener(this);

        skipbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent skp = new Intent(LoginActivity.this,Main.class);
                startActivity(skp);
            }
        });

        btnCreateAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent Cr = new Intent(LoginActivity.this,CreateAccount.class);
                startActivity(Cr);
            }
        });

    }

    @Override
    public void onBackPressed() {
        // disable going back to the phone main page
        moveTaskToBack(true);
    }

    //Sign in
    @Override
    public void onClick(View v) {
        HashMap postData = new HashMap();


        String password = etPassword.getText().toString();

        postData.put("txtUsername", etUsername.getText().toString());
        postData.put("txtPassword", password);

        PostResponseAsyncTask login = new PostResponseAsyncTask(LoginActivity.this, postData, new AsyncResponse() {
            @Override
            public void processFinish(String s) {
                Log.d(LOG, s);
                if(s.contains("success")){
                    Toast.makeText(LoginActivity.this, "Successful Login",Toast.LENGTH_LONG).show();
                    Intent in = new Intent(LoginActivity.this,Main.class);
                    String username = etUsername.getText().toString();
                    in.putExtra("username",username);
                    startActivity(in);
                }
                else{
                    Toast.makeText(LoginActivity.this, "Username or Password Incorrect",Toast.LENGTH_LONG).show();
                }
            }
        });
        login.execute("http://192.168.44.236/courseworkproject/login.php");
    }
}

