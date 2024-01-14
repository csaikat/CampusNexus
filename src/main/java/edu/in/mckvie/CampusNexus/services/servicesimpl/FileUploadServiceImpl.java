package edu.in.mckvie.CampusNexus.services.servicesimpl;

import edu.in.mckvie.CampusNexus.helper.FileUploadHelper;
import edu.in.mckvie.CampusNexus.services.FileUploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@Service
public class FileUploadServiceImpl implements FileUploadService {
    @Autowired
    private FileUploadHelper fileUploadHelper;
    @Override
    public String uploadFile(MultipartFile file) {
        try{
            //file upload
            if(fileUploadHelper.uploadFile(file)){
                String path=ServletUriComponentsBuilder.fromCurrentContextPath().path("/tmp/").path(file.getOriginalFilename()).toUriString();
                return path;
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
