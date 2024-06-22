/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package route.swing.service;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import org.json.JSONArray;
import org.json.JSONObject;
import route.swing.http.HttpClientMap;
import route.swing.model.jxmap.Region;

/**
 *
 * @author Luis
 */
public class OpenStreetMapService {
    private final HttpClientMap httpClient;

    public OpenStreetMapService() {
        this.httpClient = new HttpClientMap();
    }
    
    public Region getCoordinatesByAddress(String address) throws UnsupportedEncodingException, IOException {
        String encodedAddress = URLEncoder.encode(address, "UTF-8");
        String urlString = "https://nominatim.openstreetmap.org/search?q=" + encodedAddress + "&format=json&addressdetails=1&countrycodes=PE";
        String jsonResponse = httpClient.get(urlString);
        return parseCoordinates(jsonResponse);
    }
    
    private Region parseCoordinates(String jsonResponse) {
        JSONArray jsonArray = new JSONArray(jsonResponse);
        if (jsonArray.length() > 0) {
            JSONObject jsonObject = jsonArray.getJSONObject(0);
            double latitude = Double.parseDouble(jsonObject.getString("lat"));
            double longitude = Double.parseDouble(jsonObject.getString("lon"));
            return new Region(null, latitude, longitude);
        }
        return null;
    }

    public Region getCoordinatesByOsmId(String name, String oms_id, String oms_type) throws IOException {
        String urlString = "https://nominatim.openstreetmap.org/details.php?osmtype=" + oms_type + "&osmid=" + oms_id + "&addressdetails=1&hierarchy=0&group_hierarchy=1&format=json";
        String jsonResponse = httpClient.get(urlString);
        return parseCoordinatesFromDetails(jsonResponse, name);
    }

    private Region parseCoordinatesFromDetails(String jsonResponse, String name) {
        JSONObject jsonObject = new JSONObject(jsonResponse);
        JSONObject centroid = jsonObject.getJSONObject("centroid");
        double longitude = centroid.getJSONArray("coordinates").getDouble(0);
        double latitude = centroid.getJSONArray("coordinates").getDouble(1);
        return new Region(name, latitude, longitude);
    }

}
