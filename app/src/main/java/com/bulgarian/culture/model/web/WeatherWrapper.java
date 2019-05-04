package com.bulgarian.culture.model.web;

import java.util.List;

public class WeatherWrapper {

    private Coord coord;
    private List<Weather> weather;
    private String base;
    private Main main;
    private int visibility;
    private Wind wind;
    private Clouds clouds;
    private int dt;
    private Sys sys;
    private int id;
    private String name;
    private int code;

    protected WeatherWrapper() {

    }

    public static class Builder {
        private WeatherWrapper weatherWrapper;

        public Builder() {
            weatherWrapper = new WeatherWrapper();
        }

        public Builder coord(Coord coord) {
            weatherWrapper.coord = coord;
            return this;
        }

        public Builder weather(List<Weather> weather) {
            weatherWrapper.weather = weather;
            return this;
        }

        public Builder base(String base) {
            weatherWrapper.base = base;
            return this;
        }

        public Builder main(Main main) {
            weatherWrapper.main = main;
            return this;
        }

        public Builder visibility(int visibility) {
            weatherWrapper.visibility = visibility;
            return this;
        }

        public Builder wind(Wind wind) {
            weatherWrapper.wind = wind;
            return this;
        }

        public Builder clouds(Clouds clouds) {
            weatherWrapper.clouds = clouds;
            return this;
        }

        public Builder dt(int dt) {
            weatherWrapper.dt = dt;
            return this;
        }

        public Builder sys(Sys sys) {
            weatherWrapper.sys = sys;
            return this;
        }

        public Builder id(int id) {
            weatherWrapper.id = id;
            return this;
        }

        public Builder name(String name) {
            weatherWrapper.name = name;
            return this;
        }

        public Builder code(int code) {
            weatherWrapper.code = code;
            return this;
        }

        public WeatherWrapper build() {
            return weatherWrapper;
        }
    }

    public Coord getCoord() {
        return coord;
    }

    public List<Weather> getWeather() {
        return weather;
    }

    public String getBase() {
        return base;
    }

    public Main getMain() {
        return main;
    }

    public int getVisibility() {
        return visibility;
    }

    public Wind getWind() {
        return wind;
    }

    public Clouds getClouds() {
        return clouds;
    }

    public int getDt() {
        return dt;
    }

    public Sys getSys() {
        return sys;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getCode() {
        return code;
    }
}
