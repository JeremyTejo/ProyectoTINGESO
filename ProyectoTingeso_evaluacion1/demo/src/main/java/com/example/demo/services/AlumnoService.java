package com.example.demo.services;

import com.example.demo.models.Alumno;

import com.example.demo.repositories.AlumnoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.Optional;

@Service
public  class AlumnoService {

    private final AlumnoRepository alumnoRepository;

    @Autowired
    public AlumnoService(AlumnoRepository alumnoRepository){
        this.alumnoRepository = alumnoRepository;

    }
    public List<Alumno> getAllAlumnos() {
        return alumnoRepository.findAll();
    }

    public Alumno getAlumnoByRut(String rut) {
        return alumnoRepository.findById(rut).orElse(null);
    }

    public Alumno saveAlumno(Alumno alumno) {
        if (alumno.getFechaEgresoColegio() == null || alumno.getFechaEgresoColegio().trim().isEmpty()) {
            // Aquí puedes manejar el error como quieras: lanzar una excepción, devolver null, etc.
            throw new RuntimeException("La fecha de egreso es nula o vacía");
        }
        return alumnoRepository.save(alumno);
    }

    public Alumno updateAlumno(String rut, Alumno alumno) {
        Optional<Alumno> alumno1 = alumnoRepository.findByRut(rut);
        if (alumno1.isPresent()) {
            alumno.setRut(rut);
            return alumnoRepository.save(alumno);
        } else {
            return null;
        }

    }

    public Optional<Alumno> findByRut(String rut) {
        return alumnoRepository.findByRut(rut);
    }

    public void deleteAlumno(String rut) {
        alumnoRepository.deleteById(rut);
    }
    public double calcularMontoCuota(Alumno alumno, boolean esPagoContado) {
        double montoArancel = 1500000;
        double matricula = 70000;
        double descuento = 0.0;
        double descuentoPorTipoColegio = 0.0;

        switch (alumno.getTipoColegio()){
            case "Municipal":
                descuento = 0.20;
                break;
            case "Subvencionado":
                descuento = 0.10;
                break;
        }
        descuento +=descuentoPorTipoColegio;
        int anioEgreso = Integer.parseInt(alumno.getFechaEgresoColegio());
        int anioActual = LocalDate.now().getYear();
        int aniosDesdeEgreso = anioActual - anioEgreso;
        double tasaDescuentoAntiguedad;

        if (aniosDesdeEgreso < 1){
            tasaDescuentoAntiguedad = 0.15;
        }else if (aniosDesdeEgreso >= 1 && aniosDesdeEgreso <= 2){
            tasaDescuentoAntiguedad = 0.08;
        }else if (aniosDesdeEgreso >= 3 && aniosDesdeEgreso < 4){
            tasaDescuentoAntiguedad = 0.04;
        }else {
            tasaDescuentoAntiguedad = 0.0;
        }
        double descuentoTotal = montoArancel *(descuento + tasaDescuentoAntiguedad);

        if (esPagoContado){
            descuentoTotal += montoArancel * 0.50;
        }
        double montoTotal = matricula + montoArancel - descuentoTotal;

        int numeroDeCuotas;
        switch (alumno.getTipoColegio()){
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
        return montoTotal/numeroDeCuotas;
    }
}