package Services;

import Entities.Alumno;
import Entities.Pago;
import Repositories.PagoRepositories;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;

public class PagoService {

    @Autowired
    private PagoRepositories pagoRepositories;



    public List<Pago> todosLosPagos(){
        return pagoRepositories.findAll();
    }

    public Pago findById( Long id){
        return pagoRepositories.findById(id).orElse(null);

    }
    public Pago guardarPago( Pago pago){
        return pagoRepositories.save(pago);
    }
    public List<Pago> generarCuotas(Alumno alumno) {
        List<Pago> cuotas = new ArrayList<>();

        //generamos la matricula para luego generar las cuotas
        Pago matricula = new Pago();
        matricula.setAlumno(alumno);
        matricula.setMonto(70.000);
        matricula.setTipoPago("Matricula Estudiante");
        matricula.setEstadoPago("Pendiente");
        cuotas.add(matricula);

        // Generar cuotas basadas en tipo de colegio
        int numeroDeCuotas;
        switch (alumno.getTipoColegio()) {
            case "Municipal":
                numeroDeCuotas = 10;
                break;
            case "Subvencionado":
                numeroDeCuotas = 7;
                break;
            case "Privado":
            default:
                numeroDeCuotas = 4;
                break;
        }

        double montoCuota = 1500000.0 / numeroDeCuotas; // $1,500,000 de arancel, distribuido en cuotas

        for (int i = 0; i < numeroDeCuotas; i++) {
            Pago cuota = new Pago();
            cuota.setAlumno(alumno);
            cuota.setMonto(montoCuota);
            cuota.setTipoPago("Cuota");
            cuota.setCuotasPago(i + 1);
            cuota.setEstadoPago("Pendiente");
            cuotas.add(cuota);
        }

        return pagoRepositories.saveAll(cuotas);
    }

    }
}
