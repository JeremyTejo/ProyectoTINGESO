package Entities;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "alumno" )
@Data
@AllArgsConstructor
@NoArgsConstructor
@Setter
public class Alumno {

    @Id
    private String rut;


    private String nombre;
    private String apellido;

    @Temporal(TemporalType.DATE)
    private String fechaNacimiento;

    private String tipoColegio;
    private String nombreColegio;
    private int añoEgresoColegio;



}
