package Repositories;

import Entities.Cuota;
import Entities.Pago;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CuotaRepositories extends JpaRepository<Cuota,Long> {

    List<Cuota>findByPago_Alumno_Rut(String rut);

}
