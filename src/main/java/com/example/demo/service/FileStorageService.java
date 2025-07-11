package com.example.demo.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;

@Service
public class FileStorageService {

    @Value("${file.upload-dir}")
    private String uploadDir;

    public void storeFiles(MultipartFile[] files,Long iomId) throws IOException {

        Path folderPath = Paths.get(uploadDir, String.valueOf(iomId));


        if (!Files.exists(folderPath)) {
            Files.createDirectories(folderPath);
        }

        for (MultipartFile file : files) {
            // Clean file name
            String fileName = StringUtils.cleanPath(file.getOriginalFilename());
            Path targetPath = folderPath.resolve(fileName);
            // Copy file to the target location
            Files.copy(file.getInputStream(), targetPath, StandardCopyOption.REPLACE_EXISTING);
        }
    }
}
