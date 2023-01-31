package uz.student.student.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uz.student.student.model.FieldsOfStudyModel;
import uz.student.student.repository.FieldsofstudyRepository;

import java.util.List;

/**
 * @author Asadbek
 * @project IntelliJ IDEA
 * @Email asadbek9805@gmail.com
 * @Date 29.01.2023
 **/
@Service
public class FieldsofStudyService {
    //
    private final FieldsofstudyRepository fieldsofstudyRepository;

    public FieldsofStudyService(FieldsofstudyRepository fieldsofstudyRepository) {
        this.fieldsofstudyRepository = fieldsofstudyRepository;
    }
    public FieldsOfStudyModel create(FieldsOfStudyModel fieldsOfStudyModel){
        return fieldsofstudyRepository.save(fieldsOfStudyModel);
    }
    public List<FieldsOfStudyModel> getall(){
        return fieldsofstudyRepository.findAll();
    }
    public void delete(Long id){
        fieldsofstudyRepository.deleteById(id);
    }
    @Transactional
    public Page<FieldsOfStudyModel> findAll(Pageable pageable){
        return fieldsofstudyRepository.findAll(pageable);
    }
}
