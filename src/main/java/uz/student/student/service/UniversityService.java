package uz.student.student.service;

import org.springframework.stereotype.Service;
import uz.student.student.model.FieldsOfStudyModel;
import uz.student.student.model.FileStorageModel;
import uz.student.student.model.UnivevrsityModel;
import uz.student.student.repository.UniversityRepository;

import java.util.List;

/**
 * @author Asadbek
 * @project IntelliJ IDEA
 * @Email asadbek9805@gmail.com
 * @Date 29.01.2023
 **/
@Service
public class UniversityService {
    //
    private final UniversityRepository universityRepository;

    public UniversityService(UniversityRepository universityRepository) {
        this.universityRepository = universityRepository;
    }

    public UnivevrsityModel create(UnivevrsityModel univevrsityModel){
    return universityRepository.save(univevrsityModel);
    }

    public List<UnivevrsityModel> getAll(){
        return universityRepository.findAll();
    }
    public void delete(Long id){
        universityRepository.deleteById(id);
    }



}
