/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package route.swing;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import route.swing.http.HttpClient;
import route.swing.model.RegionDTO;
import route.swing.model.Route;
import route.swing.model.RouteDTO;
import route.swing.model.jxmap.Region;
import route.swing.service.OpenStreetMapService;
import route.swing.util.JsonUtil;

/**
 *
 * @author Luis
 */
public class Swing {

    public static void main(String[] args) throws IOException {
        OpenStreetMapService service = new OpenStreetMapService();
        HttpClient client = new HttpClient();
        JsonUtil json = new JsonUtil();
        
        String archivoCSV = "C:/Users/Luis/Desktop/rutas_peru.csv";
        
        
        
        List<Route> regionList = new ArrayList<>();

        try (CSVReader reader = new CSVReader(new FileReader(archivoCSV))) {
            List<String[]> registros = reader.readAll();
            for (String[] registro : registros) {
                String name = registro[0];
                String inicio = registro[1];
                String fin = registro[2];
                
                System.out.println("name : " + name + "\ninicio : " + inicio + "\nend : " + fin + "\n");
                
                RouteDTO route = new RouteDTO();
                route.setName(name);
                
                Region start = json.fromJson(
                        client.post("/api/region/save", 
                        json.toJson(service.getCoordinatesByAddress(inicio))), Region.class);
                
                
                Region end = json.fromJson(
                        client.post("/api/region/save", 
                        json.toJson(service.getCoordinatesByAddress(fin))), Region.class);
                
                
                route.setStart(toDTO(start));
                route.setEnd(toDTO(end));
                System.out.println(json.toJson(route));
                
                
                Route ruta = json.fromJson(client.post("/api/routes", json.toJson(route)), Route.class);
                
                System.out.println(ruta.getName());
                
                
            }
        } catch (IOException | CsvException e) {
            e.printStackTrace();
        }

        
        
        
    }
    public static RegionDTO toDTO(Region region) {
        RegionDTO dto = new RegionDTO(region.getName(), region.getLatitud(), region.getLongitud());
        dto.setId(region.getId());
        return dto;
    }
}
