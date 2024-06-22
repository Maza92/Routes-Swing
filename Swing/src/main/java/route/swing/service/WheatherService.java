/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package route.swing.service;

import java.io.IOException;
import route.swing.http.HttpClientWheater;

/**
 *
 * @author Luis
 */
public class WheatherService {
    
    HttpClientWheater api;
    
    public WheatherService() {
        api = new HttpClientWheater();
    }
    public double getActualTemp() throws IOException {
        return api.parseTemperature(api.getWeatherData("Lima"));
    }
}
