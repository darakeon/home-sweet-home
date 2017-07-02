package com.darakeon.hsh;

import android.location.Address;
import android.location.Geocoder;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

import com.darakeon.hsh.db.DBConnection;
import com.darakeon.hsh.db.Place;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback
{

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.maps);

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment =
            (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);

        mapFragment.getMapAsync(this);
    }


    @Override
    public void onMapReady(GoogleMap googleMap)
    {
        DBConnection db = new DBConnection(this);
        ArrayList<Place> placeList = db.GetAll();

        for (int p = 0; p < placeList.size(); p++)
        {
            Place place = placeList.get(p);
            LatLng latLng = getLatLng(place);

            if (latLng == null)
                continue;

            MarkerOptions options =
                new MarkerOptions()
                    .position(latLng)
                    .title(place.Value.toString());

            googleMap.addMarker(options);
        }

        //mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
    }



    Geocoder geo = new Geocoder(this);

    private LatLng getLatLng(Place place)
    {
        List<Address> addressList = null;

        try
        {
            addressList = geo.getFromLocationName(place.Address, 1);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        if (addressList == null || addressList.size() == 0)
        {
            return null;
        }

        Address address = addressList.get(0);
        double latitude = address.getLatitude();
        double longitude = address.getLongitude();

        return new LatLng(latitude, longitude);
    }



}
