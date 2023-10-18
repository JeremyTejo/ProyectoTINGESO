package com.example.demo.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;


import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "alumno")
public class Alumno {

    @Id
    private String rut;

    private String nombre;
    private String apellido;
    private LocalDate fechaNacimiento;
    private String tipoColegio;
    private String nombreColegio;

    @Column(name = "fecha_egreso_colegio", nullable = false)
    private String fechaEgresoColegio;


    private Double promedioDePruebas;

    //Atributos para implementar HU6
    private Double arancelTotal;
    private Integer cantidadCuotas;


   @OneToMany(mappedBy =  "alumno")
    private List<Cuota> cuotas;

}