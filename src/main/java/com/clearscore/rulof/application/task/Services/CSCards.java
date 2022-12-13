package com.clearscore.rulof.application.task.Services;

import com.clearscore.rulof.application.task.Exceptions.CSCardsFailedException;
import com.clearscore.rulof.application.task.Models.CSCardsService.CSCardsRequest;
import com.clearscore.rulof.application.task.Models.CSCardsService.CSCardsResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * CSCards service.
 * Used to make requests to the CSCards API
 */
public class CSCards {
    private static final String endpoint = System.getenv("CSCARDS_ENDPOINT");
    private static final String USER_AGENT = "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:221.0) Gecko/20100101 Firefox/31.0";

    /**
     * Gets the connection ready to make the request.
     * Sets header info and body content
     * @param csCardsRequest The object that will be used for the request.
     * @return An HTTP request ready to be made
     * @throws IOException
     */
    private static HttpURLConnection createConnection(CSCardsRequest csCardsRequest) throws IOException {
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
        String jsonInputString = ow.writeValueAsString(csCardsRequest);
        try(OutputStream os = con.getOutputStream()) {
            byte[] input = jsonInputString.getBytes("utf-8");
            os.write(input, 0, input.length);
        }

        return con;
    }

    /**
     * Makes the CSCards request.
     * @param csCardsRequest The body that must be used in the request
     * @return A valid response from the API
     * @throws IOException
     * @throws CSCardsFailedException If API failed on their side
     */
    public static CSCardsResponse[] getCSCardsResponses(CSCardsRequest csCardsRequest) throws IOException, CSCardsFailedException {
        HttpURLConnection con = createConnection(csCardsRequest);
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
            CSCardsResponse[] csCardsResponses = mapper.readValue(response.toString(), CSCardsResponse[].class);
            return csCardsResponses;

        } else {
            throw new CSCardsFailedException("CSCards request failed.");
        }
    }
}
