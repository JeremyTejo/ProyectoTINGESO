package com.example.demo.repositories;

import com.example.demo.models.Cuota;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CuotaRepository extends JpaRepository<Cuota, Long> {

    List<Cuota> findAllByAlumnoRut(String rut);
    List<Cuota> findByAlumnoRutAndEstadoCuota(String rut, String estado);


}
