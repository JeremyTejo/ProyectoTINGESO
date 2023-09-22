package Repositories;

import Entities.Alumno;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AlumnoRepositories extends JpaRepository<Alumno,Long> {

}
