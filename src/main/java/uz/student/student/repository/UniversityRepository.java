package uz.student.student.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.student.student.model.UnivevrsityModel;

/**
 * @author Asadbek
 * @project IntelliJ IDEA
 * @Email asadbek9805@gmail.com
 * @Date 29.01.2023
 **/
@Repository
public interface UniversityRepository extends JpaRepository<UnivevrsityModel, Long> {
    //

}
