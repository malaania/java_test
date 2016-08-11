package com.model;

import com.managers.CSVManager;
import org.codehaus.jackson.annotate.JsonProperty;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Represents the city.
 */
public class City implements CsvEntry {

    @JsonProperty("_id")
    private long id;

    private Object key;

    private String name;

    private String fullName;

    @JsonProperty("iata_airport_code")
    private String iataAirportCode;

    private String type;

    private String country;

    @JsonProperty("geo_position")
    private GeoPosition geoPosition;

    private long locationId;

    private boolean inEurope;

    private long countryId;

    private String countryCode;

    private boolean coreCountry;

    private Double distance;

    Map<String, String> names;

    Map<String, String> alternativeNames;

    public City() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Object getKey() {
        return key;
    }

    public void setKey(Object key) {
        this.key = key;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getIataAirportCode() {
        return iataAirportCode;
    }

    public void setIataAirportCode(String iataAirportCode) {
        this.iataAirportCode = iataAirportCode;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public GeoPosition getGeoPosition() {
        return geoPosition;
    }

    public void setGeoPosition(GeoPosition geoPosition) {
        this.geoPosition = geoPosition;
    }

    public long getLocationId() {
        return locationId;
    }

    public void setLocationId(long locationId) {
        this.locationId = locationId;
    }

    public boolean isInEurope() {
        return inEurope;
    }

    public void setInEurope(boolean inEurope) {
        this.inEurope = inEurope;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public boolean isCoreCountry() {
        return coreCountry;
    }

    public void setCoreCountry(boolean coreCountry) {
        this.coreCountry = coreCountry;
    }

    public Double getDistance() {
        return distance;
    }

    public void setDistance(Double distance) {
        this.distance = distance;
    }

    public long getCountryId() {
        return countryId;
    }

    public void setCountryId(long countryId) {
        this.countryId = countryId;
    }

    public Map<String, String> getNames() {
        return names;
    }

    public void setNames(Map<String, String> names) {
        this.names = names;
    }

    public Map<String, String> getAlternativeNames() {
        return alternativeNames;
    }

    public void setAlternativeNames(Map<String, String> alternativeNames) {
        this.alternativeNames = alternativeNames;
    }


    public List<String> prepareCsvEntry() {
        List<String> data = new ArrayList<String>();
        data.add(Long.toString(id));
        data.add(name);
        data.add(type);
        data.add(Double.toString(geoPosition.getLatitude()));
        data.add(Double.toString(geoPosition.getLongitude()));
        return data;
    }
}
