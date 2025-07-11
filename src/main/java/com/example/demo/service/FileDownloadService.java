package com.example.demo.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@Service
public class FileDownloadService {

    @Value("${file.upload-dir}")
    private String uploadDir;

    public Resource loadFileAsResource(Long id, String fileName) throws IOException {

        Path filePath = Paths.get(uploadDir, String.valueOf(id), fileName).normalize();

        Resource resource = new UrlResource(filePath.toUri());

        if (!resource.exists() || !resource.isReadable()) {
            throw new FileNotFoundException("File not found or unreadable: " + filePath);
        }

        return resource;

    }


    public List<String> generateFileUrls(Long folderId) {
        List<String> urls = new ArrayList<>();

        Path folderPath = Paths.get(uploadDir, String.valueOf(folderId)).normalize();
        File folder = folderPath.toFile();

        if (folder.exists() && folder.isDirectory()) {
            File[] files = folder.listFiles();

            if (files != null) {
                for (File file : files) {
                    if (file.isFile()) {
                        String fileUrl = ServletUriComponentsBuilder.fromCurrentContextPath()
                                .path("/api/files/download/")
                                .path(folderId + "/")
                                .path(file.getName())
                                .toUriString();
                        String replace = fileUrl.replace("http", "https");
                        urls.add(replace);
                    }
                }
            }
        }

        return urls;
    }

}