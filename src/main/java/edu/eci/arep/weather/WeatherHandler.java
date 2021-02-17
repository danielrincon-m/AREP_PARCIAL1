package edu.eci.arep.weather;

import spark.Request;
import spark.Response;
import spark.Route;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

public class WeatherHandler {
    public static Route handle = (Request req, Response res) -> {
        String baseURL = "https://api.openweathermap.org/data/2.5/weather";
        String appid = "14448a44c8d0ca3f6244009a1b9eee49";
        HashMap<String, String> paramMap = new HashMap<>();

        String place = req.queryParams("lugar");
        if (place != null) {
            res.status(200);
            res.type("application/json");

            paramMap.put("q", place);
            paramMap.put("appid", appid);
            String URL = createURL(baseURL, paramMap);
            return readURL(URL);
        } else {
            res.status(400);
            return "Error: No se especificó un lugar";
        }
    };

    private static String createURL(String baseURL, HashMap<String, String> params) {
        StringBuilder sb = new StringBuilder();
        sb.append(baseURL).append("?");
        for (Map.Entry<String, String> e : params.entrySet()) {
            sb.append(e.getKey()).append("=").append(e.getValue()).append("&");
        }
        sb.deleteCharAt(sb.length() - 1);
        return sb.toString();
    }

    private static String readURL(String URL) throws IOException {
        InputStream is = new URL(URL).openStream();
        BufferedReader rd = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8));
        StringBuilder sb = new StringBuilder();
        int cp;
        while ((cp = rd.read()) != -1) {
            sb.append((char) cp);
        }
        return sb.toString();
    }
}
