package uz.student.student.service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uz.student.student.model.StudentModel;
import uz.student.student.repository.StudentRepostirory;

import java.io.NotActiveException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


/**
 * @author Asadbek
 * @project IntelliJ IDEA
 * @Email asadbek9805@gmail.com
 * @Date 29.01.2023
 **/
@Service
public class StudentService {
    //
    private final StudentRepostirory studentRepostirory;

    public StudentService(StudentRepostirory studentRepostirory) {
        this.studentRepostirory = studentRepostirory;
    }

    public StudentModel create(StudentModel studentModel) {
        String pattern = "MM-dd-yyyy";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        String date = simpleDateFormat.format(new Date());
        studentModel.setCreatedAt(date);
        return studentRepostirory.save(studentModel);
    }

    public List<StudentModel> getall() {
        List<StudentModel> studentModels = studentRepostirory.findAll();
        return studentModels;
    }

    public StudentModel findOne(Long id) {
        return studentRepostirory.findById(id).get();
    }

    public StudentModel update(StudentModel studentModel, Long id) throws NotActiveException {
        StudentModel studetnbaza = studentRepostirory.findById(id).orElseThrow(NotActiveException::new);

        if (studentModel.getDescription() != null){
            studetnbaza.setDescription(studentModel.getDescription());
        }
        if (studentModel.getFirstName()!=null){
            studetnbaza.setFirstName(studentModel.getFirstName());
        }
        if (studentModel.getLastName()!=null){
            studetnbaza.setLastName(studentModel.getLastName());
        }
        if (studentModel.getMiddleName() != null) {
            studetnbaza.setMiddleName(studentModel.getMiddleName());
        }
        if (studentModel.getStudyEndDate() != null) {
            studetnbaza.setStudyEndDate(studentModel.getStudyEndDate());
        }
        if (studentModel.getStudyStatDate() != null) {
            studetnbaza.setStudyStatDate(studentModel.getStudyStatDate());
        }
        if (studentModel.getUnevrsityModel() != null) {
            studetnbaza.setUnevrsityModel(studentModel.getUnevrsityModel());
        }
        if (studentModel.getFileStorageModel() !=null){
            studetnbaza.setFileStorageModel(studentModel.getFileStorageModel());
        }
        return studentRepostirory.save(studetnbaza);
    }

    public void  delete(Long id) {
        studentRepostirory.deleteById(id);
    }
    public List<StudentModel> findAllasc(){
      List<StudentModel> studentModels =
            studentRepostirory.findAll(Sort.by("firstName").ascending());
      return studentModels;
    }

    public List<StudentModel> findAlldesc(){
        List<StudentModel> studentModels =
                studentRepostirory.findAll(Sort.by("firstName").descending());
        return studentModels;
    }

    public List<StudentModel> findByNameStartWith(String firstName){
        return studentRepostirory.findByNameLikeStartWith(firstName);
    }

    @Transactional(readOnly = true)
    public Page<StudentModel> findAll(Pageable pageable){
        return studentRepostirory.findAll(pageable);

    }





    }
