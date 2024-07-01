
package route.swing.model;



import route.swing.model.jxmap.*;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import route.swing.model.user.User;

/**
 *
 * @author luis
 */
@Getter
@Setter
@NoArgsConstructor
public class RegionDTO {

    private Long id;
    private String name;
    private Double latitud;
    private Double longitud;
    
    public RegionDTO(String name, Double latitud, Double longitud) {
        this.name = name;
        this.latitud = latitud;
        this.longitud = longitud;
    }
    
    @Override
    public String toString() {
        return getName() +"\n" + getLatitud() + "\n" + getLongitud();
    }
}
