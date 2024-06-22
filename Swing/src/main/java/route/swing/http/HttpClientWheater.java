/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package route.swing.http;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 *
 * @author Luis
 */
public class HttpClientWheater {

    private final String API_KEY = "db90cf878449475b8e910742241606";
    private final String BASE_URL = "http://api.weatherapi.com/v1/current.json";
    
    public String getWeatherData(String location) throws IOException {
        OkHttpClient client = new OkHttpClient();

        HttpUrl url = HttpUrl.parse(BASE_URL).newBuilder()
                .addQueryParameter("key", API_KEY)
                .addQueryParameter("q", location)
                .addQueryParameter("aqi", "no")
                .build();

        Request request = new Request.Builder()
                .url(url)
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                throw new IOException("Unexpected code " + response);
            }
            return response.body().string();
        }
    }

    public double parseTemperature(String jsonResponse) throws JsonProcessingException {

        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode rootNode = objectMapper.readTree(jsonResponse);
        JsonNode current = rootNode.path("current");
        return current.path("temp_c").asDouble();
    }
}
