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
@Table(name = "examen")
public class Examen {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idExamen;
    private LocalDate fechaExamen;
    private Double nota;
    private String materia;
    @ManyToOne
    @JoinColumn(name = "rut_alumno", nullable = false)
    private Alumno alumno;
}
