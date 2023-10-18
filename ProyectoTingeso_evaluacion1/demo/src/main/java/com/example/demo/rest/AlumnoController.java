package com.example.demo.rest;

import com.example.demo.models.Cuota;
import com.example.demo.services.AlumnoService;
import com.example.demo.models.Alumno;
import com.example.demo.services.CuotaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/alumno")
public class AlumnoController {

    private final AlumnoService alumnoService;
    private final CuotaService cuotaService;


    @Autowired
    public AlumnoController(AlumnoService alumnoService, CuotaService cuotaService){
        this.alumnoService = alumnoService;
        this.cuotaService = cuotaService;
    }


    //Registrar un alumno
    @GetMapping("/registrar")
    public String registroAlumnoForm(Model model){
        model.addAttribute("alumno", new Alumno());
        return "registrarEstudiante";
    }
    //guardamos en la base de datos
    @PostMapping("/guardar")
    public String guardarAlumno(@ModelAttribute Alumno alumno){
        alumnoService.saveAlumno(alumno);
        return "redirect:/alumno/principal";//redirigimos al listado de los estudiantes

    }


    @GetMapping("/listado")
    public String mostrarListado(Model model){
        List<Alumno> alumnos = alumnoService.getAllAlumnos();
        model.addAttribute("alumnos", alumnos);
        return "/listado";
    }

    @GetMapping("/{rut}")
    public ResponseEntity<Alumno> getAlumnoByRut(@PathVariable String rut) {
        return alumnoService.findByRut(rut)
                .map(ResponseEntity::ok)
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/principal")
    public String inicioPagina(Model model){
        return "index";
    }

    @PutMapping("/{rut}")
    public ResponseEntity<Alumno>updateAlumno(@PathVariable String rut, @RequestBody Alumno alumno ){
        Alumno alumnoNuevo = alumnoService.updateAlumno(rut,alumno);
        if(alumnoNuevo != null){
            return new ResponseEntity<>(alumnoNuevo, HttpStatus.OK);
        }else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{rut}")
    public ResponseEntity<Void> deleteAlumno(@PathVariable String rut){
        Alumno alumno = alumnoService.getAlumnoByRut(rut);
        if (alumno != null){
            alumnoService.deleteAlumno(rut);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    @GetMapping("/{rut}/calcularCuota")
    public ResponseEntity<Double> calcularMontoCuota(@PathVariable String rut, @RequestParam boolean esPagoContado){
        Alumno alumno = alumnoService.getAlumnoByRut(rut);
        if (alumno != null){
            double montoCuota = alumnoService.calcularMontoCuota(alumno, esPagoContado);
            return new ResponseEntity<>(montoCuota,HttpStatus.OK);
        }else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

}