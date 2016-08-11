package com.application;

import com.model.City;

import java.io.IOException;
import java.util.List;

import com.managers.CSVManager;
import com.managers.JsonDataManager;
import com.util.Util;

public class Application {

    public static void main(String[] args) throws IOException {


        String city = args[0];
        String parentDir = new java.io.File(".").getCanonicalPath();
        List<City> cities = JsonDataManager.getCityData(city);
        String filePath = CSVManager.writeCitiesToCSV(cities, city, parentDir);

        if (Util.isNullOrEmpty(filePath)) {
            System.out.println("Could not save csv file.");
        } else {
            System.out.println("File was successfully saved to: " + filePath);
        }
    }
}
