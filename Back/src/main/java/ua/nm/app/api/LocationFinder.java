package ua.nm.app.api;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ua.nm.app.dao.LocationDAO;
import ua.nm.app.models.LocationModel;

@Component
public class LocationFinder {
    private LocationModel location = new LocationModel();

    @Autowired
    private LocationDAO locationDAO;

    public LocationModel getLocationCoordinates(String name) {
        //use location from db if it's present by name
        LocationModel dbLocation =  locationDAO.getLocation(name);

        if (dbLocation != null) {
            return dbLocation;
        }

        String baseUrl = "http://api.openweathermap.org/geo/1.0/direct";
        String apiKey = "60059ed55a69c0f3cef120a39a895b29";
        String params = String.format("?q=%s&appid=%s&limit=%s", name, apiKey, "1");

        URL url;
        JSONObject resultJson;

        {
            try {
                url = new URL(baseUrl + params);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();

                connection.setRequestMethod("GET");

                int responseCode = connection.getResponseCode();
                System.out.println("LocationFinder: Response code: " + responseCode);

                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                StringBuilder response = new StringBuilder();
                String inputLine;

                while ((inputLine = reader.readLine()) != null) {
                    response.append(inputLine);
                }

                reader.close();

                connection.disconnect();

                resultJson = new JSONArray(response.toString()).getJSONObject(0);

                location.setLat(String.valueOf(resultJson.getDouble("lat")));
                location.setLon(String.valueOf(resultJson.getDouble("lon")));
                location.setName(resultJson.getString("name"));
                location.setCountry(resultJson.getString("country"));
                location.setState(resultJson.getString("state"));

                if (locationDAO.getLocation(location.getName()) == null) {
                    locationDAO.saveLocation(location);
                }

            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        return location;
    }
}
