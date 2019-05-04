package com.bulgarian.culture.util;

import com.bulgarian.culture.model.web.Clouds;
import com.bulgarian.culture.model.web.Coord;
import com.bulgarian.culture.model.web.Main;
import com.bulgarian.culture.model.web.Sys;
import com.bulgarian.culture.model.web.Weather;
import com.bulgarian.culture.model.web.WeatherWrapper;
import com.bulgarian.culture.model.web.Wind;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public final class ResponseEntityMapper {
    public WeatherWrapper mapWeatherWrapper(Map<String, Object> resource) {
        return new WeatherWrapper.Builder()
                .coord(parseCoord(resource))
                .weather(parseWeather(resource))
                .base(getResourceAttribute(resource, "base", String.class))
                .main(parseMain(resource))
                .visibility(getResourceAttribute(resource, "visibility", Integer.class))
                .wind(parseWind(resource))
                .clouds(parseClouds(resource))
                .dt(getResourceAttribute(resource, "dt", Integer.class))
                .sys(parseSys(resource))
                .id(zeroIfNull(getResourceAttribute(resource, "id", Integer.class)))
                .name(getResourceAttribute(resource, "name", String.class))
                .code(getResourceAttribute(resource, "cod", Integer.class))
                .build();

    }

    @SuppressWarnings("unchecked")
    private Coord parseCoord(Map<String, Object> resource) {
        Map<String, Object> coordMap = getResourceAttribute(resource, "coord", Map.class);
        double lon = zeroIfNull(getResourceAttribute(coordMap, "lon", Double.class));
        double lat = zeroIfNull(getResourceAttribute(coordMap, "lat", Double.class));
        return new Coord(lon, lat);
    }

    private List<Weather> parseWeather(Map<String, Object> resource) {
        List<?> weatherResource = getResourceAttribute(resource, "weather", List.class);
        return parseWeather(weatherResource);
    }

    @SuppressWarnings("unchecked")
    private List<Weather> parseWeather(List<?> weatherResource) {
        return weatherResource.stream()
                .map(wr -> {
                    Map<String, Object> resource = (Map<String, Object>) wr;
                    int id = zeroIfNull(getResourceAttribute(resource, "id", Integer.class));
                    String main = getResourceAttribute(resource, "main", String.class);
                    String description = getResourceAttribute(resource, "description", String.class);
                    String icon = getResourceAttribute(resource, "icon", String.class);
                    return new Weather(id, main, description, icon);
                }).collect(Collectors.toList());
    }

    @SuppressWarnings("unchecked")
    private Main parseMain(Map<String, Object> resource) {
        Map<String, Object> mainMap = getResourceAttribute(resource, "main", Map.class);
        double temp = zeroIfNull(getResourceAttribute(mainMap, "temp", Double.class));
        int pressure = zeroIfNull(getResourceAttribute(mainMap, "pressure", Integer.class));
        int humidity = zeroIfNull(getResourceAttribute(mainMap, "humidity", Integer.class));
        int tempMin = zeroIfNull(getResourceAttribute(mainMap, "temp_min", Integer.class));
        int tempMax = zeroIfNull(getResourceAttribute(mainMap, "temp_max", Integer.class));
        return new Main(temp, pressure, humidity, tempMin, tempMax);
    }

    @SuppressWarnings("unchecked")
    private Wind parseWind(Map<String, Object> resource) {
        Map<String, Object> windMap = getResourceAttribute(resource, "wind", Map.class);
        double speed = zeroIfNull(getResourceAttribute(windMap, "speed", Double.class));
        double deg = zeroIfNull(getResourceAttribute(windMap, "deg", Double.class));
        return new Wind(speed, deg);
    }

    @SuppressWarnings("unchecked")
    private Clouds parseClouds(Map<String, Object> resource) {
        Map<String, Object> cloudsMap = getResourceAttribute(resource, "clouds", Map.class);
        int all = zeroIfNull(getResourceAttribute(cloudsMap, "all", Integer.class));
        return new Clouds(all);
    }

    @SuppressWarnings("unchecked")
    private Sys parseSys(Map<String, Object> resource) {
        Map<String, Object> sysMap = getResourceAttribute(resource, "sys", Map.class);
        int type = zeroIfNull(getResourceAttribute(sysMap, "type", Integer.class));
        int id = zeroIfNull(getResourceAttribute(sysMap, "id", Integer.class));
        double message = zeroIfNull(getResourceAttribute(sysMap, "message", Double.class));
        String country = getResourceAttribute(sysMap, "country", String.class);
        int sunrise = zeroIfNull(getResourceAttribute(sysMap, "sunrise", Integer.class));
        int sunset = zeroIfNull(getResourceAttribute(sysMap, "sunset", Integer.class));
        return new Sys(type, id, message, country, sunrise, sunset);
    }

    @SuppressWarnings("unchecked")
    private <T> T getResourceAttribute(Map<String, Object> resource, String attributeName, Class<T> targetClass) {
        if (resource == null) {
            return null;
        }
        Object attributeValue = resource.get(attributeName);
        if (attributeName == null) {
            return null;
        }
        if (attributeValue instanceof String) {
            return (T) String.valueOf(attributeValue);
        }
        if (targetClass == Integer.class || targetClass == Boolean.class || targetClass == Map.class || targetClass == List.class || targetClass == Double.class || targetClass == Long.class) {
            return (T) attributeValue;
        }
        throw new IllegalArgumentException("Error during mapping - unsupported class for attribute mapping " + targetClass.getName());
    }

    private Integer zeroIfNull(Integer number) {
        if (number == null) {
            return 0;
        }
        return number;
    }

    private Double zeroIfNull(Double number) {
        if (number == null) {
            return 0D;
        }
        return number;
    }
}
