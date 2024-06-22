/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package route.swing;

import java.io.IOException;
import route.swing.http.HttpClient;
import route.swing.http.HttpClientIPGPS;
import route.swing.model.jxmap.Region;
import route.swing.util.JsonUtil;

/**
 *
 * @author Luis
 */
public class Swing {

    public static void main(String[] args) throws IOException {
        HttpClient client = new HttpClient();

        Region region = new Region("Lima", 12.9, -17.9);
        JsonUtil json = new JsonUtil();
        String jsonString = json.toJson(region);

        System.out.println("####antes#####\n");
        System.out.println(jsonString);

        String responseJson = client.post("/api/user/region", jsonString);
        Region response = json.fromJson(responseJson, Region.class);

        System.out.println("\n####despues#####\n");
        System.out.println(json.toJson(response));
    }
}
