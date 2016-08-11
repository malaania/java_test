package com.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.util.Collection;
import java.util.List;

/**
 * Util class with helper functions for Strings and Collections
 */
public class Util {

    /**
     * Checks if String is null or empty.
     * @param str String to check
     * @return true if given String is null or empty, false otherwise
     */
    public static boolean isNullOrEmpty(String str) {
        return ( str == null || str.isEmpty());
    }

    /**
     * Checks if Collection is null or empty.
     * @param col Collection to check
     * @return true if given Collection is null or empty, false otherwise
     */
    public static boolean isNullOrEmpty(Collection col) {
        return ( col == null || col.isEmpty());
    }

    /**
     * Checks if JSONArray is empty
     * @param jsonArr jsonArray as String
     * @return true if JSONArray is empty, false otherwise
     */
    public static boolean isEmptyJsonArray(String jsonArr)
    {
        return jsonArr.equals("[]");
    }

    /**
     * Joins elements of a list of Strings to one string with specified separator
     * between each two elements.
     * @param elements list of Strings to join
     * @param separator separator to put between each two elements of the list
     * @return elements of a list in a string separated by given separator
     */
    public static String join(List<String> elements, String separator) {
        StringBuilder sb = new StringBuilder();
        int len = elements.size();
        for (int i = 0; i < len; i++) {
            String temp = elements.get(i);
            if (temp != null) {
                sb.append(temp);
                if (i != (len - 1)) {
                    sb.append(separator);
                }
            }
        }
        return sb.toString();
    }



}
