package ua.nm.app.models;

import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;
import ua.nm.app.api.WeatherApiChecker;

public class WeatherModel {
    private final WeatherApiChecker weatherChecker = new WeatherApiChecker();
    private LocationModel location;
    private String temperature;
    private String condition;

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
        WeatherModel currentWeather = new WeatherModel();

        JSONObject data =
            weatherChecker.checkWeather(location.getLat(), location.getLon()).getJSONObject("current");

        currentWeather.setTemperature(String.valueOf(data.getDouble("temp")));
        currentWeather.setCondition(data.getJSONArray("weather").getJSONObject(0).getString("main"));
        currentWeather.setLocation(location);

        return currentWeather;
    }

    public List<WeatherModel> weatherForWeak (LocationModel location){
        JSONArray data = weatherChecker.checkWeather(location.getLat(), location.getLon()).getJSONArray("daily");
        WeatherModel currentWeather;
        ArrayList<WeatherModel> weakBroadcast = new ArrayList<>();

        int day = 0;
        while (day < 7) {
            currentWeather = new WeatherModel();
            JSONObject weatherData = data.getJSONObject(day);
            currentWeather.setTemperature(String.valueOf(weatherData.getJSONObject("temp").getDouble("day")));
            currentWeather.setCondition(weatherData.getJSONArray("weather").getJSONObject(0).getString("main"));
            currentWeather.setLocation(location);
            weakBroadcast.add(currentWeather);
            day++;
        }

        return weakBroadcast;
    }


}
