package com.example.demo.repositories;

import com.example.demo.models.Alumno;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AlumnoRepository extends JpaRepository<Alumno,String>{

    Optional<Alumno> findAllByRut(String rut);
}
