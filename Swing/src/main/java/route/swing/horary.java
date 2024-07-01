/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package route.swing;

import java.io.IOException;
import java.time.LocalTime;
import java.util.List;
import route.swing.http.HttpClient;
import route.swing.model.Horary;
import route.swing.model.Route;
import route.swing.util.JsonUtil;

/**
 *
 * @author Luis
 */
public class horary {
    public static void main(String[] args) throws IOException {
        HttpClient client = new HttpClient();
        JsonUtil json = new JsonUtil();
        
        List<Route> routes = json.fromJsonToList(client.get("/api/routes"), Route.class);
        
        
        for (int i = 100; i < 200; i++) {
            
            Horary horary = new Horary();
            horary.setDia("Lunes");
            horary.setHoraInicio(LocalTime.of(05, 00));
            horary.setHoraFin(LocalTime.of(22, 00));
            horary.setRoute(routes.get(i));
            
            client.post("/api/horary/routes", json.toJson(horary));
            
            horary.setDia("Martes");
            horary.setHoraInicio(LocalTime.of(05, 00));
            horary.setHoraFin(LocalTime.of(22, 00));
            horary.setRoute(routes.get(i));
            
            client.post("/api/horary/routes", json.toJson(horary));
            
            horary.setDia("Miercoles");
            horary.setHoraInicio(LocalTime.of(05, 00));
            horary.setHoraFin(LocalTime.of(22, 00));
            horary.setRoute(routes.get(i));
            
            client.post("/api/horary/routes", json.toJson(horary));
            
            horary.setDia("Jueves");
            horary.setHoraInicio(LocalTime.of(05, 00));
            horary.setHoraFin(LocalTime.of(22, 00));
            horary.setRoute(routes.get(i));
            
            client.post("/api/horary/routes", json.toJson(horary));
            
            horary.setDia("Viernes");
            horary.setHoraInicio(LocalTime.of(05, 00));
            horary.setHoraFin(LocalTime.of(22, 00));
            horary.setRoute(routes.get(i));
            
            client.post("/api/horary/routes", json.toJson(horary));
            
            horary.setDia("Sabado");
            horary.setHoraInicio(LocalTime.of(05, 00));
            horary.setHoraFin(LocalTime.of(22, 00));
            horary.setRoute(routes.get(i));
            
            client.post("/api/horary/routes", json.toJson(horary));
            
            horary.setDia("Domingo");
            horary.setHoraInicio(LocalTime.of(05, 00));
            horary.setHoraFin(LocalTime.of(22, 00));
            horary.setRoute(routes.get(i));
        }
        
        
    }
}
