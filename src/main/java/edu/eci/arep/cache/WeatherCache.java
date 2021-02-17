package edu.eci.arep.cache;

import java.util.HashMap;

public class WeatherCache {
    private static final HashMap<String, String> weatherCache = new HashMap<>();

    public static String searchPlace(String place) {
        return weatherCache.getOrDefault(place, null);
    }

    public static void putPlace(String place, String json) {
        weatherCache.put(place, json);
    }
}
