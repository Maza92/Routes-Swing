/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package route.swing.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 *
 * @author Luis
 */
@Getter
@Setter
@NoArgsConstructor
public class HistoryRegion {
    private Long id;
    private String name;
    private Double latitud;
    private Double longitud;
    

    public HistoryRegion(String name, Double latitud, Double longitud) {
        this.name = name;
        this.latitud = latitud;
        this.longitud = longitud;
    }
}
