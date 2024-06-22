/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package route.swing.http;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import route.swing.model.jxmap.Region;

/**
 *
 * @author Luis
 */
public class HttpClientIPGPS {
     public Region getLocation(String ipAddress) {
        try {
            // Crear un cliente OkHttp
            OkHttpClient client = new OkHttpClient();

            // Construir la solicitud HTTP
            Request request = new Request.Builder()
                    .url("http://ip-api.com/json/" + ipAddress)
                    .get()
                    .build();

            // Ejecutar la solicitud
            try (Response response = client.newCall(request).execute()) {
                if (response.isSuccessful()) {
                    // Obtener la respuesta como String
                    String responseData = response.body().string();

                    // Parsear la respuesta JSON con Jackson
                    ObjectMapper objectMapper = new ObjectMapper();
                    JsonNode jsonNode = objectMapper.readTree(responseData);
                    double latitude = jsonNode.get("lat").asDouble();
                    double longitude = jsonNode.get("lon").asDouble();

                    // Crear y retornar el objeto Location
                    return new Region("Actual Ubi", latitude, longitude);
                } else {
                    System.out.println("Error al obtener la ubicación: " + response.code());
                }
            } catch (IOException e) {
                System.out.println("Error al realizar la solicitud HTTP: " + e.getMessage());
            }
        } catch (Exception e) {
            System.out.println("Error al obtener la ubicación: " + e.getMessage());
        }
        return null;
    }
}
