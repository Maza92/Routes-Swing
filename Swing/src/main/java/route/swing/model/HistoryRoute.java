/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package route.swing.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import route.swing.model.user.User;

/**
 *
 * @author Luis
 */
@Getter
@Setter
@NoArgsConstructor
public class HistoryRoute {
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Long id;
    
    private String name;
    
    @JsonIgnore
    private User user;
    
    private HistoryRegion start;
    
    private HistoryRegion end;

    public HistoryRoute(HistoryRegion start, HistoryRegion end) {
        this.start = start;
        this.end = end;
    }
    
    
}
