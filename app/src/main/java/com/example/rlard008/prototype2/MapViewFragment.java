package com.example.rlard008.prototype2;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;

/**
 * Created by sudhakar on 1/27/17.
 */

public class MapViewFragment extends Fragment  {



    private GoogleMap mMap;
    MapView mapview;
    LatLng kapilmalhar,cdacActs,sanghli,chennai,delhi;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        super.onCreateView(inflater, container, savedInstanceState);
        View root = inflater.inflate(R.layout.activity_maps, container, false);
        mapview=(MapView)root.findViewById(R.id.mapView);
        mapview.onCreate(savedInstanceState);
      //  mapView.onResume();
        try {
            MapsInitializer.initialize(getActivity().getApplicationContext());
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        mapview.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap googleMap) {
                mMap=googleMap;
                mMap.setMyLocationEnabled(true);

                kapilmalhar = new LatLng(18.559718,73.791604);
                googleMap.addMarker(new MarkerOptions().position(kapilmalhar).title("Machine_1").snippet("Rlard,Baner"));
                cdacActs = new LatLng(18.564901,73.807288);
                googleMap.addMarker(new MarkerOptions().position(cdacActs).title("Machine_2").snippet("CdacActsPune"));
                // For zooming automatically to the location of the marker
                CameraPosition cameraPosition = new CameraPosition.Builder().target(kapilmalhar).zoom(13).build();
                googleMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
//                Polyline line = mMap.addPolyline(new PolylineOptions()
//                        .add(kapilmalhar,cdacActs)
//                        .width(5)
//                        .color(Color.RED));
            }
        });




        return root;
    }

    @Override
    public void onResume() {
        super.onResume();
        mapview.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        mapview.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mapview.onDestroy();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mapview.onLowMemory();
    }


}
