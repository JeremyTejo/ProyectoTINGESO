package com.example.demo.repositories;

import com.example.demo.models.Examen;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExamenRepository extends JpaRepository<Examen,Long> {
    List<Examen> findAllByAlumnoRut(String rut);
}
