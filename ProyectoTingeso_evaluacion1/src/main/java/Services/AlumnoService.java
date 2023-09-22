package Services;

import Repositories.AlumnoRepositories;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AlumnoService {
    private final AlumnoRepositories alumnoRepositories;

    @Autowired
    public AlumnoService(AlumnoRepositories alumnoRepositories){
        this.alumnoRepositories=alumnoRepositories;
    }

}
