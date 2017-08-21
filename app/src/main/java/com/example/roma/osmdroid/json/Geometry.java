package com.example.roma.osmdroid.json;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by roma on 21.08.17.
 */

public class Geometry {
    @SerializedName("type")
    @Expose
    public String type;
}
