package ua.nm.app.api;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import ua.nm.app.models.LocationModel;
import ua.nm.app.dao.LocationDAO;

@Component
@PropertySource("classpath:weatherApi.properties")
public class LocationFinder {
    @Value("${location.base_url}")
    private String baseUrl;
    @Value("${api_key}")
    private String apiKey;

    private LocationModel location = new LocationModel();
    private LocationDAO locationDAO;

    @Autowired
    public void setLocationDAO(LocationDAO locationDAO) {
        this.locationDAO = locationDAO;
    }

    public LocationModel getLocationCoordinates(String name) {
        //use location from db if it's present by name
        LocationModel dbLocation =  locationDAO.getLocation(name);

        if (dbLocation != null) {
            return dbLocation;
        }

        String params = String.format("?q=%s&appid=%s&limit=%s", name.replace(" ", "+"), apiKey, "1");

        URL url;
        JSONObject resultJson;

        {
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
