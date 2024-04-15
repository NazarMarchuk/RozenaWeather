package ua.nm.app.api;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import org.json.JSONObject;

public class WeatherApiChecker {
    public JSONObject checkWeather(String lat, String lon) {
        String baseUrl = "https://api.openweathermap.org/data/3.0/onecall";
        String part = "minutely,alerts";
        String apiKey = "60059ed55a69c0f3cef120a39a895b29";
        String units = "metric";
        String params = String.format("?lat=%s&lon=%s&exclude=%s&appid=%s&units=%s", lat, lon, part, apiKey, units);

        URL url;
        JSONObject resultJson;

        try {
            url = new URL(baseUrl + params);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            connection.setRequestMethod("GET");

            int responseCode = connection.getResponseCode();
            System.out.println("WeatherChecker: Response code: " + responseCode);

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
