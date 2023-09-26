package Repositories;

import Entities.Alumno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface AlumnoRepositories extends JpaRepository<Alumno,Long> {
    Optional<Alumno> buscarAlumnoPorRut(String rut);

}
