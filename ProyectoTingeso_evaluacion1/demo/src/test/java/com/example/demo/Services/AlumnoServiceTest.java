package com.example.demo.Services;

import com.example.demo.models.Alumno;
import com.example.demo.repositories.AlumnoRepository;
import com.example.demo.services.AlumnoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

public class AlumnoServiceTest {
    @InjectMocks
    private AlumnoService alumnoService;
    @Mock
    private AlumnoRepository alumnoRepository;
    @BeforeEach
    public void setUp(){
        MockitoAnnotations.initMocks(this);
    }
    @Test
    public void testGetAlumnoByRut() {
        String rut = "123456789"; // Ajusta este rut según tus necesidades
        Alumno alumno = new Alumno();
        alumno.setRut(rut);

        when(alumnoRepository.findById(rut)).thenReturn(Optional.of(alumno));

        Alumno retrievedAlumno = alumnoService.getAlumnoByRut(rut);
        assertNotNull(retrievedAlumno);
        assertEquals(rut, retrievedAlumno.getRut());
    }

    @Test
    public void testGetAlumnoByRutNotFound() {
        String rut = "1234567-8"; //

        when(alumnoRepository.findById(rut)).thenReturn(Optional.empty());

        Alumno retrievedAlumno = alumnoService.getAlumnoByRut(rut);
        assertNull(retrievedAlumno);
    }
    @Test
    public void updateAlumno_ReturnsNullWhenAlumnoDoesNotExist() {
        when(alumnoRepository.findByRut(anyString())).thenReturn(Optional.empty());

        Alumno result = alumnoService.updateAlumno("rut", new Alumno());

        assertNull(result);
    }

    @Test
    public void calcularMontoCuota_ReturnsCorrectValueForMunicipal() {
        Alumno alumno = new Alumno();
        alumno.setTipoColegio("Municipal");
        alumno.setFechaEgresoColegio("2020");

        double expectedValue = 121000.0;
        double result = alumnoService.calcularMontoCuota(alumno, false);

        assertEquals(expectedValue, result, 0.0);
    }

    @Test
    public void testGetAllAlumnos() {
        List<Alumno> alumnos = new ArrayList<>();
        alumnos.add(new Alumno());
        alumnos.add(new Alumno());

        when(alumnoRepository.findAll()).thenReturn(alumnos);

        List<Alumno> retrievedAlumnos = alumnoService.getAllAlumnos();
        assertNotNull(retrievedAlumnos);
        assertEquals(2, retrievedAlumnos.size());
    }

    @Test
    public void testSaveAlumno() {
        Alumno alumno = new Alumno();
        alumno.setFechaEgresoColegio("2020-01-01"); // Ajusta la fecha según tus necesidades

        when(alumnoRepository.save(any(Alumno.class))).thenReturn(alumno);

        Alumno savedAlumno = alumnoService.saveAlumno(alumno);
        assertNotNull(savedAlumno);
    }

    // Agrega más pruebas para otros métodos de AlumnoService según sea necesario.
}


