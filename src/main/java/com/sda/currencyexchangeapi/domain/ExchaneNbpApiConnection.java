package com.sda.currencyexchangeapi.domain;

import org.json.JSONObject;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Component
public class ExchaneNbpApiConnection {

    private static final String stringUrl = "http://api.nbp.pl/api/exchangerates/rates/c/";

    public JSONObject getPlnCurrencyExchange(String target, String date) throws URISyntaxException, IOException, InterruptedException {

        String strUrlWithParams = stringUrl + target + "/" + date + "/?format=json";



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
