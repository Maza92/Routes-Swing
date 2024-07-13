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
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author Administrator
 */
public class HttpClientLlamaApi {

    private static final String API_KEY = "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";
    private static final String MODEL_ID = "llama3-70b-8192";
    private static final String API_URL = "https://api.groq.com/openai/v1/chat/completions";
    private static final OkHttpClient client = new OkHttpClient();

    public static String getCityDescription(String cityName) {
        String prompt = "Describe en dos líneas la ciudad de " + cityName + ".";
        return sendPrompt(prompt);
    }

    public static String sendPrompt(String prompt) {
        JSONObject message = new JSONObject();
        message.put("role", "user");
        message.put("content", prompt);

        JSONArray messages = new JSONArray();
        messages.put(message);

        JSONObject json = new JSONObject();
        json.put("messages", messages);
        json.put("model", MODEL_ID);

        RequestBody body = RequestBody.create(json.toString(), MediaType.get("application/json; charset=utf-8"));
        Request request = new Request.Builder()
                .url(API_URL)
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer " + API_KEY)
                .post(body)
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (response.isSuccessful()) {
                String responseBody = response.body().string();
                JSONObject jsonResponse = new JSONObject(responseBody);
                JSONArray choices = jsonResponse.getJSONArray("choices");
                if (choices.length() > 0) {
                    JSONObject choice = choices.getJSONObject(0);
                    JSONObject messageObject = choice.getJSONObject("message");
                    return messageObject.getString("content").trim();
                }
            } else if (response.code() == 401) {
                System.err.println("Error 401: No autorizado. Verifica tu clave API.");
                return null;
            } else {
                System.err.println("Error: " + response.code());
                System.err.println("Cuerpo: " + response.body().string());
                return null;
            }
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        return null;
    }
}
