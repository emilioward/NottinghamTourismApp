package com.example.emilioward.mobilecoursework;

/**
 * Created by emilioward on 28/02/2016.
 */

import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.AdapterView;

import com.amigold.fundapter.BindDictionary;
import com.amigold.fundapter.FunDapter;
import com.amigold.fundapter.extractors.StringExtractor;
import com.amigold.fundapter.interfaces.DynamicImageLoader;
import com.kosalgeek.android.json.JsonConverter;
import com.kosalgeek.genasync12.AsyncResponse;
import com.kosalgeek.genasync12.PostResponseAsyncTask;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.io.Serializable;
import java.util.ArrayList;

public class AllActivities extends AppCompatActivity implements AsyncResponse, AdapterView.OnItemClickListener {

    final String LOG = "AllActivities";
    private ArrayList<Events> eventList;
    private ListView lvEvent;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.displaylist);

        ImageLoader.getInstance().init(UILConfig.config(AllActivities.this));

        PostResponseAsyncTask taskread = new PostResponseAsyncTask(AllActivities.this,this);
    taskread.execute("http://192.168.44.236/courseworkproject/get_all_activities.php");

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
                Intent i = new Intent(AllActivities.this, Main.class);
                startActivity(i);
                return false;
            }
        });
        return true;
    }

    //Setting up the database and displaying the data. This was followed on a tutorial which is credited in the report
    @Override
    public void processFinish(String s) {
        eventList = new JsonConverter<Events>().toArrayList(s, Events.class);
        BindDictionary<Events> dict = new BindDictionary<Events>();
        dict.addStringField(R.id.txt_name, new StringExtractor<Events>() {
            @Override
            public String getStringValue(Events event, int position) {
                return event.name;
            }
        });

        dict.addStringField(R.id.txt_time, new StringExtractor<Events>() {
            @Override
            public String getStringValue(Events event, int position) {
                return event.time;
            }
        });

        dict.addStringField(R.id.txt_location, new StringExtractor<Events>() {
            @Override
            public String getStringValue(Events event, int position) {
                return event.location;
            }
        });

        dict.addStringField(R.id.txt_description, new StringExtractor<Events>() {
            @Override
            public String getStringValue(Events event, int position) {
                return event.description;
            }
        });

        dict.addStringField(R.id.txt_price, new StringExtractor<Events>() {
            @Override
            public String getStringValue(Events event, int position) {
                return "" + event.price;
            }
        });

        dict.addDynamicImageField(R.id.imageurl, new StringExtractor<Events>() {
                    @Override
                    public String getStringValue(Events event, int position) {
                        return event.imageurl;
                    }
                }, new DynamicImageLoader() {
                    @Override
                    public void loadImage(String url, ImageView imageView) {
                        ImageLoader.getInstance().displayImage(url, imageView);
                    }
                });


                FunDapter < Events > adapter = new FunDapter<>(
                        AllActivities.this, eventList, R.layout.all_activities_listlayout, dict);

        lvEvent = (ListView)findViewById(R.id.lvEvent);
        lvEvent.setAdapter(adapter);
        lvEvent.setOnItemClickListener(this);

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Events SelectedEvent = eventList.get(position);
        Intent in = new Intent(AllActivities.this, ActivityDetail.class);
        in.putExtra("Events", (Serializable) SelectedEvent);
        startActivity(in);
    }
}
