package com.example.roma.osmdroid;

import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import org.osmdroid.api.IMapController;
import org.osmdroid.config.Configuration;
import org.osmdroid.tileprovider.tilesource.TileSourceFactory;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.ScaleBarOverlay;

public class MainActivity extends AppCompatActivity {

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
// Мур-манск
        GeoPoint startPoint = new GeoPoint(68.9800, 33.0504);
        mapController.setCenter(startPoint);

        ScaleBarOverlay scaleBarOverlay = new ScaleBarOverlay(mapView);
        scaleBarOverlay.setCentred(true);
// подберите значения самостоятельно
        scaleBarOverlay.setScaleBarOffset(200, 30);
        mapView.getOverlays().add(scaleBarOverlay);

    }
}
