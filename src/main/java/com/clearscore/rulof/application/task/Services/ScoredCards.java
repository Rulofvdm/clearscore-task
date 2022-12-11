package com.clearscore.rulof.application.task.Services;

import com.clearscore.rulof.application.task.Exceptions.ScoredCardsFailedException;
import com.clearscore.rulof.application.task.Models.ScoredCardsService.ScoredCardsRequest;
import com.clearscore.rulof.application.task.Models.ScoredCardsService.ScoredCardsResponse;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class ScoredCards {
    private static String endpoint = System.getenv("SCOREDCARDS_ENDPOINT");

    public static ScoredCardsResponse[] getScoredCardsResponses(ScoredCardsRequest scoredCardsResponse) throws ScoredCardsFailedException, IOException {
        URL obj = new URL(endpoint);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        con.setRequestMethod("GET");
        int responseCode = con.getResponseCode();
        if (responseCode == HttpURLConnection.HTTP_OK) { // success
            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            ObjectMapper mapper = new ObjectMapper();
            ScoredCardsResponse[] scoredCardsResponses = mapper.readValue(response.toString(), ScoredCardsResponse[].class);
            return scoredCardsResponses;

        } else {
            throw new ScoredCardsFailedException("Scored cards get failed.");
        }
    }
}
