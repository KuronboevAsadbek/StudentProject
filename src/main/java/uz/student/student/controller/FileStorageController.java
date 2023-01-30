package uz.student.student.controller;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileUrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.yaml.snakeyaml.util.UriEncoder;
import uz.student.student.model.FileStorageModel;
import uz.student.student.service.FileStorageService;

import java.io.NotActiveException;
import java.net.MalformedURLException;

import static uz.student.student.utils.EndPoint.pdfPath;

/**
 * @author Asadbek
 * @project IntelliJ IDEA
 * @Email asadbek9805@gmail.com
 * @Date 29.01.2023
 **/
@RestController
@RequestMapping("/api")
public class FileStorageController {
    private final FileStorageService fileStorageService;

    public FileStorageController(FileStorageService fileStorageService) {
        this.fileStorageService = fileStorageService;
    }
    @PostMapping("/uploadfile")
    public ResponseEntity<?> upload(@RequestParam("file") MultipartFile multipartFile){
        FileStorageModel fileStorageModel = fileStorageService.save(multipartFile);
        return ResponseEntity.ok(fileStorageModel);
    }

    @GetMapping("/file-preview/{hashid}")
    public ResponseEntity<?> preview(@PathVariable String hashid) throws MalformedURLException {
        FileStorageModel fileStorageModel = fileStorageService.findByHashId(hashid);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "inline; fileName=\""+ UriEncoder.encode(fileStorageModel.getName()))
                .contentType(MediaType.parseMediaType(fileStorageModel.getContentType()))
                .contentLength(fileStorageModel.getFileSize())
                .body(new FileUrlResource(String.format("%s/%s", pdfPath, fileStorageModel.getUploadFolder())));

    }
    @DeleteMapping("/delete/{hashid}")
    public ResponseEntity<?> delete(@PathVariable String hashid){
        fileStorageService.delete(hashid);
        return ResponseEntity.ok("File Deleted!");
    }

//    @PutMapping("/updatefile/{hashid}")
//    public ResponseEntity<?> update(@PathVariable String hashid,
//                                    @RequestBody FileStorageModel fileStorageModel) throws NotActiveException, MalformedURLException {
//       ResponseEntity.ok(fileStorageService.update(fileStorageModel, hashid));
//        return ResponseEntity.ok()
//                .header(HttpHeaders.CONTENT_DISPOSITION, "inline; fileName=\""+ UriEncoder.encode(fileStorageModel.getName()))
//                .contentType(MediaType.parseMediaType(fileStorageModel.getContentType()))
//                .contentLength(fileStorageModel.getFileSize())
//                .body(new FileUrlResource(String.format("%s/%s", pdfPath, fileStorageModel.getUploadFolder())));
//
//    }

}
