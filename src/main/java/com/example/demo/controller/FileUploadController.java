package com.example.demo.controller;

import com.example.demo.dto.ApiResponse;
import com.example.demo.service.FileStorageService;
import org.hibernate.sql.ast.tree.expression.Collation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Collections;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/files")
public class FileUploadController {

    @Autowired
    private FileStorageService fileStorageService;

    @PostMapping("/upload")
    public ResponseEntity<?> uploadFiles(@RequestParam("files") MultipartFile[] files) {
        try {
            fileStorageService.storeFiles(files);

            ApiResponse<Object> build = ApiResponse.builder()
                    .message("Files uploaded successfully.")
                    .status(HttpStatus.OK.value())
                    .data(Collections.emptyList()).build();
            return ResponseEntity.ok(build);
        } catch (IOException e) {
            ApiResponse<Object> build = ApiResponse.builder()
                    .message("File upload failed")
                    .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                    .data(Collections.emptyList()).build();
          return new ResponseEntity<>(build,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
