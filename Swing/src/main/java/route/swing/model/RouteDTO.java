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
public class RouteDTO {
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Long id;
    private String name;
    private RegionDTO start;
    private RegionDTO end;

    public RouteDTO(RegionDTO start, RegionDTO end) {
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
