package com.example.roma.osmdroid;

import android.graphics.Color;
import android.preference.PreferenceManager;
import android.support.v4.graphics.ColorUtils;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.roma.osmdroid.json.Feature;
import com.example.roma.osmdroid.json.Geo;
import com.example.roma.osmdroid.json.GeoObjectDeserializer;
import com.example.roma.osmdroid.json.Geometry;
import com.example.roma.osmdroid.json.MyPolygon;
import com.example.roma.osmdroid.json.Point;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.osmdroid.api.IMapController;
import org.osmdroid.config.Configuration;
import org.osmdroid.tileprovider.tilesource.TileSourceFactory;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.ItemizedIconOverlay;
import org.osmdroid.views.overlay.OverlayItem;
import org.osmdroid.views.overlay.Polygon;
import org.osmdroid.views.overlay.ScaleBarOverlay;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private Geo geo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //important! set your user agent to prevent getting banned from the osm servers
        Configuration.getInstance().load(this, PreferenceManager.getDefaultSharedPreferences(this));
        setContentView(R.layout.activity_main);

        MapView mapView = (MapView) findViewById(R.id.map_view);
        // здесь задается поставщик картинки карты
        // можно выбрать подходящий вариант, подробнее см. в документации
        mapView.setTileSource(TileSourceFactory.MAPNIK);

        mapView.setBuiltInZoomControls(true);
        mapView.setMultiTouchControls(true);

        IMapController mapController = mapView.getController();
        mapController.setZoom(11);
        GeoPoint startPoint = new GeoPoint(68.9800, 33.0504);
        mapController.setCenter(startPoint);

        ScaleBarOverlay scaleBarOverlay = new ScaleBarOverlay(mapView);
        scaleBarOverlay.setCentred(true);
        scaleBarOverlay.setScaleBarOffset(200, 30);
        mapView.getOverlays().add(scaleBarOverlay);

        loadFromAssets();
        List<OverlayItem> items = new ArrayList<>();
        ItemizedIconOverlay.OnItemGestureListener<OverlayItem> listener = new ItemizedIconOverlay.OnItemGestureListener<OverlayItem>() {
            @Override
            public boolean onItemSingleTapUp(int index, OverlayItem item) {
                return false;
            }

            @Override
            public boolean onItemLongPress(int index, OverlayItem item) {
                return false;
            }
        };
        ItemizedIconOverlay<OverlayItem> itemItemizedIconOverlay = new ItemizedIconOverlay<OverlayItem>(items, listener, this);
        for (Feature feature : geo.features) {
            if (feature.geometry instanceof MyPolygon){
                Polygon polygon = new Polygon();
                MyPolygon myPolygon = (MyPolygon) feature.geometry;
                List<GeoPoint> geoPoints = new ArrayList<>();
                for (List<Double> doubles : myPolygon.coordinates.get(0)) {
                    GeoPoint geoPoint = new GeoPoint(doubles.get(1), doubles.get(0));
                    geoPoints.add(geoPoint);
                }
                polygon.setPoints(geoPoints);
                int fillColor = Color.parseColor(feature.properties.fill);
                float fillOpacity = feature.properties.fillOpacity;
//                Color.argb(fillOpacity, Color.red(fillColor), Color.green(fillColor), Color.blue(fillColor));
                int color = ColorUtils.setAlphaComponent(fillColor, (int) (255 * fillOpacity));
                int strokeColor = Color.parseColor(feature.properties.stroke);
                int strokeWidth = Integer.parseInt(feature.properties.strokeWidth);
                polygon.setFillColor(color);
                polygon.setStrokeColor(strokeColor);
                polygon.setStrokeWidth(strokeWidth);

                mapView.getOverlays().add(polygon);
            }
            if (feature.geometry instanceof Point){
                Point point = (Point) feature.geometry;
                GeoPoint geoPoint = new GeoPoint(point.coordinates.get(1), point.coordinates.get(0));
                OverlayItem overlayItem = new OverlayItem("", "", geoPoint);
                itemItemizedIconOverlay.addItem(overlayItem);
                mapView.getController().setCenter(geoPoint);
//                items.add(overlayItem);
            }
        }
        mapView.getOverlays().add(itemItemizedIconOverlay);
    }

    private void loadFromAssets(){
        try {
            StringBuilder buf=new StringBuilder();
            InputStream json=getAssets().open("geo");
            BufferedReader in=new BufferedReader(new InputStreamReader(json, "UTF-8"));
            String str;

            while ((str=in.readLine()) != null) {
                buf.append(str);
            }
            String json_geo = buf.toString();
            GeoObjectDeserializer deserializer = new GeoObjectDeserializer();
            Gson gson = new GsonBuilder()
                    .registerTypeAdapter(Geometry.class, deserializer)
                    .create();
            geo = gson.fromJson(json_geo, Geo.class);
        }catch (IOException e){

        }

    }
}
