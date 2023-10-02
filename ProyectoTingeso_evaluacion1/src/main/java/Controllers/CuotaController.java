package Controllers;


import Entities.Cuota;
import Services.CuotaService;
import Services.PagoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/cuotas")
public class CuotaController {

    @Autowired
    private CuotaService cuotaService;
    @Autowired
    private PagoService pagoService;

    @GetMapping("/{id}")
    public ResponseEntity<Cuota> getById(@PathVariable Long id){
        Cuota cuota = cuotaService.findById(id);
        if (cuota != null){
            return ResponseEntity.ok(cuota);
        }
        return ResponseEntity.notFound().build();
    }
    @GetMapping
    public List<Cuota> getAll(){
        return cuotaService.findAll();
    }
    @PostMapping
    public Cuota create(@RequestBody Cuota cuota){
        return cuotaService.saveCuota(cuota);
    }
    @PutMapping("/{id}")
    public ResponseEntity<Cuota>update(@PathVariable Long id, @RequestBody Cuota cuota){
            Cuota updateCuota = cuotaService.update(id,cuota);
            if (updateCuota != null){
                return ResponseEntity.ok(updateCuota);
            }
            return ResponseEntity.notFound().build();
    }

    @GetMapping("/listar")
    public String listarCuotasPorEstudianteForm(){
        return "listarCuotasForm";
    }
    /**
    @PostMapping("/listar")
    public String obtenerCuotasEstudiantesSubmit(@RequestParam String rut, Model model){
        List<Cuota> cuotas = pagoService.ObtenerCuotasRutEstudiantes(rut);
        model.addAttribute("cuotas",cuotas);
        return "listarCuotas";
    }
    **/
}

