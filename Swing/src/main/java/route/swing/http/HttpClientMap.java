/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package route.swing.http;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.json.JSONArray;
import org.json.JSONObject;
import route.swing.model.jxmap.Region;

/**
 *
 * @author Luis
 */
public class HttpClientMap {

    private final OkHttpClient client = new OkHttpClient();

    public String getCoordinates(String address) throws UnsupportedEncodingException {
        String encodedAddress = URLEncoder.encode(address, "UTF-8");
        System.out.println(encodedAddress);
        String urlString = "https://nominatim.openstreetmap.org/search?q=" + encodedAddress + "&format=json&addressdetails=1&countrycodes=PE";

        Request request = new Request.Builder()
                .url(urlString)
                .header("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/58.0.3029.110 Safari/537.3")
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                throw new IOException("Unexpected code " + response);
            }
            return response.body().string();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public Region parseCoordinates(String jsonResponse) {
        JSONArray jsonArray = new JSONArray(jsonResponse);
        Region zone = new Region();
        if (jsonArray.length() > 0) {
            JSONObject jsonObject = jsonArray.getJSONObject(0);
            zone.setLatitud(Double.parseDouble(jsonObject.getString("lat")));
            zone.setLongitud(Double.parseDouble(jsonObject.getString("lon")));
            return zone;
        }
        return null;
    }
}
