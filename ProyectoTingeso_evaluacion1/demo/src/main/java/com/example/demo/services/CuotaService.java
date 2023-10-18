package com.example.demo.services;

import com.example.demo.models.Alumno;
import com.example.demo.models.Cuota;
import com.example.demo.repositories.CuotaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CuotaService {

    private final CuotaRepository cuotaRepository;
    private final AlumnoService alumnoService;
    private final ExamenService examenService;

    @Autowired
    public CuotaService(CuotaRepository cuotaRepository, AlumnoService alumnoService, ExamenService examenService) {
        this.cuotaRepository = cuotaRepository;
        this.alumnoService = alumnoService;
        this.examenService = examenService;
    }

    public List<Cuota> getAllCuotas() {
        return cuotaRepository.findAll();
    }

    public List<Cuota> getCuotasByRut(String rut) {
        return cuotaRepository.findAllByAlumnoRut(rut);
    }

    public Cuota saveCuota(Cuota cuota) {
        return cuotaRepository.save(cuota);
    }

    public Cuota getCuotaById(Long id) {
        return cuotaRepository.findById(id).orElse(null);
    }

    public List<Cuota> crearCuotas(String rut) {
        Alumno alumno = alumnoService.getAlumnoByRut(rut);
        if (alumno == null) {
            throw new IllegalArgumentException("El rut proporcionado no corresponde a un alumno registrado.");
        }

        double montoCuota = alumnoService.calcularMontoCuota(alumno, false);
        double promedio = examenService.calcularPromedioDeNotas(alumno.getRut());
        double descuentoPromedio = calcularDescuentoPorPromedio(promedio);
        montoCuota -= montoCuota * descuentoPromedio;

        int numeroDeCuotas = 0;
        switch (alumno.getTipoColegio()) {
            case "Municipal":
                numeroDeCuotas = 10;
                break;
            case "Subvencionado":
                numeroDeCuotas = 7;
                break;
            default:
                numeroDeCuotas = 4;
                break;
        }
        List<Cuota> cuotasGeneradas = new ArrayList<>();

        for (int i = 0; i < numeroDeCuotas; i++) {
            Cuota cuota = new Cuota();
            cuota.setAlumno(alumno);
            cuota.setMontoCuota(montoCuota);
            cuota.setFechaVencimiento(calcularFechaVencimiento().plusMonths(i));
            cuota.setEstadoCuota("Pendiente");
            cuotasGeneradas.add(cuota);
        }
        for (Cuota cuota : cuotasGeneradas) {
            cuotaRepository.save(cuota);
        }
        return cuotasGeneradas;
    }


    public Cuota updateCuota(Cuota cuota) {
        if (cuotaRepository.existsById(cuota.getIdCuota())) {
            return cuotaRepository.save(cuota);
        } else {
            return null;
        }
    }

    public void deleteCuota(Long id) {
        cuotaRepository.deleteById(id);
    }

    private LocalDate calcularFechaVencimiento() {
        LocalDate now = LocalDate.now();
        return LocalDate.of(now.getYear(), now.getMonth(), 10);
    }

    public Cuota aplicarInteresPorAtraso(Cuota cuota) {
        if (cuota.getFechaPago() != null && cuota.getFechaPago().isAfter(cuota.getFechaPago())) {
            throw new IllegalArgumentException("La fecha de pago no puede ser posterior a la fecha de vencimiento.");
        }
        double interes = 0.0;

        switch (cuota.getMesesAtraso()) {
            case 1:
                interes = 0.03;
                break;
            case 2:
                interes = 0.06;
                break;
            case 3:
                interes = 0.09;
                break;
            default:
                if (cuota.getMesesAtraso() > 3) {
                    interes = 0.15;
                }
                break;
        }
        double montoConInteres = cuota.getMontoCuota() * (1 + interes);
        cuota.setMontoCuota(montoConInteres);
        return cuotaRepository.save(cuota);
    }

    public double calcularDescuentoPorPromedio(double promedio) {
        if (promedio < 0 || promedio > 1000) {
            throw new IllegalArgumentException("El promedio debe estar entre o y 1000.");
        }
        double descuento = 0.0;
        if (promedio >= 950 && promedio <= 1000) {
            descuento = 0.10;
        } else if (promedio >= 900 && promedio <= 950) {
            descuento = 0.05;
        } else if (promedio >= 850 && promedio <= 900) {
            descuento = 0.02;
        }
        return descuento;
    }

    public Cuota registrarPagoCuota(Long idCuota) {
        Cuota cuota = cuotaRepository.findById(idCuota).orElseThrow(() -> new IllegalArgumentException("Cuota no encontrada"));

        if ("Pagado".equals(cuota.getEstadoCuota())) {
            throw new IllegalStateException("La cuota ya ha sido pagada.");
        }

        // Aplicar descuentos por promedio
        double promedio = examenService.calcularPromedioDeNotas(cuota.getAlumno().getRut());
        double descuentoPromedio = calcularDescuentoPorPromedio(promedio);
        cuota.setMontoCuota(cuota.getMontoCuota() - cuota.getMontoCuota() * descuentoPromedio);

        // Aplicar intereses por atraso si la fecha actual es posterior a la fecha de vencimiento
        if (LocalDate.now().isAfter(cuota.getFechaVencimiento())) {
            cuota = aplicarInteresPorAtraso(cuota);
        }

        cuota.setFechaPago(LocalDate.now());
        cuota.setEstadoCuota("Pagado");

        return cuotaRepository.save(cuota);
    }

    public Map<String, Object> generarPlanillaPagos(Alumno alumno) {
        List<Cuota> cuotas = getCuotasByRut(alumno.getRut());

        Double totalArancel = calculateArancelTotal(cuotas);
        int cantidadCuotas = cuotas.size();

        Map<String, Object> planillaData = new HashMap<>();
        planillaData.put("alumno", alumno);
        planillaData.put("cuotas", cuotas);
        planillaData.put("totalArancel", totalArancel);
        planillaData.put("cantidadCuotas", cantidadCuotas);

        return planillaData;
    }

    private Double calculateArancelTotal(List<Cuota> cuotas) {
        return cuotas.stream().mapToDouble(Cuota::getMontoCuota).sum();
    }

}




