package com.example.demo.Services;

import com.example.demo.models.Alumno;
import com.example.demo.models.Examen;
import com.example.demo.repositories.ExamenRepository;
import com.example.demo.services.AlumnoService;
import com.example.demo.services.ExamenService;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class ExamenServiceTest {

        @Mock
        private ExamenRepository examenRepository;

        @Mock
        private AlumnoService alumnoService;

        @InjectMocks
        private ExamenService examenService;



    @Test
    public void calcularPromedioDeNotas() {
        Examen examen1 = new Examen();
        examen1.setNota(90.0);

        Examen examen2 = new Examen();
        examen2.setNota(90.0);

        Examen examen3 = new Examen();
        examen3.setNota(20.0);

        List<Examen> examenes = Arrays.asList(examen1, examen2, examen3);
        when(examenRepository.findAllByAlumnoRut(anyString())).thenReturn(examenes);

        Double result = examenService.calcularPromedioDeNotas("someRut");

        assertEquals(66.67, result, 0.01);

    }

    @Test
    public void importarExamenesDesdeExcel_ImportsDataCorrectly() throws Exception {
        // Crear un archivo Excel ficticio en memoria
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        try (XSSFWorkbook workbook = new XSSFWorkbook()) {
            XSSFSheet sheet = workbook.createSheet();
            XSSFRow header = sheet.createRow(0);
            header.createCell(0).setCellValue("RUT");
            header.createCell(1).setCellValue("Fecha");
            header.createCell(2).setCellValue("Nota");
            header.createCell(3).setCellValue("Materia");

            XSSFRow dataRow = sheet.createRow(1);
            dataRow.createCell(0).setCellValue("12345678-9");
            dataRow.createCell(1).setCellValue(new Date());
            dataRow.createCell(2).setCellValue(90.0);
            dataRow.createCell(3).setCellValue("Matemáticas");

            workbook.write(bos);
        }
        byte[] bytes = bos.toByteArray();
        InputStream is = new ByteArrayInputStream(bytes);

        MultipartFile mockFile = mock(MultipartFile.class);
        when(mockFile.getInputStream()).thenReturn(is);

        Alumno mockAlumno = new Alumno();
        mockAlumno.setRut("12345678-9");
        when(alumnoService.getAlumnoByRut(anyString())).thenReturn(mockAlumno);

        List<Examen> result = examenService.importarExamenesDesdeExcel(mockFile);

        // Verificar que result tiene los datos esperados
        assertEquals(1, result.size());
        assertEquals("12345678-9", result.get(0).getAlumno().getRut());
        assertEquals(90.0, result.get(0).getNota(), 0.0);
        assertEquals("Matemáticas", result.get(0).getMateria());

        // Verificar que se haya llamado a examenRepository.saveAll() con esos datos
        verify(examenRepository).saveAll(anyList());
    }



}
