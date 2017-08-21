package com.example.roma.osmdroid.json;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by roma on 21.08.17.
 */

public class Geo {
    @SerializedName("type")
    @Expose
    public String type;
    @SerializedName("metadata")
    @Expose
    public Metadata metadata;
    @SerializedName("features")
    @Expose
    public List<Feature> features = null;

}
