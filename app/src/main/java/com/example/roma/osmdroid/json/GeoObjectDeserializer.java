package com.example.roma.osmdroid.json;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;

/**
 * Created by roma on 24.08.17.
 */

public class GeoObjectDeserializer implements JsonDeserializer<Geometry> {
    @Override
    public Geometry deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        Geometry geometry = null;
        JsonObject jsonObject = json.getAsJsonObject();
        String type = jsonObject.get("type").getAsString();
        if (type.equals("Polygon")){
            geometry = context.deserialize(json, MyPolygon.class);
        }
        if (type.equals("Point")){
            geometry = context.deserialize(json, Point.class);
        }
        return geometry;
    }
}
