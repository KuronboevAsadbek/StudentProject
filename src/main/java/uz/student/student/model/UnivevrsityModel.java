package uz.student.student.model;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author Asadbek
 * @project IntelliJ IDEA
 * @Email asadbek9805@gmail.com
 * @Date 28.01.2023
 **/
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UnivevrsityModel implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String university;

    @ManyToOne
    @JoinColumn(name = "fields_of_study_id")
    private FieldsOfStudyModel fieldsOfStudyModel;




}
