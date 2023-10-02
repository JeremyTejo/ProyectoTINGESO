package Controllers;

import Entities.Alumno;
import Entities.Cuota;
import Services.AlumnoService;
import com.sun.xml.bind.util.AttributesImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/alumno")
public class AlumnoController {

    @Autowired
    private AlumnoService alumnoService;

    //Registrar un alumno

    @GetMapping("/Registrar")
    public String showForm(Model model){
        model.addAttribute("alumno", new Alumno());
        return "ingresarEstudiante";
    }

    @PostMapping("/crear")
    public String crearEstudiante(
            @RequestParam String rut,
            @RequestParam String nombre,
            @RequestParam String apellido,
            @RequestParam @DateTimeFormat(pattern = " yyyy-MM-dd")Date fechaNacimiento,
            @RequestParam String tipoColegio,
            @RequestParam String nombreColegio,
            @RequestParam int añoEgresoColegio){


        Alumno alumno = new Alumno(rut,nombre,apellido,fechaNacimiento,tipoColegio,nombreColegio,añoEgresoColegio);
        alumnoService.saveAlumno(alumno);
        return "redirect:/generarCuotas";
    }



    @GetMapping("/ListadoEstudiantes")
    private ResponseEntity<List<Alumno>> getAllAlumnos() {
        return ResponseEntity.ok(alumnoService.getAllAlumnos());
    }





}







