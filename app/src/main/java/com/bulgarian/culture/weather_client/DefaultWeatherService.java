package com.bulgarian.culture.weather_client;

import android.os.AsyncTask;

import com.bulgarian.culture.factory.RestTemplateFactory;

import java.util.Map;

public class DefaultWeatherService extends AsyncTask<String, Void, String> implements WeatherService {

    private static final String BASE_WEATHER_URL = "http://api.openweathermap.org/data/2.5/weather";
    private static final String APP_ID = "a1af72065a69b0a97c187b726958ba1d";
    private static final String UNITS = "metric";

    @Override
    protected String doInBackground(String... args) {
        String townName = args[0];
        String url = BASE_WEATHER_URL + "?q={townName},bg&APPID={appId}&units={metric}";
        Map<?, ?> result = RestTemplateFactory.getDefaultRestTemplate().getForObject(url, Map.class, townName, APP_ID, UNITS);
        return result.toString();
    }

}
