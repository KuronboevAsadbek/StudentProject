package uz.student.student.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.student.student.model.StudentModel;
import uz.student.student.service.Exporter;
import uz.student.student.service.ExporterPdf;
import uz.student.student.service.StudentService;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author Asadbek
 * @project IntelliJ IDEA
 * @Email asadbek9805@gmail.com
 * @Date 30.01.2023
 **/
@RestController
@RequestMapping("api")
public class PdfController {
    private  final  StudentService studentService;

    public PdfController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/pdf/{id}")
    public void exportPdf(@PathVariable Long id,
                          HttpServletResponse response) throws IOException {
        response.setContentType("application/pdf");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=users_" + currentDateTime + ".pdf";
        response.setHeader(headerKey, headerValue);

        StudentModel studentModel = studentService.findOne(id);


        ExporterPdf pdf = new ExporterPdf(studentModel);
        pdf.export(response);
    }
}
