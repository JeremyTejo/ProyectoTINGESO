package Controllers;


import Entities.Alumno;
import Entities.Cuota;
import Entities.Pago;
import Services.AlumnoService;
import Services.PagoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping("/pago")
public class PagoController {
    @Autowired
    private PagoService pagoService;
    @Autowired
    private AlumnoService alumnoService;

    @GetMapping
    public List<Pago> todosLosPagos(){
        return pagoService.getAllPagos();
    }

    @PostMapping
    public Pago guardarPagos(@RequestBody Pago pago){
        return pagoService.savePago(pago);
    }

    @GetMapping("/{id}")
    public Pago getPagoById(@PathVariable Long id){
        return pagoService.getPagoById(id);
    }
    /**
    @PostMapping("/generarCuotas")
    public ResponseEntity<Pago> generarCuotas(@RequestParam String rut, @RequestParam Double montoTotal, @RequestParam int numeroCuotas,
                                              @RequestParam Alumno.TipoColegio tipoColegio, @RequestParam int añoEgreso){
        try {
            Pago newPago = pagoService.crearPagoConCuotas(rut,numeroCuotas,montoTotal, tipoColegio,añoEgreso);
            if (newPago != null){
                return new ResponseEntity<>(newPago, HttpStatus.CREATED);
            }else{
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

**/
}