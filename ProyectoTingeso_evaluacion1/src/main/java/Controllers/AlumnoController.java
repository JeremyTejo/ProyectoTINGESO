package Controllers;

import Entities.Alumno;
import Services.AlumnoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/alumno")
public class AlumnoController {

    @Autowired
    private AlumnoService alumnoService;

    @PostMapping
    public ResponseEntity<Alumno> crearAlumno(@RequestBody Alumno alumno){
        return ResponseEntity.ok(alumnoService.guardar(alumno));
    }

    @GetMapping
    private ResponseEntity<List<Alumno>>getAllAlumnos(){
        return ResponseEntity.ok(alumnoService.listarAlumnos());
    }

/**    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarAlumno(@PathVariable Long id){
        if (!alumnoService.buscarPorId(id).isPresent()){
            return ResponseEntity.notFound().build();
        }
        alumnoService.deleteById(id);
        return ResponseEntity.noContent().build();**/

//Ingresar un alumno al registro
    @PostMapping
    public ResponseEntity<Alumno> ingresarAlumno(@RequestBody Alumno alumno ){
        Alumno alumnoRegistrado = alumnoService.guardar(alumno);
        return ResponseEntity.ok(alumnoRegistrado);



    }


}
