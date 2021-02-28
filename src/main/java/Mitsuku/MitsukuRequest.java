package Mitsuku;

import main.Utils;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

public class MitsukuRequest {

    private static int sessionid = 0;
    private static int channel = 7;

    // Bot base URL
    private String baseURL = "https://icap.iconiq.ai/talk";
    // Mitsuku bot key on site
    private String botKey = "icH-VVd4uNBhjUid30-xM9QhnvAaVS3wVKA3L8w2mmspQ-hoUB3ZK153sEG3MX-Z8bKchASVLAo~";

    // String message user wants to send bot
    private String message;

    public MitsukuRequest (String message) {

        this.message = message;

    }


    public String run () {

        try {
            // Create url connection with url
            URL urlObj = new URL(buildURL());

            HttpURLConnection connection = (HttpURLConnection) urlObj.openConnection();

            // We want to make a post request to server
            connection.setRequestMethod("POST");
            // We will be working with JSON
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setDoOutput(true);

            // Handle response from request
            return handleResponse(connection);

        } catch (IOException e) {
            System.out.println(e);
            return "Sorry, something has gone wrong.";
        }

    }

    private String handleResponse (HttpURLConnection connection) throws IOException {

        // Get input stream from connection
        BufferedReader input = new BufferedReader(new InputStreamReader(connection.getInputStream()));

        String line;
        StringBuffer requestResponse = new StringBuffer();

        // Read response and add it to buffer
        while ((line = input.readLine()) != null) {
            requestResponse.append(line);
        }

        // Close buffered reader
        input.close();

        // Convert response string to JSON
        JSONObject json = Utils.toJSON(requestResponse.toString());

        // If status != ok
        if (!json.get("status").equals("ok")) {
            return "Sorry, something has gone wrong.";
        }

        // Response array returned from request
        JSONArray responses = (JSONArray) json.get("responses");

        // Format first response given by bot
        return Utils.formatMitsukuMessage(responses.get(0).toString());


    }

    // Encode message to put in url params
    private String encodeParam (String param) throws UnsupportedEncodingException {
        return URLEncoder.encode(param, StandardCharsets.UTF_8.toString());
    }

    // Create request url
    private String buildURL () throws UnsupportedEncodingException {
        return baseURL + "?input=" + encodeParam(this.message) + "&botkey=" + botKey
                + "&channel=" + channel + (sessionid != 0 ? String.valueOf(sessionid) : "");
    }


}
