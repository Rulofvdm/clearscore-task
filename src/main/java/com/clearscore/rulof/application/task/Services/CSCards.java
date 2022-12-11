package com.clearscore.rulof.application.task.Services;

import com.clearscore.rulof.application.task.Exceptions.CSCardsFailedException;
import com.clearscore.rulof.application.task.Exceptions.ScoredCardsFailedException;
import com.clearscore.rulof.application.task.Models.CSCardsService.CSCardsRequest;
import com.clearscore.rulof.application.task.Models.CSCardsService.CSCardsResponse;
import com.clearscore.rulof.application.task.Models.ScoredCardsService.ScoredCardsResponse;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class CSCards {
    private static String endpoint = System.getenv("CSCARDS_ENDPOINT");

    public static CSCardsResponse[] getCSCardsResponses(CSCardsRequest csCardsRequest) throws IOException, CSCardsFailedException {
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
            CSCardsResponse[] csCardsResponses = mapper.readValue(response.toString(), CSCardsResponse[].class);
            return csCardsResponses;

        } else {
            throw new CSCardsFailedException("Scored cards get failed.");
        }
    }
}
