package com.bulgarian.culture.weather_client;

public interface WeatherService {

    String BASE_WEATHER_URL = "http://api.openweathermap.org/data/2.5/weather";

    default String exchangeUrl(String url) {
        return BASE_WEATHER_URL + url;
    }
}
