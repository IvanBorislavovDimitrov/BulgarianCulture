package com.bulgarian.culture.listener;

import android.location.Location;
import android.location.LocationListener;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.TextView;

import com.bulgarian.culture.R;
import com.bulgarian.culture.activity.WeatherActivity;
import com.bulgarian.culture.detector.TownDetector;
import com.bulgarian.culture.model.web.WeatherWrapper;
import com.bulgarian.culture.weather_client.DefaultWeatherService;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

public class WeatherLocationListener implements LocationListener {

    private final DefaultWeatherService weatherService;
    private final WeatherActivity weatherActivity;

    public WeatherLocationListener(DefaultWeatherService weatherService, WeatherActivity weatherActivity) {
        this.weatherService = weatherService;
        this.weatherActivity = weatherActivity;
    }

    @Override
    public void onLocationChanged(Location location) {
        TextView temperature = weatherActivity.findViewById(R.id.temperatureTextViewWeatherActivity);
        TextView townMessage = weatherActivity.findViewById(R.id.townMessageTextViewWeatherActivity);
        TownDetector townDetector = new TownDetector(weatherActivity, location.getLatitude(), location.getLongitude());
        updateWeather(townDetector, temperature, townMessage);
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }

    private void updateWeather(TownDetector townDetector, TextView temperature, TextView townMessage) {
        AsyncTask<String, Void, WeatherWrapper> weather;
        String townName = null;
        try {
            townName = townDetector.getCurrentTownName();
            weather = weatherService.execute(townName);
        } catch (IOException e) {
            throw new IllegalArgumentException(e.getMessage());
        }
        updateWeather(weather, temperature);
        updateTownMessage(townMessage, townName);
    }

    private void updateTownMessage(TextView townMessage, String townName) {
        townMessage.setText("Town: " + townName);
    }

    private void updateWeather(AsyncTask<String, Void, WeatherWrapper> weather, TextView temperature) {
        try {
            WeatherWrapper weatherWrapper = weather.get();
            temperature.setText(String.valueOf(weatherWrapper.getMain().getTemp()));
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
