package com.clearscore.rulof.application.task.Services;

import com.clearscore.rulof.application.task.Exceptions.ScoredCardsFailedException;
import com.clearscore.rulof.application.task.Models.ScoredCardsService.ScoredCardsRequest;
import com.clearscore.rulof.application.task.Models.ScoredCardsService.ScoredCardsResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;


/**
 * ScoredCards service.
 * Used to make requests to the ScoredCards API
 */
public class ScoredCards {
    private static final String endpoint = System.getenv("SCOREDCARDS_ENDPOINT");
    private static final String USER_AGENT = "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:221.0) Gecko/20100101 Firefox/31.0";

    /**
     * Gets the connection ready to make the request.
     * Sets header info and body content
     * @param scoredCardsRequest The object that will be used for the request.
     * @return An HTTP request ready to be made
     * @throws IOException
     */
    private static HttpURLConnection createConnection(ScoredCardsRequest scoredCardsRequest) throws IOException {
        // connection details
        URL url = new URL(endpoint);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.addRequestProperty("User-Agent", USER_AGENT);
        con.setRequestMethod("POST");
        con.setRequestProperty("Content-Type", "application/json");
        con.setDoOutput(true);
        con.setRequestProperty("Accept", "application/json");

        // request body
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        String jsonInputString = ow.writeValueAsString(scoredCardsRequest);
        try(OutputStream os = con.getOutputStream()) {
            byte[] input = jsonInputString.getBytes("utf-8");
            os.write(input, 0, input.length);
        }

        return con;
    }

    /**
     * Makes the ScoredCards request.
     * @param scoredCardsRequest The body that must be used in the request
     * @return A valid response from the API
     * @throws IOException
     * @throws ScoredCardsFailedException If API failed on their side
     */
    public static ScoredCardsResponse[] getScoredCardsResponses(ScoredCardsRequest scoredCardsRequest) throws IOException, ScoredCardsFailedException {
        HttpURLConnection con = createConnection(scoredCardsRequest);
        int responseCode = con.getResponseCode();
        if (responseCode == HttpURLConnection.HTTP_OK) {
            StringBuilder response = new StringBuilder();
            try(BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream(), "utf-8"))) {
                String responseLine = null;
                while ((responseLine = br.readLine()) != null) {
                    response.append(responseLine.trim());
                }
            }

            ObjectMapper mapper = new ObjectMapper();
            ScoredCardsResponse[] scoredCardsResponses = mapper.readValue(response.toString(), ScoredCardsResponse[].class);
            return scoredCardsResponses;

        } else {
            throw new ScoredCardsFailedException("ScoredCards request failed.");
        }
    }
}
