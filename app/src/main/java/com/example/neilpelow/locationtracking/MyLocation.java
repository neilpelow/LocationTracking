package com.example.neilpelow.locationtracking;

import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

public class MyLocation extends AppCompatActivity implements LocationListener {

    private TextView locationText;
    private LocationManager locationManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        locationText = (TextView) findViewById(R.id.locationText);
        setUpLocation();
    }

    public void onLocationChanged(Location location) {
        String latestLocation = "";
        if (location != null) {
            Log.w("onLocationChanged runs", " ");
            latestLocation = String.format("Current Location \n Longitute: %1$s \n Latitude: %2$s",
                    location.getLongitude(), location.getLatitude());
        }
        Toast.makeText(MyLocation.this, latestLocation, Toast.LENGTH_LONG).show();
        locationText.setText(latestLocation);
    }

    public void onProviderDisabled(String provider) {
        //Code
    }

    public void onProviderEnabled(String provider) {
        //Code
    }

    public void onStatusChanged(String provider, int status, Bundle extras) {
        //Code
    }

    public void setUpLocation() {
        try {
            Log.w("setUpLocation runs", " ");
            locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
            locationManager.requestLocationUpdates(
                    LocationManager.GPS_PROVIDER,
                    500,
                    1,
                    this);

        } catch (SecurityException e) {
            Log.d("MyLocation :", "u dun fkd up", e);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        try {
        locationManager.requestLocationUpdates(
                LocationManager.GPS_PROVIDER,
                500,
                1,
                this);
        } catch (SecurityException e) {
        Log.d("MyLocation :", "u dun fkd up", e);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        try {
            locationManager.removeUpdates(this);
        } catch (SecurityException e) {
            Log.d("MyLocation :", "u dun fkd up", e);
        }

    }


}//end MyLocation
