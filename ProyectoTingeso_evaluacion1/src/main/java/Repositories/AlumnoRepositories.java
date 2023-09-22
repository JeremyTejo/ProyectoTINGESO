package Repositories;

import Entities.Alumno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface AlumnoRepositories extends JpaRepository<Alumno,Long> {

}
