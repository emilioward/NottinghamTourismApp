package com.example.emilioward.mobilecoursework;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

/**
 * Created by emilioward on 06/03/2016.
 */
public class Events {

    @SerializedName("eventid")
    public int eventid;
    @SerializedName("name")
    public String name;
    @SerializedName("time")
    public String time;
    @SerializedName("location")
    public String location;
    @SerializedName("price")
    public int price;
    @SerializedName("description")
    public String description;
    @SerializedName("imageurl")
    public String imageurl;

}
