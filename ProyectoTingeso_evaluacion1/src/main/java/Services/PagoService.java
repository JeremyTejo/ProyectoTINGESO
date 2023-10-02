package Services;

import Entities.Alumno;
import Entities.Cuota;
import Entities.Pago;
import Repositories.CuotaRepositories;
import Repositories.PagoRepositories;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

@Service
public class PagoService  {

    @Autowired
    private PagoRepositories pagoRepositories;
    @Autowired
    private AlumnoService alumnoService;
    @Autowired
    private CuotaRepositories cuotaRepositories;

    private static final Double ARANCEL = 1500000.0; //SE MANTENDRA SIEMPRE QUE EL ARANCEL SERAN 1.500.000
    private static final Double MATRICULA = 70000.0; // AL IGUAL QUE EL ARANCEL  A NO SER QUE SE CALCULEN LOS DESCUENTOS LUEGO

    public Pago getPagoById(Long id){
        return pagoRepositories.findById(id).orElse(null);
    }

    public Pago savePago(Pago pago){
        return pagoRepositories.save(pago);
    }

    public List<Pago> getAllPagos(){
        return pagoRepositories.findAll();
    }
/**
    public Pago crearPagoConCuotas(String rut, int numeroDeCuotas, Double montoTotal, Alumno.TipoColegio tipoColegio, int añoDeEgreso) {
        Alumno alumno = alumnoService.getAlumnoByRut(rut);
        if (alumno == null) {
            return null;
        }
        double descuento = calcularDescuento(tipoColegio, añoDeEgreso);
        double montoFinalConDescuento = montoTotal *(1-descuento);


        Pago pago = new Pago();
        pago.setAlumno(alumno);
        pago.setFechaPago(LocalDate.now());
        pago.setMonto(montoFinalConDescuento);
        pago.setTipoPago(Pago.TipoPago.CUOTAS);
        pago.setEstadoPago(Pago.EstadoPago.PENDIENTE);
        pago.setCuotasPago(numeroDeCuotas);

        pago = pagoRepositories.save(pago);

        double montoPorCuota = montoFinalConDescuento / numeroDeCuotas;

        for (int i = 0; i<numeroDeCuotas;i++){
            Cuota cuota = new Cuota();
            cuota.setPago(pago);
            cuota.setMontoInicio(pago.getMonto()/numeroDeCuotas);
            cuota.setMontoFinal(cuota.getMontoInicio());
            cuota.setFechaVencimiento(LocalDate.now().plusMonths(i + 1));
            cuota.setEstadoCuota(Cuota.EstadoCuota.PENDIENTE);
            cuotaRepositories.save(cuota);


        }
        return pago;
    }


    public Double calcularDescuento(Alumno.TipoColegio tipoColegio, int añoEgreso){
        double descuento = 0.0;
        switch (tipoColegio){
            case MUNICIPAL:
                descuento += 0.20;
                break;
            case SUBVENCIONADO:
                descuento += 0.10;
                break;
            case PRIVADO:
                descuento += 0.0;
                break;
            default:
                return 0.0;
        }

        if (añoEgreso <1){
            descuento += 0.15;
        }else if (añoEgreso <=2) {
            descuento += 0.08;
        }else if (añoEgreso<=4) {
            descuento += 0.04;
        }
        return descuento;
    }

    public List<Cuota>ObtenerCuotasRutEstudiantes(String rut){
        return cuotaRepositories.findByPago_Alumno_Rut(rut);
    }

**/


}

