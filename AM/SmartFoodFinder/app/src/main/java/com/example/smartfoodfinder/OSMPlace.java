package com.example.smartfoodfinder;

import com.google.gson.annotations.SerializedName;

public class OSMPlace {
    @SerializedName("display_name")
    public String displayName;

    @SerializedName("lat")
    public String lat;

    @SerializedName("lon")
    public String lon;
}