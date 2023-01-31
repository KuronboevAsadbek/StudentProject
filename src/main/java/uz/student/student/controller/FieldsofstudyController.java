package uz.student.student.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.student.student.model.FieldsOfStudyModel;
import uz.student.student.service.FieldsofStudyService;

/**
 * @author Asadbek
 * @project IntelliJ IDEA
 * @Email asadbek9805@gmail.com
 * @Date 29.01.2023
 **/
@RestController
@RequestMapping("/api")
public class FieldsofstudyController {
    //
    private final FieldsofStudyService fieldsofStudyService;

    public FieldsofstudyController(FieldsofStudyService fieldsofStudyService) {
        this.fieldsofStudyService = fieldsofStudyService;
    }

    @PostMapping("/createfield")
    public ResponseEntity<?> createfield(@RequestBody FieldsOfStudyModel fieldsOfStudyModel){
    return ResponseEntity.ok(fieldsofStudyService.create(fieldsOfStudyModel));
    }

    @GetMapping("/getallf")
    public ResponseEntity<?> getall(){
        return ResponseEntity.ok(fieldsofStudyService.getall());
    }
    @DeleteMapping("/deletestudy/{id}")
    public  ResponseEntity delete(@PathVariable Long id){
        fieldsofStudyService.delete(id);
        return ResponseEntity.ok("Deleted!");
    }
    @GetMapping("/paging/getfield")
    public ResponseEntity<?> getAllbyPage(Pageable pageable){
        Page<FieldsOfStudyModel> result = fieldsofStudyService.findAll(pageable);
        return ResponseEntity.ok(result);
    }
}
