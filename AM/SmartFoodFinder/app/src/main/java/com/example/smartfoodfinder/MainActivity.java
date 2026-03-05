package com.example.smartfoodfinder;

import android.os.Bundle;
import android.widget.SearchView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import org.maplibre.android.MapLibre;
import org.maplibre.android.annotations.MarkerOptions;
import org.maplibre.android.camera.CameraUpdateFactory;
import org.maplibre.android.geometry.LatLng;
import org.maplibre.android.geometry.LatLngBounds;
import org.maplibre.android.maps.MapLibreMap;
import org.maplibre.android.maps.MapView;
import org.maplibre.android.maps.OnMapReadyCallback;
import org.maplibre.android.maps.Style;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private MapView mapView;
    private MapLibreMap mapLibreMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MapLibre.getInstance(this);
        setContentView(R.layout.activity_main);

        mapView = findViewById(R.id.mapView);
        mapView.onCreate(savedInstanceState);

        mapView.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(@NonNull MapLibreMap map) {
                mapLibreMap = map;

                map.setStyle("https://basemaps.cartocdn.com/gl/positron-gl-style/style.json", new Style.OnStyleLoaded() {
                    @Override
                    public void onStyleLoaded(@NonNull Style style) {
                        LatLng gorzow = new LatLng(52.7368, 15.2288);
                        map.animateCamera(CameraUpdateFactory.newLatLngZoom(gorzow, 14));
                    }
                });
            }
        });

        SearchView searchView = findViewById(R.id.searchView);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                searchView.clearFocus();
                if (!query.isEmpty()) searchOSM(query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) { return false; }
        });
    }

    private void searchOSM(String query) {
        if (mapLibreMap == null) return;

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://nominatim.openstreetmap.org")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        OSMService service = retrofit.create(OSMService.class);
        Call<List<OSMPlace>> call = service.searchPlaces(query + " Gorzów Wielkopolski", "json", 20);

        call.enqueue(new Callback<List<OSMPlace>>() {
            @Override
            public void onResponse(@NonNull Call<List<OSMPlace>> call, @NonNull Response<List<OSMPlace>> response) {
                if (response.body() == null || response.body().isEmpty()) {
                    Toast.makeText(MainActivity.this, "Brak wyników w Twojej okolicy", Toast.LENGTH_SHORT).show();
                    return;
                }

                mapLibreMap.clear();

                List<LatLng> coords = new ArrayList<>();
                for (OSMPlace p : response.body()) {
                    LatLng pos = new LatLng(Double.parseDouble(p.lat), Double.parseDouble(p.lon));
                    mapLibreMap.addMarker(new MarkerOptions().position(pos).title(p.displayName));
                    coords.add(pos);
                }

                LatLngBounds.Builder builder = new LatLngBounds.Builder();
                for (LatLng p : coords) builder.include(p);

                mapView.post(() -> mapLibreMap.animateCamera(CameraUpdateFactory.newLatLngBounds(builder.build(), 150)));
            }

            @Override
            public void onFailure(@NonNull Call<List<OSMPlace>> call, @NonNull Throwable t) {
                Toast.makeText(MainActivity.this, "Błąd sieci: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override protected void onStart() { super.onStart(); mapView.onStart(); }
    @Override protected void onResume() { super.onResume(); mapView.onResume(); }
    @Override protected void onPause() { super.onPause(); mapView.onPause(); }
    @Override protected void onStop() { super.onStop(); mapView.onStop(); }
    @Override protected void onDestroy() { super.onDestroy(); mapView.onDestroy(); }
    @Override public void onLowMemory() { super.onLowMemory(); mapView.onLowMemory(); }
}