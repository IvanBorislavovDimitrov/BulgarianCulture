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
        TownDetector townDetector = new TownDetector(weatherActivity, location.getLatitude(), location.getLongitude());
        updateWeather(townDetector);
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

    private void updateWeather(TownDetector townDetector) {
        AsyncTask<String, Void, WeatherWrapper> weather = null;
        String townName = null;
        try {
            townName = townDetector.getCurrentTownName();
            weather = weatherService.execute(townName);
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
        updateWeather(weather);
        updateTownMessage(townName);
    }

    private void updateWeather(AsyncTask<String, Void, WeatherWrapper> weather) {
        try {
            WeatherWrapper weatherWrapper = weather.get();
            updateWeatherMetrics(weatherWrapper);
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void updateWeatherMetrics(WeatherWrapper weatherWrapper) {
        updateTemperature(weatherWrapper);
        updateTownPressure(weatherWrapper);
        updateHumidity(weatherWrapper);
        updateMaxTemp(weatherWrapper);
        updateMinTemp(weatherWrapper);
    }

    private void updateTemperature(WeatherWrapper weatherWrapper) {
        TextView temperature = weatherActivity.findViewById(R.id.temperatureTextViewWeatherActivity);
        temperature.setText(String.valueOf(weatherWrapper.getMain().getTemp()));
    }

    private void updateTownMessage(String townName) {
        TextView townMessage = weatherActivity.findViewById(R.id.townMessageTextViewWeatherActivity);
        townMessage.setText("Град: " + townName);
    }

    private void updateTownPressure(WeatherWrapper weatherWrapper) {
        TextView pressure = weatherActivity.findViewById(R.id.pressureTextViewWeatherActivity);
        pressure.setText(String.valueOf(weatherWrapper.getMain().getPressure()));
    }

    private void updateHumidity(WeatherWrapper weatherWrapper) {
        TextView humidity = weatherActivity.findViewById(R.id.humidityTextViewWeatherActivity);
        humidity.setText(String.valueOf(weatherWrapper.getMain().getHumidity()));
    }

    private void updateMaxTemp(WeatherWrapper weatherWrapper) {
        TextView maxTemp = weatherActivity.findViewById(R.id.maxTempTextViewWeatherActivity);
        maxTemp.setText(String.valueOf(weatherWrapper.getMain().getTempMax()));
    }

    private void updateMinTemp(WeatherWrapper weatherWrapper) {
        TextView minTemp = weatherActivity.findViewById(R.id.minTempTextViewWeatherActivity);
        minTemp.setText(String.valueOf(weatherWrapper.getMain().getTempMin()));
    }
}
