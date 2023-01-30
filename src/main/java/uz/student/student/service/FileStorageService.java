package uz.student.student.service;

import org.hashids.Hashids;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import uz.student.student.enumuration.FileStorageEnum;
import uz.student.student.model.FileStorageModel;
import uz.student.student.repository.FileStorageRepository;

import java.io.File;
import java.io.IOException;
import java.io.NotActiveException;
import java.util.Date;

import static uz.student.student.utils.EndPoint.pdfPath;

/**
 * @author Asadbek
 * @project IntelliJ IDEA
 * @Email asadbek9805@gmail.com
 * @Date 29.01.2023
 **/
@Service
public class FileStorageService {
    private final Hashids hashids;

    private final FileStorageRepository fileStorageRepository;

    public FileStorageService(FileStorageRepository fileStorageRepository) {
        this.fileStorageRepository = fileStorageRepository;
        this.hashids = new Hashids(getClass().getName(), 6);

    }

    public FileStorageModel save(MultipartFile multipartFile){
        FileStorageModel fileStorageModel = new FileStorageModel();
        fileStorageModel.setName(multipartFile.getOriginalFilename());
        fileStorageModel.setFileSize(multipartFile.getSize());
        fileStorageModel.setContentType(multipartFile.getContentType());
        fileStorageModel.setExtension(getExt(multipartFile.getOriginalFilename()));
        fileStorageModel.setFileStorageEnum(FileStorageEnum.DRAFT);
        fileStorageModel = fileStorageRepository.save(fileStorageModel);

        // path
        Date now = new Date();
        String path =String.format("%s/upload_files/%d/%d/%d", pdfPath, 1900 +
                now.getYear(), 1 + now.getMonth(), now.getDate());
        File uploadfolder = new File(path);
        if (!uploadfolder.exists() && uploadfolder.mkdirs()){
            System.out.println("Folder Created ");
        }
        fileStorageModel.setHashId(hashids.encode(fileStorageModel.getId()));
        String pathLocal =String.format("/upload_files/%d/%d/%d/%s.%s",
                1900 + now.getYear(),
                1 + now.getMonth(),
                now.getDate(),
                fileStorageModel.getHashId(),
                fileStorageModel.getExtension());

        fileStorageModel.setUploadFolder(pathLocal);
        fileStorageRepository.save(fileStorageModel);
        uploadfolder = uploadfolder.getAbsoluteFile();
        File file= new File(uploadfolder, String.format("%s.%s",
                fileStorageModel.getHashId(), fileStorageModel.getExtension()));
        try {
            multipartFile.transferTo(file);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return fileStorageModel;
    }
    public FileStorageModel findByHashId(String hashId){
        return fileStorageRepository.findByHashId(hashId);
    }
    public void delete(String hashid){
        FileStorageModel fileStorageModel = fileStorageRepository.findByHashId(hashid);
        File file = new File(String.format("%s/%s", pdfPath, fileStorageModel.getUploadFolder()));
        if (file.delete()){
            fileStorageRepository.delete(fileStorageModel);
        }
    }

    private String getExt(String fileName){
        String ext=null;
        if (fileName!=null && !fileName.isEmpty()){
            int dot = fileName.lastIndexOf('.');
            if (dot > 0 && dot<=fileName.length() - 2){
                ext=fileName.substring(dot+1);
            }
        }
        return ext;
    }
//    public FileStorageModel update(FileStorageModel fileStorageModel, String hashid) throws NotActiveException {
//        FileStorageModel model = fileStorageRepository.findByHashId(hashid);
//        if (fileStorageModel.getName() !=null){
//            model.setName(fileStorageModel.getName());
//        }
//        if (fileStorageModel.getFileSize() !=null){
//            model.setFileSize(fileStorageModel.getFileSize());
//        }
//        if (fileStorageModel.getContentType() !=null){
//            model.setContentType(fileStorageModel.getContentType());
//        }
//        if ((fileStorageModel.getHashId()) != null){
//            model.setHashId(fileStorageModel.getHashId());
//        }
//        if (fileStorageModel.getUploadFolder() != null){
//            model.setUploadFolder(fileStorageModel.getUploadFolder());
//        }
//        if (fileStorageModel.getFileStorageEnum() != null){
//            model.setFileStorageEnum(fileStorageModel.getFileStorageEnum());
//        }
//        if (fileStorageModel.getExtension() != null){
//            model.setExtension(fileStorageModel.getExtension());
//
//        }
//        return fileStorageRepository.save(model);
//    }

}
