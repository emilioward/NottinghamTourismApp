package com.example.emilioward.mobilecoursework;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;

/**
 * Created by emilioward on 15/03/2016.
 */
public class AttractionDetail extends AppCompatActivity {

    TextView txtName, txtDesc, txtAddress, txtWebURL;
    ImageView imageViewAttract;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attractions_map);

        ImageLoader.getInstance().init(UILConfig.config(AttractionDetail.this));
        Places place = (Places) getIntent().getSerializableExtra("Places");

        txtName=(TextView)findViewById(R.id.txt_name);
        txtDesc=(TextView)findViewById(R.id.txt_description);
        txtAddress=(TextView)findViewById(R.id.txt_Address);
        txtWebURL=(TextView)findViewById(R.id.txt_Weburl);
        imageViewAttract=(ImageView)findViewById(R.id.imageurl);


        if(place!=null){
            txtName.setText(place.name);
            txtDesc.setText(place.description);
            txtAddress.setText(place.address);
            txtWebURL.setText(place.weburl);
            ImageLoader.getInstance().displayImage(place.imageurl, imageViewAttract);
        }


    }

}
