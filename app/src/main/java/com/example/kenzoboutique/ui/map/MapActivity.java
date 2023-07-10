package com.example.kenzoboutique.ui.map;

import static com.example.kenzoboutique.R.drawable.baseline_location_on_24;
import static com.example.kenzoboutique.R.drawable.baseline_location_on_48;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.widget.TextView;

import com.example.kenzoboutique.BuildConfig;
import com.example.kenzoboutique.R;

import org.osmdroid.config.Configuration;
import org.osmdroid.events.MapEventsReceiver;
import org.osmdroid.tileprovider.tilesource.TileSourceFactory;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.MapEventsOverlay;
import org.osmdroid.views.overlay.Marker;

public class MapActivity extends AppCompatActivity implements MapEventsReceiver {
  private final int REQUEST_PERMISSION_REQUEST_CODE = 1;
  private TextView btnBack;
  private MapView map = null;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_map);
    Configuration.getInstance().setUserAgentValue(BuildConfig.APPLICATION_ID);

    map = (MapView) findViewById(R.id.mapView);
    btnBack = (TextView) findViewById(R.id.btnBack);

    initialize();
    initPoiHandler();
  }

  @Override
  protected void onResume() {
    super.onResume();
    map.onResume();
  }

  @Override
  protected void onPause() {
    super.onPause();
    map.onPause();
  }

  private void initialize() {
    btnBack.setOnClickListener(v -> {onBackPressed();});
  }

  private void initPoiHandler() {
    MapEventsOverlay mapEventsOverlay = new MapEventsOverlay(this, this);
    map.getOverlays().add(0, mapEventsOverlay);
    map.setTileSource(TileSourceFactory.MAPNIK);

    setMarker(-6.915845285206341, 107.58613438261567, "Outlet A");
    setMarker(-6.916319633556482, 107.59370478791487, "Outlet B");
    setMarker(-6.912804868628957, 107.59174141113208, "Outlet C");

    map.getController().setZoom(19);
    map.invalidate();
  }

  private void setMarker(double aLatitude, double aLongtitude, String title) {
    GeoPoint startPoint = new GeoPoint(aLatitude, aLongtitude);
    Marker startMarker = new Marker(map);
    startMarker.setPosition(startPoint);
    startMarker.setIcon(ContextCompat.getDrawable(this, baseline_location_on_48));
    startMarker.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM);
    startMarker.setTitle(title);
    map.getController().setCenter(startPoint);
    map.getOverlays().add(startMarker);
  }

  @Override
  public boolean singleTapConfirmedHelper(GeoPoint p) {
    return false;
  }

  @Override
  public boolean longPressHelper(GeoPoint p) {
    return false;
  }
}