package com.sheela.mobilestore.ui;

import androidx.fragment.app.FragmentActivity;

import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.sheela.mobilestore.R;
import com.sheela.mobilestore.model.LatitudeLongitude;

import java.util.ArrayList;
import java.util.List;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        List<LatitudeLongitude> longitudeList = new ArrayList<>();
        longitudeList.add(new LatitudeLongitude(27.7052354, 85.3294158, "MobileStore,Dillibazar"));
        longitudeList.add(new LatitudeLongitude(27.7064748, 85.3142013, "MobileStore,DurbarMarg"));
        longitudeList.add(new LatitudeLongitude(27.7415528, 85.3346216, "MobileStore,Bansbari"));
        longitudeList.add(new LatitudeLongitude(27.6856712, 85.299826, "MobileStore,sanepa"));

        CameraUpdate center, zoom;
        for (int i = 0; i < longitudeList.size(); i++) {
            center = CameraUpdateFactory.newLatLng(new LatLng(longitudeList.get(i).getLat(),
                    longitudeList.get(i).getLon()));
            zoom = CameraUpdateFactory.zoomTo(13);

            mMap.addMarker(new MarkerOptions().position(new LatLng(longitudeList.get(i).getLat(),
                    longitudeList.get(i).getLon())).title(longitudeList.get(i).getMarker()));

            mMap.moveCamera(center);

            mMap.animateCamera(zoom);

            mMap.setMapType(mMap.MAP_TYPE_HYBRID);

            mMap.getUiSettings().setZoomControlsEnabled(true);
//        CameraUpdate zoom;
//        zoom=CameraUpdateFactory.zoomTo(16);
//        // Add a marker in Sydney and move the camera
//        LatLng sydney = new LatLng(27.706841,85.330329 );
//        mMap.addMarker(new MarkerOptions().position(sydney).title("We are here"));
//        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
//        mMap.animateCamera(zoom);
//        mMap.getUiSettings().setZoomControlsEnabled(true);
        }
    }
}
