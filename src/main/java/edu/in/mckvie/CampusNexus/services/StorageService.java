package edu.in.mckvie.CampusNexus.services;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.net.URL;

public interface StorageService {
        public boolean uploadFile(File file) ;
        public String uploadMultipartFile(MultipartFile file) ;
        public byte[] downloadFile(String fileName);
        public String deleteFile(String fileName) ;
        public URL serveS3URL(String bucketName, String fileName);

}
