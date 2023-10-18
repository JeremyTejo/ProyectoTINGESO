package com.example.demo.services;

import com.example.demo.models.Alumno;
import com.example.demo.models.Examen;
import com.example.demo.repositories.ExamenRepository;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
@Service
public class ExamenService {

    @Autowired
    private ExamenRepository examenRepository;
    @Autowired
    private AlumnoService alumnoService;

    public Double calcularPromedioDeNotas(String rut){
        List<Examen> examenes =examenRepository.findAllByAlumnoRut(rut);
        return examenes.stream().mapToDouble(Examen::getNota).average().orElse(0.0);
    }

    public List<Examen> importarExamenesDesdeExcel(MultipartFile file)throws Exception {
        List<Examen> examenes = new ArrayList<>();
        try (XSSFWorkbook workbook = new XSSFWorkbook(file.getInputStream())) {
            XSSFSheet sheet = workbook.getSheetAt(0);

            for (Row row : sheet) {
                if (row.getRowNum() == 0) continue;

                String rut = row.getCell(0).getStringCellValue();
                Alumno alumno = alumnoService.getAlumnoByRut(rut);
                if (alumno == null) {
                    throw new Exception("El alumno con RUT " + rut + "no existe.");
                }
                Examen examen = new Examen();
                examen.setFechaExamen(row.getCell(1).getDateCellValue().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
                examen.setNota(row.getCell(2).getNumericCellValue());
                examen.setMateria(row.getCell(3).getStringCellValue());
                examen.setAlumno(alumno);
                examenes.add(examen);
            }
            examenRepository.saveAll(examenes);
        }
        return examenes;
    }
}
