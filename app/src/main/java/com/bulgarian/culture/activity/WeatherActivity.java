package com.bulgarian.culture.activity;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.bulgarian.culture.R;
import com.bulgarian.culture.listener.WeatherLocationListener;
import com.bulgarian.culture.weather_client.DefaultWeatherService;

public class WeatherActivity extends AppCompatActivity {

    private DefaultWeatherService weatherService;
    private LocationManager locationManager;
    private LocationListener locationListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);
        initDependencies();
        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        updateLocation();
    }

    private void updateLocation() {
        if (checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{
                    Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.ACCESS_COARSE_LOCATION,
                    Manifest.permission.INTERNET
            }, 10);
        }
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 5000,
                1000, locationListener);
    }

    private void initDependencies() {
        weatherService = new DefaultWeatherService();
        locationListener = new WeatherLocationListener(weatherService, this);
    }
}
