package ua.nm.app.models;

import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ua.nm.app.api.WeatherApiChecker;

@Component
public class WeatherModel {
    private static final String WEATHER = "weather";
    private static final String TEMP = "temp";

    private final WeatherApiChecker weatherChecker;

    private LocationModel location;
    private String temperature;
    private String temperatureMax;
    private String temperatureMin;
    private String temperatureFeelsLike;
    private String condition;
    private String weatherDescription;

    @Autowired
    public WeatherModel(WeatherApiChecker weatherChecker) {
        this.weatherChecker = weatherChecker;
    }

    public String getWeatherDescription() {
        return weatherDescription;
    }

    public void setWeatherDescription(String weatherDescription) {
        this.weatherDescription = weatherDescription;
    }

    public String getTemperatureMax() {
        return temperatureMax;
    }

    public void setTemperatureMax(String temperatureMax) {
        this.temperatureMax = temperatureMax;
    }

    public String getTemperatureMin() {
        return temperatureMin;
    }

    public void setTemperatureMin(String temperatureMin) {
        this.temperatureMin = temperatureMin;
    }

    public String getTemperatureFeelsLike() {
        return temperatureFeelsLike;
    }

    public void setTemperatureFeelsLike(String temperatureFeelsLike) {
        this.temperatureFeelsLike = temperatureFeelsLike;
    }

    public LocationModel getLocation() {
        return location;
    }

    public String getTemperature() {
        return temperature;
    }

    public String getCondition() {
        return condition;
    }

    public void setLocation(LocationModel location) {
        this.location = location;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public WeatherModel getWeatherOneDayByLocation(LocationModel location) {
        WeatherModel currentWeather = new WeatherModel(weatherChecker);

        JSONObject data =
            weatherChecker.checkWeather(location.getLat(), location.getLon()).getJSONObject("current");

        currentWeather.setTemperature(String.valueOf(data.getDouble(TEMP)));
        currentWeather.setCondition(data.getJSONArray(WEATHER).getJSONObject(0).getString("main"));
        currentWeather.setLocation(location);

        return currentWeather;
    }

    public List<WeatherModel> weatherForWeek (LocationModel location){
        JSONArray data = weatherChecker.checkWeather(location.getLat(), location.getLon()).getJSONArray("daily");
        WeatherModel currentWeather;
        ArrayList<WeatherModel> weakBroadcast = new ArrayList<>();

        int day = 0;
        while (day < 7) {
            currentWeather = new WeatherModel(weatherChecker);
            JSONObject weatherData = data.getJSONObject(day);
            currentWeather.setTemperature(String.valueOf(weatherData.getJSONObject(TEMP).getDouble("day")));
            currentWeather.setTemperatureMax(String.valueOf(weatherData.getJSONObject(TEMP).getDouble("max")));
            currentWeather.setTemperatureMin(String.valueOf(weatherData.getJSONObject(TEMP).getDouble("min")));
            currentWeather.setCondition(weatherData.getJSONArray(WEATHER).getJSONObject(0).getString("main"));
            currentWeather.setWeatherDescription(weatherData.getJSONArray(WEATHER).getJSONObject(0).getString("description"));
            currentWeather.setLocation(location);
            weakBroadcast.add(currentWeather);
            day++;
        }

        return weakBroadcast;
    }

    public List<WeatherModel> weatherForDayByHour (LocationModel location){
        JSONArray data = weatherChecker.checkWeather(location.getLat(), location.getLon()).getJSONArray("hourly");
        WeatherModel currentWeather;
        ArrayList<WeatherModel> dayBroadcast = new ArrayList<>();

        int hour = 0;
        while (hour < 25) {
            currentWeather = new WeatherModel(weatherChecker);
            JSONObject weatherData = data.getJSONObject(hour);
            currentWeather.setTemperature(String.valueOf(weatherData.getDouble(TEMP)));
            currentWeather.setCondition(weatherData.getJSONArray(WEATHER).getJSONObject(0).getString("main"));
            currentWeather.setWeatherDescription(weatherData.getJSONArray(WEATHER).getJSONObject(0).getString("description"));
            currentWeather.setLocation(location);
            dayBroadcast.add(currentWeather);
            hour++;
        }

        return dayBroadcast;
    }


}
