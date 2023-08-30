package com.example.maps;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;

import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.example.maps.databinding.ActivityMapsBinding;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback, GoogleMap.OnMarkerClickListener {

    private GoogleMap mMap;
    private ActivityMapsBinding binding;
    private final LatLng PALMAS = new LatLng(-10.1868191,-48.3336937);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMapsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        Marker marker = mMap.addMarker(
            new MarkerOptions()
                .position(PALMAS)
                .title("Palmas - TO")
                .snippet("Praça dos Girassóis")
                .icon(
                        BitmapDescriptorFactory.fromResource(R.drawable.girassol)
                )
        );
        marker.setTag(0);
        mMap.moveCamera(CameraUpdateFactory.newLatLng(PALMAS));
        mMap.setOnMarkerClickListener(this);
    }

    @Override
    public boolean onMarkerClick(@NonNull Marker marker) {
        Integer count = (Integer) marker.getTag();
        if (count != null) {
            count++;
            marker.setTag(count);
            Toast.makeText(this, "Clicado " +count + "x", Toast.LENGTH_SHORT).show();
        }
        return false;
    }
}