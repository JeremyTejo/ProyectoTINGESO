package com.example.demo.services;

import com.example.demo.models.Alumno;

import com.example.demo.repositories.AlumnoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        return alumnoRepository.save(alumno);
    }

    public Alumno updateAlumno(String rut, Alumno alumno) {
        Optional<Alumno> alumno1 = alumnoRepository.findAllByRut(rut);
        if (alumno1.isPresent()) {
            alumno.setRut(rut);
            return alumnoRepository.save(alumno);
        } else {
            return null;
        }

    }

    public Optional<Alumno> findAllByRut(String rut) {
        return alumnoRepository.findAllByRut(rut);
    }

    public void deleteAlumno(String rut) {
        alumnoRepository.deleteById(rut);
    }
}