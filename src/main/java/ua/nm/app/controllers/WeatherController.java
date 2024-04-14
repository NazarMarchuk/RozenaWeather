package ua.nm.app.controllers;

import java.util.List;
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
    WeatherModel weather = new WeatherModel();
    LocationModel location = new LocationModel();
    LocationFinder locationFinder = new LocationFinder();

    @GetMapping("")
    public String setInputs(@ModelAttribute("location") String inputtedLocation, Model model) {

        if (!(inputtedLocation == null || inputtedLocation.isEmpty())) {
            location = locationFinder.getLocationCoordinates(inputtedLocation);
        }

        model.addAttribute("weather", weather);
        model.addAttribute("location", location);
        return "weather/start_input_location";
    }

    @GetMapping("/now")
    public String weatherNowPage(Model model) {
        System.out.println("finding weather in: " + location);
        WeatherModel currentWeather = weather.getWeatherOneDayByLocation(location);
        model.addAttribute("weather", currentWeather);
        return "weather/now";
    }

    @GetMapping("/weak")
    public String WeatherWeek(Model model) {
        List<WeatherModel> weakBroadcast = weather.weatherForWeak(location);
        model.addAttribute("weatherList", weakBroadcast);

        return "weather/one_weak";
    }

}
