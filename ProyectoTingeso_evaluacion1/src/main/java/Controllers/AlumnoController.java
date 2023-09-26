package Controllers;

import Entities.Alumno;
import Services.AlumnoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/alumno")
public class AlumnoController {

    @Autowired
    private AlumnoService alumnoService;

    //Registrar un alumno
    @PostMapping("/ingresarAlumno")
    public ResponseEntity<?> ingresarAlumno(@RequestBody Alumno alumno) {
        try {
            Alumno alumnoNuevo = alumnoService.guardarAlumno(alumno);
            return ResponseEntity.ok(alumnoNuevo);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }

    @PostMapping
    public ResponseEntity<Alumno> crearAlumno(@RequestBody Alumno alumno) {
        return ResponseEntity.ok(alumnoService.guardar(alumno));
    }

    @GetMapping("/ListadoEstudiantes")
    private ResponseEntity<List<Alumno>> getAllAlumnos() {
        return ResponseEntity.ok(alumnoService.listarAlumnos());
    }

    @GetMapping("/rut/{rut}")
    public ResponseEntity<?> obtenerAlumnoPorRut(@PathVariable String rut) {
        try {
            Optional<Alumno> alumno = alumnoService.buscarAlumnoPorRut(rut);
            if (alumno.isEmpty()) {
                return ResponseEntity.notFound().build();  // retorna un 404 Not Found
            }
            return ResponseEntity.ok(alumno.get());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }


}
/**    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarAlumno(@PathVariable Long id){
        if (!alumnoService.buscarPorId(id).isPresent()){
            return ResponseEntity.notFound().build();
        }
        alumnoService.deleteById(id);
        return ResponseEntity.noContent().build();**/









