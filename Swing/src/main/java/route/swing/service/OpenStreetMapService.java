/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package route.swing.service;

import java.io.UnsupportedEncodingException;
import route.swing.http.HttpClientMap;
import route.swing.model.jxmap.Region;

/**
 *
 * @author Luis
 */
public class OpenStreetMapService {
    HttpClientMap mapApi;

    public OpenStreetMapService() {
        mapApi = new HttpClientMap();
    }
    
    public Region findRegionByName(String name) throws UnsupportedEncodingException {
        Region region = mapApi.parseCoordinates(mapApi.getCoordinates(name));
        region.setName(name);
        return region;
    }
    
}
