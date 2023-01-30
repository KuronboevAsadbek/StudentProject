package uz.student.student.model;

import com.fasterxml.jackson.annotation.JsonProperty;
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
public class StudentModel implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonProperty("first_name")
    private String firstName;
    @JsonProperty("last_name")
    private String lastName;
    @JsonProperty("middle_name")
    private String middleName;
    private String description;
    @JsonProperty("study_stat_date")
    private String studyStatDate;
    @JsonProperty("study_end_date")
    private String studyEndDate;
    @ManyToOne
    @JoinColumn(name = "university_id")
    private UnivevrsityModel unevrsityModel;

    @OneToOne
    private FileStorageModel fileStorageModel;

    @Column(updatable = false)
    @JsonProperty("created_at")
    private String createdAt;

}
