/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package route.swing.model;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import route.swing.model.jxmap.Region;

/**
 *
 * @author Administrator
 */
@Getter
@Setter
@NoArgsConstructor
public class RouteDTO2 {
    private Long id;
    private String name;
    private Region start;
    private Region end;

//    public RouteDTO2(Region start, Region end) {
//        this.start = start;
//        this.end = end;
//    }

    public RouteDTO2(Long id, String name, Region start, Region end) {
        this.id = id;
        this.name = name;
        this.start = start;
        this.end = end;
    }
    
    
    @Override
    public String toString() {
        return "\n" + getName()
                + ":\n- " + getStart().getName()
                + "\n- " + getEnd().getName()
                + "\n";
    }
}
