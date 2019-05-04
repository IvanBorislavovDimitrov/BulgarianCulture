package com.bulgarian.culture.weather_client;

import android.os.AsyncTask;

import com.bulgarian.culture.factory.RestTemplateFactory;
import com.bulgarian.culture.model.web.WeatherWrapper;
import com.bulgarian.culture.util.ResponseEntityMapper;

import java.util.Map;

public class DefaultWeatherService extends AsyncTask<String, Void, WeatherWrapper> {

    private static final String BASE_WEATHER_URL = "http://api.openweathermap.org/data/2.5/weather";
    private static final String APP_ID = "a1af72065a69b0a97c187b726958ba1d";
    private static final String UNITS = "metric";

    private ResponseEntityMapper responseEntityMapper = new ResponseEntityMapper();

    @Override
    protected WeatherWrapper doInBackground(String... args) {
        String townName = args[0];
        Map<String, Object> resource = RestTemplateFactory.getDefaultRestTemplate()
                .getForObject(exchangeUrl("?q={townName},bg&APPID={appId}&units={metric}"), Map.class, townName, APP_ID, UNITS);
        return responseEntityMapper.mapWeatherWrapper(resource);
    }

    private String exchangeUrl(String url) {
        return BASE_WEATHER_URL + url;
    }
}
