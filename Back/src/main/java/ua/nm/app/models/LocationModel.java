package ua.nm.app.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "location")
public class LocationModel {

    @Id
    @Column(name="name")
    private String name;
    @Column(name="lat")
    private String lat;
    @Column(name="lon")
    private String lon;
    @Column(name="country")
    private String country;
    @Column(name="state")
    private String state;

    public LocationModel() {
    }

    public String getLat() {
        return lat;
    }
    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLon() {
        return lon;
    }

    public void setLon(String lon) {
        this.lon = lon;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getState() {
        return state;
    }
    public void setState(String state) { this.state = state; }

}
