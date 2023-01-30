package uz.student.student.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import uz.student.student.enumuration.FileStorageEnum;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * @author Asadbek
 * @project IntelliJ IDEA
 * @Email asadbek9805@gmail.com
 * @Date 29.01.2023
 **/
@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class FileStorageModel implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String extension;
    @JsonProperty("file_size")
    private Long fileSize;
    @JsonProperty("content_type")
    private String contentType;
    @JsonProperty("hash_id")
    private String hashId;
    @JsonProperty("upload_foler")
    private String uploadFolder;
    private FileStorageEnum fileStorageEnum;


}
