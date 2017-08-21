package com.example.roma.osmdroid.json;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by roma on 21.08.17.
 */

public class Polygon extends Geometry {
//    @SerializedName("type")
//    @Expose
//    public String type;
    @SerializedName("coordinates")
    @Expose
    public List<List<List<Double>>> coordinates = null;
// double[][][] coordinates;
}
