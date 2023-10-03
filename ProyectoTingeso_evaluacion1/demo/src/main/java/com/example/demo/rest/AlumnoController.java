package com.example.demo.rest;

import com.example.demo.services.AlumnoService;
import com.example.demo.models.Alumno;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/alumno")
public class AlumnoController {

    private final AlumnoService alumnoService;


    @Autowired
    public AlumnoController(AlumnoService alumnoService){
        this.alumnoService = alumnoService;
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
        return "listado";//redirigimos al listado de los estudiantes

    }


    @GetMapping("/listado")
    public String mostrarListado(Model model){
        List<Alumno> alumnos = alumnoService.getAllAlumnos();
        model.addAttribute("alumnos", alumnos);
        return "alumno/listado";
    }

    @GetMapping("/{rut}")
    public ResponseEntity<Alumno> getAlumnoByRut(@PathVariable String rut) {
        return alumnoService.findAllByRut(rut)
                .map(ResponseEntity::ok)
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/inicio")
    public String inicioPagina(Model model){
        return "index";
    }

    @PutMapping("/{rut}")
    public ResponseEntity<Alumno>updateAlumno(@PathVariable String rut, @RequestParam Alumno alumno ){
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

}