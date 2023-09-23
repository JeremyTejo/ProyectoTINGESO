package Services;

import Entities.Alumno;
import Repositories.AlumnoRepositories;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public  class AlumnoService {
    private AlumnoRepositories alumnoRepositories;


    public Alumno guardar(Alumno alumno) {
        return alumnoRepositories.save(alumno);
    }


    public List<Alumno> getAllAlumnos() {
        return alumnoRepositories.findAll();
    }

    public void delete(Alumno alumno) {
        alumnoRepositories.delete(alumno);
    }

    public Optional<Alumno> find(Long id) {
        return alumnoRepositories.findById(id);
    }

    public List<Alumno> listarAlumnos(){
        return alumnoRepositories.findAll();
    }

    public Optional<Alumno> buscarPorId(Long id) {
        return alumnoRepositories.findById(id);
    }
    public void deleteById(Long id){
        alumnoRepositories.deleteById(id);
    }
}
