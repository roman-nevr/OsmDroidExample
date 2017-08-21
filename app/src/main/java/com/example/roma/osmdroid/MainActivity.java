package com.example.roma.osmdroid;

import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.roma.osmdroid.json.Geo;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import org.osmdroid.api.IMapController;
import org.osmdroid.config.Configuration;
import org.osmdroid.tileprovider.tilesource.TileSourceFactory;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.Polygon;
import org.osmdroid.views.overlay.ScaleBarOverlay;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
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

        Polygon polygon = new Polygon();
        loadFromAssets();
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
            Gson gson = new GsonBuilder().create();
            geo = gson.fromJson(json_geo, Geo.class);
        }catch (IOException e){

        }

    }
}
