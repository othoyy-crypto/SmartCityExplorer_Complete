package com.smartcity.frontend;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.net.URI;
import java.net.http.*;
import java.util.ArrayList;
import java.util.List;

public class ApiClient {

    private static final String BASE_URL = "http://localhost:8081/api";
    private static final HttpClient CLIENT = HttpClient.newHttpClient();
    private static final ObjectMapper MAPPER = new ObjectMapper();

    // =========================
    // LOGIN
    // =========================
    public static String login(String email, String password) throws Exception {

        String json = String.format(
                "{\"email\":\"%s\",\"password\":\"%s\"}",
                escape(email), escape(password)
        );

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(BASE_URL + "/users/login"))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(json))
                .build();

        HttpResponse<String> response = CLIENT.send(
                request,
                HttpResponse.BodyHandlers.ofString()
        );

        return response.body();
    }

    // =========================
    // REGISTER
    // =========================
    public static String register(String name, String email, String password) throws Exception {

        String json = String.format(
                "{\"name\":\"%s\",\"email\":\"%s\",\"password\":\"%s\"}",
                escape(name),
                escape(email),
                escape(password)
        );

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(BASE_URL + "/users/register"))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(json))
                .build();

        HttpResponse<String> response = CLIENT.send(
                request,
                HttpResponse.BodyHandlers.ofString()
        );

        return response.body();
    }

    // =========================
    // GET PLACES
    // =========================
    public static List<String> getPlaces() throws Exception {

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(BASE_URL + "/places"))
                .GET()
                .build();

        HttpResponse<String> response = CLIENT.send(
                request,
                HttpResponse.BodyHandlers.ofString()
        );

        JsonNode array = MAPPER.readTree(response.body());

        List<String> result = new ArrayList<>();

        for (JsonNode place : array) {

            result.add(
                    place.path("name").asText()
                    + " | " + place.path("category").asText()
                    + " | " + place.path("location").asText()
                    + "\n" + place.path("description").asText()
            );
        }

        return result;
    }

    // =========================
    // GET RESTAURANTS
    // =========================
    public static List<String> getRestaurants() throws Exception {

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(BASE_URL + "/restaurants"))
                .GET()
                .build();

        HttpResponse<String> response = CLIENT.send(
                request,
                HttpResponse.BodyHandlers.ofString()
        );

        JsonNode array = MAPPER.readTree(response.body());

        List<String> result = new ArrayList<>();

        for (JsonNode restaurant : array) {

            result.add(
                    restaurant.path("name").asText()
                    + " | " + restaurant.path("category").asText()
                    + " | " + restaurant.path("location").asText()
                    + "\n" + restaurant.path("description").asText()
            );
        }

        return result;
    }
    
public static List<String> getHotels() throws Exception {

    HttpRequest request = HttpRequest.newBuilder()
            .uri(URI.create(BASE_URL + "/hotels"))
            .GET()
            .build();

    HttpResponse<String> response = CLIENT.send(
            request,
            HttpResponse.BodyHandlers.ofString()
    );

    JsonNode array = MAPPER.readTree(response.body());

    List<String> result = new ArrayList<>();

    for (JsonNode hotel : array) {

        result.add(
                "Name: " + hotel.path("name").asText()
                + "\nLocation: " + hotel.path("location").asText()
                + "\nPrice: " + hotel.path("price").asDouble()
                + "\nRating: " + hotel.path("rating").asDouble()
                + "\nDescription: " + hotel.path("description").asText()
        );
    }

    return result;
}

    // =========================
    // ESCAPE JSON
    // =========================
    private static String escape(String value) {

        return value
                .replace("\\", "\\\\")
                .replace("\"", "\\\"");
    }
}