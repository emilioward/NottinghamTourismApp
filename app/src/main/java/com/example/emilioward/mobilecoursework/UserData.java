package com.example.emilioward.mobilecoursework;

import com.google.gson.annotations.SerializedName;

/**
 * Created by emilioward on 16/03/2016.
 */
public class UserData {

        @SerializedName("name")
        public String name;
        @SerializedName("age")
        public String age;
        @SerializedName("gender")
        public String gender;
        @SerializedName("email")
        public String email;
        @SerializedName("username")
        public String username;
        @SerializedName("password")
        public String password;

}
