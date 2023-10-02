package Services;

import Entities.Alumno;
import Entities.Cuota;
import Entities.Pago;
import Repositories.AlumnoRepositories;
import Repositories.CuotaRepositories;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

@Service
public  class AlumnoService {
    @Autowired
    private AlumnoRepositories alumnoRepositories;
    @Autowired
    private CuotaRepositories cuotaRepositories;

    public List<Alumno> getAllAlumnos() {
        return alumnoRepositories.findAll();
    }

    public Alumno getAlumnoByRut(String rut){
        return alumnoRepositories.findById(rut).orElse(null);
    }

    public Alumno saveAlumno(Alumno alumno){
        return alumnoRepositories.save(alumno);
    }

    public void generarCuotas(Pago pago, int numeroCuotas){
        Double montoCuota = pago.getMonto()/numeroCuotas;

        for (int i = 0;i < numeroCuotas; i++){
            Cuota cuota = new Cuota();
            cuota.setPago(pago);
            cuota.setMontoInicio(montoCuota);
            cuota.setMontoFinal(montoCuota);
            cuota.setEstadoCuota(Cuota.EstadoCuota.PENDIENTE);
            cuota.setFechaVencimiento(pago.getFechaPago().plusMonths(i+1));
            cuotaRepositories.save(cuota);
        }
    }

}
