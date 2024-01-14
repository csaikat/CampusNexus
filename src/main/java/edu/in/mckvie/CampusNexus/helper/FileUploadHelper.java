package edu.in.mckvie.CampusNexus.helper;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Component
@Slf4j
public class FileUploadHelper {
    public final String UPLOAD_DIR=new ClassPathResource("static/tmp/").getFile().getAbsolutePath();

    public FileUploadHelper() throws IOException {
    }

    public boolean uploadFile(MultipartFile file){
        boolean f=false;
        try{
            /*
            //read
            InputStream is=file.getInputStream();
            byte data[]=new byte[is.available()];
            is.read(data);
            //write
            FileOutputStream fos=new FileOutputStream(UPLOAD_DIR+ File.separator+file.getOriginalFilename());
            fos.write(data);
            fos.flush();
            fos.close();
            */
            log.info(UPLOAD_DIR);
            Files.copy(file.getInputStream(), Paths.get(UPLOAD_DIR+File.separator+file.getOriginalFilename()), StandardCopyOption.REPLACE_EXISTING);
            f=true;
        }catch(Exception e){
            e.printStackTrace();
        }
        return f;
    }

}
