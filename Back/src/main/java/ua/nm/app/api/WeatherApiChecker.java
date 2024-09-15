package ua.nm.app.api;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

@Component
public class WeatherApiChecker {
    @Value("${weather.base_url}")
    private String baseUrl;
    @Value("${api_key}")
    private String apiKey;

    public JSONObject checkWeather(String lat, String lon) {
        String part = "minutely,alerts";
        String units = "metric";
        String params = String.format("?lat=%s&lon=%s&exclude=%s&appid=%s&units=%s", lat, lon, part, apiKey, units);

        URL url;
        JSONObject resultJson;

        try {
            url = new URL(baseUrl + params);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            connection.setRequestMethod("GET");

            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuilder response = new StringBuilder();
            String inputLine;

            while ((inputLine = reader.readLine()) != null) {
                response.append(inputLine);
            }

            reader.close();
            connection.disconnect();

            resultJson = new JSONObject(response.toString());

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    return resultJson;

    }

}
