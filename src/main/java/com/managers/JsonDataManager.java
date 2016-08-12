package com.managers;

import com.model.City;
import com.util.Util;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.List;

/**
 * Manages fetching JSON from api.
 */
public class JsonDataManager {

    private static String BASE_URL = "http://api.goeuro.com/api/v2/position/suggest/en/";

    /**
     * Looks up the city in GoEuro api.
     *
     * @param city city name
     * @return list of places that have been returned by api
     */
    public static List<City> getCityData(String city) {
        String fullUrl = BASE_URL + city;
        String citiesData = fetchJson(fullUrl);
        if (Util.isNullOrEmpty(citiesData) || Util.isEmptyJsonArray(citiesData)) {
            System.out.println("Place " + city + " was not found.");
            return null;
        }
        List<City> citiesList = jsonArrayToCities(citiesData);
        return citiesList;
    }


    /**
     * Method that maps Json as String to City class.
     *
     * @param citiesJson JsonArray with cities data.
     * @return List of cities.
     */
    private static List<City> jsonArrayToCities(String citiesJson) {
        ObjectMapper mapper = new ObjectMapper();
        TypeReference<List<City>> typeRef = new TypeReference<List<City>>() {
        };
        List<City> cities = null;
        try {
            cities = mapper.readValue(citiesJson, typeRef);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return cities;
    }

    /**
     * Fetches Json from given url.
     *
     * @param path String url to query
     * @return Json as String obtained by quering api at url
     */
    public static String fetchJson(String path) {
        String errorMessage = "";
        try {
            URL url = new URL(path);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            String responseMessage = connection.getResponseMessage();
            int code = connection.getResponseCode();

            if (code != HttpURLConnection.HTTP_OK) {
                System.out.println(code + " : " + responseMessage);
            } else {
                InputStream is = connection.getInputStream();
                BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
                String jsonText = readJson(rd);
                is.close();
                return jsonText;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Reads Json as String from Buffer reader.
     *
     * @param rd BufferReader
     * @return Json as String
     */
    public static String readJson(BufferedReader rd) {
        StringBuilder sb = new StringBuilder();
        int cp;
        try {
            while ((cp = rd.read()) != -1) {
                sb.append((char) cp);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sb.toString();
    }


}
