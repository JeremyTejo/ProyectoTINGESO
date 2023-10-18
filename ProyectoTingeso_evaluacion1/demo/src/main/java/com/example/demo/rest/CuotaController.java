package com.example.demo.rest;

import com.example.demo.models.Alumno;
import com.example.demo.services.AlumnoService;
import org.springframework.ui.Model;
import com.example.demo.models.Cuota;
import com.example.demo.services.CuotaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/cuota")
public class CuotaController {

    @Autowired
    private CuotaService cuotaService;
    @Autowired
    private AlumnoService alumnoService;

    // Mostrar formulario para generar cuotas
    @GetMapping("/formulario")
    public ModelAndView mostrarFormulario() {
        ModelAndView modelAndView = new ModelAndView("cuotas");
        return modelAndView;
    }

    @PostMapping("/generar")
    public String generarCuotas(@RequestParam String rut, RedirectAttributes redirectAttributes) {
        try {
            cuotaService.crearCuotas(rut);
            redirectAttributes.addFlashAttribute("successMessage", "Cuotas Generadas con exito!");
            return "redirect:/cuota/listaCuotas/" + rut;
        } catch (IllegalArgumentException e) {
            redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
            return "redirect:/cuota/formulario";
        }
    }

    @GetMapping("/listaCuotas/{rut}")
    public String listarCuotas(@PathVariable String rut, Model model) {
        List<Cuota> cuotas = cuotaService.getCuotasByRut(rut);
        if (cuotas == null || cuotas.isEmpty()) {
            model.addAttribute("error", "No hay cuotas para el RUT proporcionado.");
        } else {
            model.addAttribute("cuotas", cuotas);
        }
        return "listaCuotas"; // Retorna la vista de Thymeleaf
    }

    @PostMapping("/pagar/{idCuota}")
    public String pagarCuota(@PathVariable Long idCuota, RedirectAttributes redirectAttributes) {
        try {
            Cuota cuotaPagada = cuotaService.registrarPagoCuota(idCuota);
            redirectAttributes.addFlashAttribute("successMessage", "Cuota pagada con éxito por un monto de: " + cuotaPagada.getMontoCuota());
            return "redirect:/cuota/pagoExitoso/" + cuotaPagada.getAlumno().getRut();  // Redireccionar a la vista adecuada
        } catch (IllegalArgumentException | IllegalStateException e) {
            redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
            return "redirect:/cuota/listaCuotas";  // Redireccionar a la vista adecuada con mensaje de error
        }
    }

    @GetMapping("/pagoExitoso/{rut}")
    public String mostrarPagoExitoso(@PathVariable String rut, Model model){
        model.addAttribute("rut",rut);
        return "pagoExitoso";
    }

    @PostMapping("/generarPlanilla/{rut}")
    public String generarPlanilla(@PathVariable String rut, Model model, RedirectAttributes redirectAttributes) {
        Alumno alumno = alumnoService.findByRut(rut).orElse(null);
        if (alumno != null) {
            Map<String, Object> planillaData = cuotaService.generarPlanillaPagos(alumno);
            model.addAllAttributes(planillaData);
            redirectAttributes.addAttribute("rut",rut);
            return "mostrarPlanilla";  // Vista que mostrará la planilla generada
        } else {
            return "mostrarPlanilla";  // Página de error o mensaje de estudiante no encontrado
        }
    }







}


