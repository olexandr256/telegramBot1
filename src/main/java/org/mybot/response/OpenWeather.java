package org.mybot.response;

import com.google.gson.Gson;
import org.mybot.response.ResponseWether;
import org.mybot.response.converteerData.ResponseDate;

import java.io.IOException;
import java.net.ProxySelector;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import static spark.Spark.*;

public class OpenWeather {
    private static final String getRequest = "http://api.openweathermap.org/data/2.5/weather?q=";
    private static final String apiKey = "&APPID=838baf1ed0eeae26247260817281a8a6";
//    private String city;

    public OpenWeather() {

    }

    public String run(String city) throws URISyntaxException, IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI(getRequest+city+apiKey))
                .GET()
                .build();

        HttpResponse<String> response = HttpClient
                .newBuilder()
                .proxy(ProxySelector.getDefault())
                .build()
                .send(request, HttpResponse.BodyHandlers.ofString());

        System.out.println(response.body());

        Gson gson = new Gson();
        ResponseWether responseWether = gson.fromJson(response.body(), ResponseWether.class);
        if (responseWether.getCod()==200) {
            ResponseDate date1 = new ResponseDate(responseWether.getSys().getSunrise());
            ResponseDate date2 = new ResponseDate(responseWether.getSys().getSunset());


            return "температура: " + (Math.round(responseWether.getMain().getTemp() - 273)) + " C\n"
                    + "тиск: " + (responseWether.getMain().getPressure() * 0.75) + " мм. р.с.\n"
                    + "вологість: " + responseWether.getMain().getHumidity() + " %\n"
                    + "хмарність: " + responseWether.getClouds().getAll() + " %\n"
                    + "схід сонця: " + date1.toTime() + "\n"
                    + "захід сонця: " + date2.toTime() + "\n"
//                    +responseWether.getWeather().get(0).getIcon()
                    ;
        }
        else return "місто "+ city +" не знайдено";
    }
}
