package com.bulgarian.culture.activity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bulgarian.culture.R;
import com.bulgarian.culture.model.web.WeatherWrapper;
import com.bulgarian.culture.weather_client.DefaultWeatherService;

import java.util.concurrent.ExecutionException;

public class WeatherActivity extends AppCompatActivity {

    private DefaultWeatherService weatherService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);
        initDependencies();
        updateWeather();
    }

    private void updateWeather() {
        TextView temperature = findViewById(R.id.temperatureTextViewWeatherActivity);
        AsyncTask<String, Void, WeatherWrapper> weather = weatherService.execute("Pazardzhik");
        updateWeather(weather, temperature);
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

    private void initDependencies() {
        weatherService = new DefaultWeatherService();
    }
}
