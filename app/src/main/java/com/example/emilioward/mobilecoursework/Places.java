package com.example.emilioward.mobilecoursework;

import com.google.gson.annotations.SerializedName;

/**
 * Created by emilioward on 15/03/2016.
 */
public class Places {
    @SerializedName("attractionid")
    public int attractionid;
    @SerializedName("name")
    public String name;
    @SerializedName("address")
    public String address;
    @SerializedName("website")
    public String weburl;
    @SerializedName("description")
    public String description;
    @SerializedName("imageurl")
    public String imageurl;
}
