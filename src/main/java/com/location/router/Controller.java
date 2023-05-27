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
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

@RestController
public class Controller {
    private final HttpServletRequest request;
    private final HttpServletResponse response;


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
//    public void redirectBasedOnLocation(String redirect) throws IOException {
//        String ipAddress = request.getRemoteAddr();
//
//        // Use the appropriate geolocation API to get the user's location details
//        String location = getUserLocation(ipAddress);
//
//        String redirectUrl;
//
//        // Determine the destination URL based on the user's location
//        if (location.equals("US")) {
//            redirectUrl = "http://20.51.237.16:8081/" + redirect;
//        } else if (location.equals("AsiaPacific")) {
//            redirectUrl = "http://20.51.237.16:8081/" + redirect;
//        } else {
//            redirectUrl = "http://20.51.237.16:8081/" + redirect;
//        }
//
//        HttpServletResponse httpResponse = (HttpServletResponse) response;
//
//        httpResponse.setStatus(HttpServletResponse.SC_TEMPORARY_REDIRECT);
//        httpResponse.setHeader("Location", redirectUrl);
//        }

//    public void redirectBasedOnLocation(String redirect) throws IOException {
//
//        String ipAddress = request.getRemoteAddr();
//
//        // Use the appropriate geolocation API to get the user's location details
//        String location = getUserLocation(ipAddress);
//
//        String redirectUrl;
//
//        // Determine the destination URL based on the user's location
//        if (location.equals("US")) {
//            redirectUrl = "http://20.51.237.16:8081/"+redirect;
//        } else if (location.equals("AsiaPacific")) {
//            redirectUrl = "http://20.51.237.16:8081/"+redirect;
//        } else {
//            redirectUrl = "http://20.51.237.16:8081/"+redirect;
//        }
//        response.setStatus(307); //this makes the redirection keep your requesting method as is.
//        response.addHeader("content-type","application/json");
//        // Redirect the user to the appropriate URL
//        response.sendRedirect(redirectUrl);
//    }
//    public void redirectBasedOnLocation(String redirect) throws IOException {
//        String ipAddress = request.getRemoteAddr();
//
//        // Use the appropriate geolocation API to get the user's location details
//        String location = getUserLocation(ipAddress);
//
//        String redirectUrl;
//
//        // Determine the destination URL based on the user's location
//        if (location.equals("US")) {
//            redirectUrl = "http://20.51.237.16:8081/" + redirect;
//        } else if (location.equals("AsiaPacific")) {
//            redirectUrl = "http://20.51.237.16:8081/" + redirect;
//        } else {
//            redirectUrl = "http://20.51.237.16:8081/" + redirect;
//        }
//
//        response.setStatus(HttpServletResponse.SC_TEMPORARY_REDIRECT);
//        response.setHeader("Location", redirectUrl);
//    }
//public void redirectBasedOnLocation(String redirect) throws IOException {
//    String ipAddress = request.getRemoteAddr();
//    System.out.println("test");
//
//    // Use the appropriate geolocation API to get the user's location details
//    String location = getUserLocation(ipAddress);
//
//    String redirectUrl;
//
//    // Determine the destination URL based on the user's location
//    if (location.equals("US")) {
//        redirectUrl = "http://20.51.237.16:8081/" + redirect;
//    } else if (location.equals("AsiaPacific")) {
//        redirectUrl = "http://20.51.237.16:8081/" + redirect;
//    } else {
//        redirectUrl = "http://20.51.237.16:8081/" + redirect;
//    }
//
//    response.setStatus(HttpServletResponse.SC_MOVED_PERMANENTLY);
//    response.setHeader("Location", redirectUrl);
//    response.setHeader("Content-Type", "text/event-stream");
//}


//    public void redirectPostBasedOnLocation(String redirect) throws IOException {
//        String ipAddress = request.getRemoteAddr();
//
//        // Use the appropriate geolocation API to get the user's location details
//        String location = getUserLocation(ipAddress);
//
//        String redirectUrl;
//
//        // Determine the destination URL based on the user's location
//        if (location.equals("US")) {
//            redirectUrl = "http://20.51.237.16:8081/" + redirect;
//        } else if (location.equals("AsiaPacific")) {
//            redirectUrl = "http://20.51.237.16:8081/" + redirect;
//        } else {
//            redirectUrl = "http://20.51.237.16:8081/" + redirect;
//        }
//
//        response.setStatus(HttpServletResponse.SC_TEMPORARY_REDIRECT);
//        response.setHeader("Location", redirectUrl);
//        response.addHeader("content-type", "application/json");
//        response.getWriter().write("{ \"query\": \"SELECT  ip@string, eventTimestamp@long FROM networkTraffic\",\n" +
//                "    \"apiKey\": \"Tu_TZ0W2cR92-sr1j-l7ACA.newone.9pej9tihskpx2vYZaxubGW3sFCJLzxe55NRh7T0uk1JMYiRmHdiQsWh5JhRXXT6c418385\",\n" +
//                "    \"id\": \"ZXCVB\"}");
//    }


