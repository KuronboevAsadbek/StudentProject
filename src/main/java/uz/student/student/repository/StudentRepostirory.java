package uz.student.student.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import uz.student.student.model.StudentModel;

import java.util.List;


/**
 * @author Asadbek
 * @project IntelliJ IDEA
 * @Email asadbek9805@gmail.com
 * @Date 29.01.2023
 **/
@Repository
public interface StudentRepostirory extends JpaRepository<StudentModel, Long> {

    @Query("select s from StudentModel s where UPPER(s.firstName) like upper(concat('%', :firstName, '%'))")
    List<StudentModel> findByNameLikeStartWith(@Param("firstName") String firstName);






}
