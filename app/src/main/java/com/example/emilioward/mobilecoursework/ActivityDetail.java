package com.example.emilioward.mobilecoursework;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;

public class ActivityDetail extends AppCompatActivity {

    TextView txtName, txtPrice, txtLocation, txtDesc, txtTime;
    ImageView imageView2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.displaylist);

        ImageLoader.getInstance().init(UILConfig.config(ActivityDetail.this));
        Events event = (Events) getIntent().getSerializableExtra("Events");

        txtName=(TextView)findViewById(R.id.txtName);
        txtPrice=(TextView)findViewById(R.id.txtPrice);
        txtLocation=(TextView)findViewById(R.id.txtLocation);
        txtDesc=(TextView)findViewById(R.id.txtDesc);
        txtTime=(TextView)findViewById(R.id.txtTime);
        imageView2=(ImageView)findViewById(R.id.imageView2);

        if(event!=null){
            txtName.setText(event.name);
            txtPrice.setText(""+event.price);
            txtLocation.setText(event.location);
            txtDesc.setText(event.description);
            txtTime.setText(""+event.time);

            ImageLoader.getInstance().displayImage(event.imageurl, imageView2);
        }
    }
}
