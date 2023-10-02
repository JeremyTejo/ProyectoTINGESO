package Entities;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "alumno")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Alumno {

    @Id
    private String rut;

    @Column(name = "nombre")
    private String nombre;
    @Column(name = "apellido")
    private String apellido;

    @Column(name = "fechaNacimiento")
    @Temporal(TemporalType.DATE)
    private Date fechaNacimiento;

    @Column(name = "tipoColegio")
    private String tipoColegio;
    @Column(name = "nombreColegio")
    private String nombreColegio;
    @Column(name = "añoEgresoColegio")
    private int añoEgresoColegio;


}
