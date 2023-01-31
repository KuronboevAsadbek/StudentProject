package uz.student.student.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.student.student.model.StudentModel;
import uz.student.student.model.UnivevrsityModel;
import uz.student.student.service.StudentService;

import java.io.NotActiveException;
import java.util.List;
import java.util.Locale;

/**
 * @author Asadbek
 * @project IntelliJ IDEA
 * @Email asadbek9805@gmail.com
 * @Date 29.01.2023
 **/
@RestController
@RequestMapping("/api")
public class StudentController {
    //
    private final StudentService studentService;


    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping("/createStudent")
    public ResponseEntity<?> create(@RequestBody StudentModel studentModel) {
        return ResponseEntity.ok(studentService.create(studentModel));
    }

    @GetMapping("/getStudents")
    public ResponseEntity<?> getAll() {
        return ResponseEntity.ok(studentService.getall());
    }

    @GetMapping("/getone/{id}")
    public ResponseEntity<?> getone(@PathVariable Long id) {
        return ResponseEntity.ok(studentService.findOne(id));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable Long id,
                                    @RequestBody StudentModel studentModel) throws NotActiveException {
        return ResponseEntity.ok(studentService.update(studentModel, id));
    }

    @DeleteMapping("/deletestudent/{id}")
    public ResponseEntity delete(@PathVariable Long id) {
        studentService.delete(id);
        return ResponseEntity.ok(id + " Deleted");
    }

    @GetMapping("/getallasc")
    public ResponseEntity<?> getallasc() {
        return ResponseEntity.ok(studentService.findAllasc());
    }

    @GetMapping("/getalldesc")
    public ResponseEntity<?> getalldesc() {
        return ResponseEntity.ok(studentService.findAlldesc());
    }

    @GetMapping("/getbystartname")
    public ResponseEntity<?> findByStartName(@RequestParam String firstName) {
        List<StudentModel> studentModels = studentService.findByNameStartWith(firstName.toUpperCase(Locale.ROOT));
        return ResponseEntity.ok(studentModels);
    }

    @GetMapping("/posts/paging")
    public ResponseEntity<?> getAllbyPaging(Pageable pageable) {
        Page<StudentModel> result = studentService.findAll(pageable);
        return    ResponseEntity.ok(result);
    }
}
