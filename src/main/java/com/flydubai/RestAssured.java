package com.flydubai;

import io.restassured.config.SSLConfig;
import io.restassured.response.Response;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.UUID;

public class RestAssured {
    public static void main(String[] args){

        //SSl Disable
        io.restassured.RestAssured.config = io.restassured.RestAssured.config().sslConfig(new SSLConfig().relaxedHTTPSValidation());

        // Base URL
        String baseUrl = "https://qa1-flights2.np.flydubai.com";

        // Generate a random string
        String randomString = UUID.randomUUID().toString();

        // Create the JSON body
        JSONObject requestBody = new JSONObject();
        requestBody.put("cabinClass", "Economy");
        requestBody.put("promoCode", "TESTTEST");
        requestBody.put("isDestMetro", false);
        requestBody.put("isOriginMetro", true);

        // Passenger Information
        JSONObject paxInfo = new JSONObject();
        paxInfo.put("adultCount", 2);
        paxInfo.put("childCount", 0);
        paxInfo.put("infantCount", 0);
        requestBody.put("paxInfo", paxInfo);

        // Search Criteria
        JSONObject searchCriteria = new JSONObject();
        searchCriteria.put("date", "05/29/2024 12:00 AM");
        searchCriteria.put("dest", "PRG");
        searchCriteria.put("direction", "outBound");
        searchCriteria.put("origin", "DXB");
        searchCriteria.put("isOriginMetro", true);
        searchCriteria.put("isDestMetro", false);
        requestBody.put("searchCriteria", new JSONArray().put(searchCriteria));
        requestBody.put("variant", "1");
        requestBody.put("curr", "INR");

        // Convert request body to string
        String requestBodyString = requestBody.toString();

        System.out.println(requestBodyString);

        // Make the POST request

        Response response = io.restassured.RestAssured.given()
                .baseUri(baseUrl)
                .header("Content-Type", "application/json")
                .header("Host", "qa1-flights2.np.flydubai.com")
                .header("Accept", "*/*")
                .header("Accept-Encoding", "gzip, deflate, br")
                .header("Connection", "keep-alive")
                .header("appID", "Desktop")
                .header("test", "test")
                .header("Cookie", "_abck=C55DF7AAC9579C804D709F02C6FB4357~-1~YAAQjGPUF4JGDU+PAQAAhB9fgAtWD618v5Wtul8XM9JGPQxzWDfu/DRwBuvyCNaHIWzppbfqbKOoHQnsGU9ryMh/3X0mmRef+ebTkq6Lg3smM+/uFmU0rFMss9k7WnR0lc6iFKyc9vK69s36DU+a0TwhjpTNz7HbGnxpPpVEiq0xKMid6R0Z3azecZgFeiLfnY6PpJwVCbEaz+gyJkruxDEJ420Fz3Ah7ew0P4oUw3w9sHNUAuA11utGnl8W/p05yY6pv1Asfdio+yJyM4uDmE3qF9IAEwFAUvYg/7q73tXc2NXxFEKzr9w40v/Kykvfk+/jEJetEm6zQ7U+tOZkwFR087ODZ22c9nnClPlg/cq1OCA7Q96uPJfIQA==~-1~-1~-1")
                .header("Cache-Control", "no-cache")
                .header("Postman-Token", randomString)

                .body(requestBodyString)
                .post("/api/flights/1");


        // Print the response
        System.out.println(response.getStatusCode());
        System.out.println(response.getBody().asString());
    }
}
