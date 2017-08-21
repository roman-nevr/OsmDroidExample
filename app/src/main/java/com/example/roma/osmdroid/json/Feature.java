package com.example.roma.osmdroid.json;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by roma on 21.08.17.
 */

public class Feature {
    @SerializedName("type")
    @Expose
    public String type;
    @SerializedName("id")
    @Expose
    public Integer id;
    @SerializedName("geometry")
    @Expose
    public Geometry geometry;
    @SerializedName("properties")
    @Expose
    public Properties properties;
}
