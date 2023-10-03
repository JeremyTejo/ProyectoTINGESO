package com.example.demo.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.time.LocalDate;
import java.util.Date;

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
    @Temporal(TemporalType.DATE)
    private LocalDate fechaNacimiento;
    private String tipoColegio;
    private String nombreColegio;
    private String añoEgresoColegio;
}