package com.example.demo.Services;

import com.example.demo.models.Alumno;
import com.example.demo.models.Cuota;
import com.example.demo.repositories.CuotaRepository;
import com.example.demo.services.AlumnoService;
import com.example.demo.services.CuotaService;
import com.example.demo.services.ExamenService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class CuotaServiceTest {

    @InjectMocks
    private CuotaService cuotaService;

    @Mock
    private CuotaRepository cuotaRepository;

    @Mock
    private AlumnoService alumnoService;

    @Mock
    private ExamenService examenService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetAllCuotas() {
        List<Cuota> cuotas = new ArrayList<>();
        cuotas.add(new Cuota());
        cuotas.add(new Cuota());

        when(cuotaRepository.findAll()).thenReturn(cuotas);

        List<Cuota> retrievedCuotas = cuotaService.getAllCuotas();
        assertNotNull(retrievedCuotas);
        assertEquals(2, retrievedCuotas.size());
    }

    @Test
    public void testGetCuotasByRut() {
        String rut = "123456789"; // Ajusta este rut según tus necesidades
        List<Cuota> cuotas = new ArrayList<>();
        cuotas.add(new Cuota());
        cuotas.add(new Cuota());

        when(cuotaRepository.findAllByAlumnoRut(rut)).thenReturn(cuotas);

        List<Cuota> retrievedCuotas = cuotaService.getCuotasByRut(rut);
        assertNotNull(retrievedCuotas);
        assertEquals(2, retrievedCuotas.size());
    }

    @Test
    public void testSaveCuota() {
        Cuota cuota = new Cuota();

        when(cuotaRepository.save(any(Cuota.class))).thenReturn(cuota);

        Cuota savedCuota = cuotaService.saveCuota(cuota);
        assertNotNull(savedCuota);
    }

    @Test
    public void testCrearCuotas() {
        // Arrange
        String rut = "123456789"; // Rut válido para un alumno registrado
        Alumno alumno = new Alumno();
        alumno.setTipoColegio("Municipal"); // Tipo de colegio válido
        alumno.setFechaEgresoColegio("2019"); // Año de egreso válido
        when(alumnoService.getAlumnoByRut(rut)).thenReturn(alumno);
        double promedio = 8.5; // Promedio de notas válido
        when(examenService.calcularPromedioDeNotas(alumno.getRut())).thenReturn(promedio);
        List<Cuota> cuotasGeneradas = cuotaService.crearCuotas(rut);
        assertNotNull(cuotasGeneradas);
        assertEquals(10, cuotasGeneradas.size()); // Verifica que se generen 10 cuotas para colegio municipal
    }
    @Test
    public void aplicarInteresPorAtraso_ThrowsExceptionForInvalidFechaPago() {
        Cuota cuota = new Cuota();
        cuota.setFechaVencimiento(LocalDate.now().minusDays(1));
        cuota.setFechaPago(LocalDate.now());

        cuotaService.aplicarInteresPorAtraso(cuota);
    }
    @Test
    public void calcularDescuentoPorPromedio_ThrowsExceptionForInvalidPromedio() {
        assertThrows(IllegalArgumentException.class, () -> {
            cuotaService.calcularDescuentoPorPromedio(1100);
        });
    }





}
