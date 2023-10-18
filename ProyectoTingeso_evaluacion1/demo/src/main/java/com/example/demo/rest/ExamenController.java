package com.example.demo.rest;

import com.example.demo.models.Alumno;
import com.example.demo.models.Examen;
import com.example.demo.services.AlumnoService;
import com.example.demo.services.ExamenService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Controller
@RequestMapping("/examenes")
public class ExamenController {

    @Autowired
    private ExamenService examenService;
    @Autowired
    private AlumnoService alumnoService;

    @PostMapping("/importar")
    public String importarExamenesDesdeExcel(@RequestParam("file") MultipartFile file, Model model) {
        try {
            List<Examen> examenesImportados = examenService.importarExamenesDesdeExcel(file);
            model.addAttribute("examenes", examenesImportados);
            return "vistaExamenImportado";  // Esta es la vista que mostrará los exámenes importados
        } catch (Exception e) {
            model.addAttribute("message", "Error al importar: " + e.getMessage());
            return "index"; //
        }
    }


    @GetMapping("/importar")
    public String mostrarFormularioImportar() {
        return "importarExamenes";  // Esta es la vista de Thymeleaf que deberías crear
    }

}



