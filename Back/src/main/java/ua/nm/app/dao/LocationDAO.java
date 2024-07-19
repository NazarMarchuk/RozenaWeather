package ua.nm.app.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ua.nm.app.models.LocationModel;

@Component
public class LocationDAO {
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public LocationDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void saveLocation(LocationModel location) {
        jdbcTemplate.update("INSERT INTO Location VALUES (1, ?, ?, ?, ?, ?)", location.getName(),
            location.getLat(), location.getLon(), location.getCountry(), location.getState());
    }

    public LocationModel getLocation(String name) {
        return jdbcTemplate.query("SELECT * FROM Location WHERE name=?",
            new BeanPropertyRowMapper<>(LocationModel.class), name).stream().findFirst().orElse(null);
    }
}
