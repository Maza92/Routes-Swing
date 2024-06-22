/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package route.swing.http;

import java.io.IOException;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 *
 * @author Luis
 */
public class HttpClient {

    private final OkHttpClient client;
    
    private final String api = "http://localhost:8080";

    public HttpClient() {
        this.client = new OkHttpClient();
    }

    public String get(String url) throws IOException {
        Request request = new Request.Builder()
                .url(api + url)
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                throw new IOException("Unexpected code " + response);
            }

            return response.body().string();
        }
    }

    public String post(String url, String json) throws IOException {
        RequestBody body = RequestBody.create(
                json, MediaType.get("application/json; charset=utf-8"));

        Request request = new Request.Builder()
                .url(api + url)
                .post(body)
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                throw new IOException("Unexpected code " + response);
            }

            return response.body().string();
        }
    }
}
