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
@Table(name = "reporte")
public class Reporte {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idReporte;

    @ManyToOne
    @JoinColumn(name = "rutAlumno")
    private Alumno alumno;

    private LocalDate fechaGeneracion;
    private String descripcion;
    private int cuotasPendientes;
    private int cuotasPagadas;
    private int montoTotalPendiente;
    private int montoTotalPagado;

}
