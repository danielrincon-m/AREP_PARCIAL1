package edu.eci.arep;

import com.google.gson.Gson;
import edu.eci.arep.weather.WeatherHandler;

import java.io.FileNotFoundException;
import java.net.URISyntaxException;

import static spark.Spark.*;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
        port(getPort());
        Gson gson = new Gson();

        get("/clima", WeatherHandler.handle);

//        get("*", (Request req, Response res) -> {
//            res.redirect(Path.Web.INDEX);
//            return null;
//        });
    }

    static int getPort() {
        if (System.getenv("PORT") != null) {
            return Integer.parseInt(System.getenv("PORT"));
        }
        return 4567; //returns default port if heroku-port isn't set
    }
}
