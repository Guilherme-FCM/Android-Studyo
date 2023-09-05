package com.example.latlngbounds;

import androidx.fragment.app.FragmentActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.MarkerOptions;
import com.example.latlngbounds.databinding.ActivityMapsBinding;

public class MapsActivity extends FragmentActivity
        implements OnMapReadyCallback, GoogleMap.OnCameraIdleListener {

    private GoogleMap mMap;
    private ActivityMapsBinding binding;
    private static final LatLngBounds ADELAIDE = new LatLngBounds(
            new LatLng(-35.0, 138.58),
            new LatLng(-34.9, 138.61)); //restrição

    private static final CameraPosition ADELAIDE_CAMERA =
            new CameraPosition.Builder()
                    .target(new LatLng(-34.92873, 138.59995))
                    .zoom(20.0f).bearing(0).tilt(0).build(); //ponto a ser mostrado

    private float mMinZoom;
    private float mMaxZoom;
    private TextView mCameraTextView;
    private static final float DEFAULT_MIN_ZOOM = 2.0f;
    private static final float DEFAULT_MAX_ZOOM = 22.0f;
    private static final float ZOOM_DELTA = 2.0f;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        binding = ActivityMapsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        mCameraTextView = binding.cameraText;
        resetMinMaxZoom();


        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    private void resetMinMaxZoom() {
        mMinZoom = DEFAULT_MIN_ZOOM;
        mMaxZoom = DEFAULT_MAX_ZOOM;
    }

    private boolean checkReady() {
        if (mMap == null) {
            toast("Error Map");
            return false;
        }
        return true;
    }

    public void onLatLngClampReset(View view) {
        if (!checkReady()) {
            return;
        }
        // Setting bounds to null removes any previously set bounds.
        mMap.setLatLngBoundsForCameraTarget(null);
        toast("LatLngBounds clamp reset.");
    }
    private void toast(String msg) {
        Toast.makeText(getBaseContext(), msg, Toast.LENGTH_SHORT).show();
    }

    public void onClampToAdelaide(View view) {
        if (!checkReady()) {
            return;
        }
        mMap.setLatLngBoundsForCameraTarget(ADELAIDE);
        mMap.animateCamera(CameraUpdateFactory.newCameraPosition(ADELAIDE_CAMERA));
    }

    public void onSetMinZoomClamp(View view) {
        if (!checkReady()) {
            return;
        }
        mMinZoom += ZOOM_DELTA;
        // Constrains the minimum zoom level.
        mMap.setMinZoomPreference(mMinZoom);
        toast("Min zoom preference set to: " + mMinZoom);
    }

    public void onSetMaxZoomClamp(View view) {
        if (!checkReady()) {
            return;
        }
        mMaxZoom -= ZOOM_DELTA;
        // Constrains the maximum zoom level.
        mMap.setMaxZoomPreference(mMaxZoom);
        toast("Max zoom preference set to: " + mMaxZoom);
    }

    public void onMinMaxZoomClampReset(View view) {
        if (!checkReady()) {
            return;
        }
        resetMinMaxZoom();
        mMap.resetMinMaxZoomPreference();
        toast("Min/Max zoom preferences reset.");
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.setOnCameraIdleListener(this);
    }

    @Override
    public void onCameraIdle() {
        mCameraTextView.setText(mMap.getCameraPosition().toString());
    }
}
