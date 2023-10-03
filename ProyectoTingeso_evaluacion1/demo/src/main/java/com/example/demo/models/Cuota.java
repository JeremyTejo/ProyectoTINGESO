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
    @JoinColumn(name = "rutAlumno")
    private Alumno alumno;

    @ManyToOne
    @JoinColumn(name = "idPago")
    private Pago pago;

    private LocalDate fechaPago;
    private LocalDate fechaVencimiento;
    private int cantidadCuotas;
    private int montoCuota;
    private String estadoCuota;




}
