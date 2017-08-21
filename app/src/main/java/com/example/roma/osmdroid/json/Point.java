package com.example.roma.osmdroid.json;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by roma on 21.08.17.
 */

public class Point extends Geometry {
    @SerializedName("coordinates")
    @Expose
    public List<Double> coordinates = null;
//    @SerializedName("type")
//    @Expose
//    public String type;
}
