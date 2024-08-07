/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package route.swing.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.LocalTime;
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
public class Horary {
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private int id;
    private String dia;
    private LocalTime horaInicio;
    private LocalTime horaFin;
    private Route route;
}
