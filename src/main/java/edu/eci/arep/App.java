package edu.eci.arep;

import edu.eci.arep.weather.WeatherHandler;
import spark.Request;
import spark.Response;

import static spark.Spark.get;
import static spark.Spark.port;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
        port(getPort());
        get("/clima", WeatherHandler.handle);
        get("/", (Request req, Response res) -> {
            res.redirect("/clima");
            return null;
        });
    }

    static int getPort() {
        if (System.getenv("PORT") != null) {
            return Integer.parseInt(System.getenv("PORT"));
        }
        return 4567; //returns default port if heroku-port isn't set
    }
}
