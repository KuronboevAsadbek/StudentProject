package uz.student.student.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.student.student.model.UnivevrsityModel;
import uz.student.student.service.UniversityService;

/**
 * @author Asadbek
 * @project IntelliJ IDEA
 * @Email asadbek9805@gmail.com
 * @Date 29.01.2023
 **/
@RestController
@RequestMapping("/api")
public class UniverstyController {
    //
    private  final UniversityService universityService;

    public UniverstyController(UniversityService universityService) {
        this.universityService = universityService;
    }

    @PostMapping("/create")
    public ResponseEntity<?>create(@RequestBody UnivevrsityModel universityModel){
    return ResponseEntity.ok(universityService.create(universityModel));
    }

    @GetMapping("/getuniver")
    public ResponseEntity<?> getuniver(){
        return ResponseEntity.ok(universityService.getAll());
    }


    @DeleteMapping("/deleteuniver/{id}")
        public ResponseEntity delete (@PathVariable Long id){
            universityService.delete(id);
            return ResponseEntity.ok("Deleted!");
        }

}
