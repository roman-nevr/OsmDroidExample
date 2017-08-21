package com.example.roma.osmdroid.json;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by roma on 21.08.17.
 */

public class Properties {
    @SerializedName("fill")
    @Expose
    public String fill;
    @SerializedName("fill-opacity")
    @Expose
    public Float fillOpacity;
    @SerializedName("stroke")
    @Expose
    public String stroke;
    @SerializedName("stroke-width")
    @Expose
    public String strokeWidth;
    @SerializedName("stroke-opacity")
    @Expose
    public Float strokeOpacity;
    @SerializedName("marker-color")
    @Expose
    public String markerColor;
}
