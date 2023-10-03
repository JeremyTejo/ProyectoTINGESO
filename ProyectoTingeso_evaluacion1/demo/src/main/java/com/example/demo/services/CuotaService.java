package com.example.demo.services;

import com.example.demo.models.Cuota;
import com.example.demo.repositories.CuotaRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class CuotaService {

    @Autowired
    private CuotaRepository cuotaRepository;

    public List<Cuota> obtenerCuotasPorEstudianteRut(String rut){
        return cuotaRepository.findByAlumnoRut(rut);
    }
    public Cuota obtenerCuotaPorId(Long id){
        return cuotaRepository.findById(id).orElse(null);
    }
    public Cuota saveCuota(Cuota cuota){
        return cuotaRepository.save(cuota);
    }
}
