package Controllers;


import Entities.Alumno;
import Entities.Pago;
import Repositories.PagoRepositories;
import Services.PagoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Controller
@RequestMapping("/pago")
public class PagoController {
    @Autowired
    private PagoService pagoService;

    @GetMapping
    public List<Pago> buscarPagos(){
        return pagoService.todosLosPagos();
    }

    @PostMapping
    public Pago guardarPagos(@RequestBody Pago pago){
        return pagoService.guardarPago(pago);
    }

    @PostMapping("/generarCuotas/{id}")
    public ResponseEntity<List<Pago>> generarCuotas(@PathVariable Long idAlumno){
        List<Pago> cuotas = pagoService.generarCuotas(idAlumno);
        if(cuotas == null|| cuotas.isEmpty()){
            return ResponseEntity.badRequest().body(null);
        }
        return ResponseEntity.ok(cuotas);

    }
}
