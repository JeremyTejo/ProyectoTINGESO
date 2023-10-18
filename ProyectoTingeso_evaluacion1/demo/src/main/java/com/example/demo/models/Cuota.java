package com.example.demo.models;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "cuota")
public class Cuota {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCuota;

    @ManyToOne
    @JoinColumn(name = "rut_alumno")
    private Alumno alumno;

    private LocalDate fechaPago;
    private LocalDate fechaVencimiento;
    private double montoCuota;
    private String estadoCuota;
    private int mesesAtraso = 0;

    public Cuota(Alumno alumno, double montoCuota, LocalDate fechaVencimiento, String pendiente) {
    }
}
