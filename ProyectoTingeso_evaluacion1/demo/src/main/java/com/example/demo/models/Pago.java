package com.example.demo.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "pago")
public class Pago {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPago;

    @ManyToOne
    @JoinColumn(name = "rutAlumno")
    private Alumno alumno;

    private LocalDate fechaPago;
    private int montoTotal;
    private String medioPago;

    @OneToMany(mappedBy = "pago")
    private List<Cuota> cuotasPagadas;


}

