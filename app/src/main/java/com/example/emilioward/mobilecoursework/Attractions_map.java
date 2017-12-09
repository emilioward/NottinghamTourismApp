package com.example.emilioward.mobilecoursework;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.amigold.fundapter.BindDictionary;
import com.amigold.fundapter.FunDapter;
import com.amigold.fundapter.extractors.StringExtractor;
import com.amigold.fundapter.interfaces.DynamicImageLoader;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.kosalgeek.android.json.JsonConverter;
import com.kosalgeek.genasync12.AsyncResponse;
import com.kosalgeek.genasync12.PostResponseAsyncTask;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.io.Serializable;
import java.util.ArrayList;

public class Attractions_map extends AppCompatActivity implements OnMapReadyCallback, AsyncResponse,AdapterView.OnItemClickListener {

    private GoogleMap mMap;
    final String LOG = "AttractionsList";
    private ArrayList<Places> attractionsList;
    private ListView lvAttract;
    TextView txtName, txtDesc;
    ImageView imageViewAttract;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attractions_map);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        //Places place = (Places) getIntent().getSerializableExtra("Places");
        ImageLoader.getInstance().init(UILConfig.config(Attractions_map.this));

        //txtName=(TextView)findViewById(R.id.txt_name);
        //txtDesc=(TextView)findViewById(R.id.txt_description);
        //imageViewAttract=(ImageView)findViewById(R.id.imageurl);

        /*
        if(place!=null){
            txtName.setText(place.name);
            txtDesc.setText(place.description);
            ImageLoader.getInstance().displayImage(place.imageurl, imageViewAttract);
        }
        */

        PostResponseAsyncTask taskread = new PostResponseAsyncTask(Attractions_map.this,this);
        taskread.execute("http://192.168.44.236/courseworkproject/get_all_attractions.php");

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
                Intent i = new Intent(Attractions_map.this, Main.class);
                startActivity(i);
                return false;
            }
        });
        return true;
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;


        LatLng Nottingham = new LatLng(52.9548, -1.1581);
        LatLng NottinghamCastle = new LatLng(52.9493, -1.1546);
        LatLng NottinghamPlayhouse = new LatLng(52.9537, -1.1577);
        LatLng NottinghamRoyalTheatre = new LatLng(52.9551, -1.1511);
        LatLng NottinghamVictoriacentre = new LatLng(52.9564, -1.1472);
        LatLng NottinghamWollatonhall = new LatLng(52.9478, -1.20959);
        LatLng NottinghamForestFootball = new LatLng(52.9399, -1.13258);
        LatLng NottinghamYeoldpub = new LatLng(52.9493, -1.1526);
        LatLng NottinghamMarketSquare = new LatLng(52.9527, -1.1446);


        float zoomVariable = 14;

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        mMap.setMyLocationEnabled(true);


        mMap.addMarker(new MarkerOptions().position(NottinghamCastle).title("Nottingham Castle"));
        mMap.addMarker(new MarkerOptions().position(NottinghamPlayhouse).title("Nottingham Playhouse"));
        mMap.addMarker(new MarkerOptions().position(NottinghamRoyalTheatre).title("Nottingham Royal Theatre"));
        mMap.addMarker(new MarkerOptions().position(NottinghamVictoriacentre).title("Victoria Centre"));
        mMap.addMarker(new MarkerOptions().position(NottinghamWollatonhall).title("Wollaton Hall"));
        mMap.addMarker(new MarkerOptions().position(NottinghamForestFootball).title("Nottingham Forest Ground"));
        mMap.addMarker(new MarkerOptions().position(NottinghamYeoldpub).title("Ye Olde Trip to Jerusalem"));
        mMap.addMarker(new MarkerOptions().position(NottinghamMarketSquare).title("Market Square"));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(Nottingham, zoomVariable));


    }

    @Override
    public void processFinish(String s) {
        attractionsList = new JsonConverter<Places>().toArrayList(s, Places.class);
        BindDictionary<Places> dict = new BindDictionary<Places>();
        dict.addStringField(R.id.txt_name, new StringExtractor<Places>() {
            @Override
            public String getStringValue(Places place, int position) {
                return place.name;
            }
        });

        dict.addStringField(R.id.txt_description, new StringExtractor<Places>() {
            @Override
            public String getStringValue(Places place, int position) {
                return place.description;
            }
        });

        dict.addStringField(R.id.txt_Address, new StringExtractor<Places>() {
            @Override
            public String getStringValue(Places place, int position) {
                return place.address;
            }
        });

        dict.addStringField(R.id.txt_Weburl, new StringExtractor<Places>() {
            @Override
            public String getStringValue(Places place, int position) {
                return place.weburl;
            }
        });

        dict.addDynamicImageField(R.id.imageurl, new StringExtractor<Places>() {
            @Override
            public String getStringValue(Places place, int position) {
                return place.imageurl;
            }
        }, new DynamicImageLoader() {
            @Override
            public void loadImage(String url, ImageView imageView) {
                ImageLoader.getInstance().displayImage(url, imageView);
            }
        });


        FunDapter< Places > adapter = new FunDapter<>(
                Attractions_map.this, attractionsList, R.layout.all_attractions_listlayout, dict);

        lvAttract = (ListView)findViewById(R.id.lvAttract);
        lvAttract.setAdapter(adapter);
        lvAttract.setOnItemClickListener(this);
    }
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Places SelectedEvent = attractionsList.get(position);
        Intent in = new Intent(Attractions_map.this, AttractionDetail.class);
        in.putExtra("Places", (Serializable) SelectedEvent);
        startActivity(in);
    }
}
