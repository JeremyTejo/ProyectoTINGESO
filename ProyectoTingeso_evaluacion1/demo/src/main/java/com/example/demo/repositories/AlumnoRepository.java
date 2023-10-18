package com.example.demo.repositories;

import com.example.demo.models.Alumno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface AlumnoRepository extends JpaRepository<Alumno,String>{
    Optional<Alumno> findByRut(String rut);
}
