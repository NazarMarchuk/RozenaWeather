package ua.nm.app.controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import ua.nm.app.api.LocationFinder;
import ua.nm.app.models.LocationModel;
import ua.nm.app.models.WeatherModel;

@Controller
@RequestMapping("/weather")
public class WeatherController {
    private final WeatherModel weather;
    private final LocationFinder locationFinder;

    private LocationModel location = new LocationModel();

    @Autowired
    public WeatherController(WeatherModel weather, LocationFinder locationFinder) {
        this.weather = weather;
        this.locationFinder = locationFinder;
    }

    @GetMapping("")
    public String setInputs(@ModelAttribute("location") String inputtedLocation, Model model) {

        if (!(inputtedLocation == null || inputtedLocation.isEmpty())) {
            location = locationFinder.getLocationCoordinates(inputtedLocation);
            WeatherModel currentWeather = weather.getWeatherOneDayByLocation(location);
            model.addAttribute("currentWeather", currentWeather);
        }

        model.addAttribute("weather", weather);
        model.addAttribute("location", location);

        return "weather/start_input_location";
    }

    @GetMapping("/weak")
    public String weatherWeek(Model model) {
        List<WeatherModel> weakBroadcast = weather.weatherForWeek(location);
        model.addAttribute("weatherList", weakBroadcast);

        return "weather/one_weak";
    }

    @GetMapping("/day")
    public String weatherDay(Model model) {
        List<WeatherModel> dayBroadcast = weather.weatherForDayByHour(location);
        model.addAttribute("weatherList", dayBroadcast);

        String [] weatherTime = new String[25];
        weatherTime[0] = "Now";
        weatherTime[1] = "1 Hour";

        int hour = 2;
        while (hour < 25) {
            weatherTime[hour] = hour + " Hours";
            hour++;
        }
        model.addAttribute("weatherTime", weatherTime);

        return "weather/one_day";
    }

}
