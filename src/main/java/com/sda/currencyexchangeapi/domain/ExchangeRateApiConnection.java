package com.sda.currencyexchangeapi.domain;

import org.json.JSONObject;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.*;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Component
public class ExchangeRateApiConnection {

    private static final String stringUrl = "https://api.exchangerate.host/";

    public JSONObject getCurrencyExchange(String base, String target, String date) throws URISyntaxException, IOException, InterruptedException {

        String strUrlWithParams = stringUrl + date + "?base=" + base + "&symbols=" + target;


        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI(strUrlWithParams))
                .version(HttpClient.Version.HTTP_2)
                .GET()
                .build();

        HttpResponse<String> response = HttpClient.newHttpClient()
                .send(request, HttpResponse.BodyHandlers.ofString());

        return new JSONObject(response.body());

    }
}