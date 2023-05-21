package com.location.router;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class Controller {
    private HttpServletRequest request;
    private HttpServletResponse response;


    public Controller(HttpServletRequest request,HttpServletResponse response){
        this.request =request;
        this.response =response;
    }
    public String getUserLocation(String ipAddress) throws IOException {
//        String ipAddress = "20.51.237.16";
        System.out.println("ip: "+ipAddress);
        URL url = new URL("https://ipapi.co/" + ipAddress + "/json/");
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");

        BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        StringBuilder response = new StringBuilder();
        String line;

        while ((line = reader.readLine()) != null) {
            response.append(line);
        }
        reader.close();

        JSONObject jsonObject = new JSONObject(response.toString());
        String country = jsonObject.getString("country");
//        String region = jsonObject.getString("region");
//        String city = jsonObject.getString("city");
//        String timeZone = jsonObject.getString("timezone");

        return country;
    }

    public void redirectBasedOnLocation() throws IOException {

        String ipAddress = request.getRemoteAddr();

        // Use the appropriate geolocation API to get the user's location details
        String location = getUserLocation(ipAddress);

        String redirectUrl;

        // Determine the destination URL based on the user's location
        if (location.equals("US")) {
            redirectUrl = "https://20.51.237.16";
        } else if (location.equals("AsiaPacific")) {
            redirectUrl = "http://destination2.com";
        } else {
            redirectUrl = "http://defaultdestination.com";
        }
        // Redirect the user to the appropriate URL
        response.sendRedirect(redirectUrl);
    }

    @GetMapping("/publish")
    public void redirectPublish() throws IOException {
        redirectBasedOnLocation();
    }

    @GetMapping("/traffic")
    public void redirectSetQuery() throws IOException {
        redirectBasedOnLocation();
    }
}