    public void redirectGetBasedOnLocation(String redirect) throws IOException {
        String ipAddress = request.getRemoteAddr();

        // Use the appropriate geolocation API to get the user's location details
        String location = getUserLocation(ipAddress);

        String redirectUrl;

        // Determine the destination URL based on the user's location
        if (location.equals("US")) {
            redirectUrl = "http://20.51.237.16:8081/" + redirect;
        } else if (location.equals("AsiaPacific")) {
            redirectUrl = "http://20.51.237.16:8081/" + redirect;
        } else {
            redirectUrl = "http://20.51.237.16:8081/" + redirect;
        }

        URL url = new URL(redirectUrl);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");

        int responseCode = connection.getResponseCode();

        if (responseCode == HttpURLConnection.HTTP_OK) {
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line;
            StringBuilder response = new StringBuilder();

            while ((line = reader.readLine()) != null) {
                response.append(line);
            }

            reader.close();

            // Handle the response as needed
            System.out.println("Response: " + response.toString());
        } else {
            // Handle the error response
            System.out.println("Error response code: " + responseCode);
        }
    }


    public void redirectPostBasedOnLocation(String redirect) throws IOException {
        String ipAddress = request.getRemoteAddr();

        // Use the appropriate geolocation API to get the user's location details
        String location = getUserLocation(ipAddress);

        String redirectUrl;

        // Determine the destination URL based on the user's location
        if (location.equals("US")) {
            redirectUrl = "http://20.51.237.16:8081/" + redirect;
        } else if (location.equals("AsiaPacific")) {
            redirectUrl = "http://20.51.237.16:8081/" + redirect;
        } else {
            redirectUrl = "http://20.51.237.16:8081/" + redirect;
        }

        URL url = new URL(redirectUrl);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("POST");
        connection.setRequestProperty("Content-Type", "application/json");

        String requestBody = "{ \"query\": \"SELECT  ip@string, eventTimestamp@long FROM networkTraffic\",\n" +
                "    \"apiKey\": \"Tu_TZ0W2cR92-sr1j-l7ACA.newone.9pej9tihskpx2vYZaxubGW3sFCJLzxe55NRh7T0uk1JMYiRmHdiQsWh5JhRXXT6c418385\",\n" +
                "    \"id\": \"ZXCVB\"}";

        connection.setDoOutput(true);
        OutputStream outputStream = connection.getOutputStream();
        outputStream.write(requestBody.getBytes());
        outputStream.flush();
        outputStream.close();

        int responseCode = connection.getResponseCode();
        System.out.println("res: "+responseCode);
        // Handle the response as needed
    }



    @PostMapping("/publish")
    public void redirectPublish() throws IOException {
        redirectPostBasedOnLocation("publish");
    }

    @GetMapping("/time")
    public void redirectSetQuery() throws IOException {
        redirectGetBasedOnLocation("time");
    }
}
