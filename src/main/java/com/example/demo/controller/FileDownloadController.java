package com.example.demo.controller;

import com.example.demo.dto.ApiResponse;
import com.example.demo.service.FileDownloadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/files")
public class FileDownloadController {

    private final FileDownloadService fileDownloadService;

    @Autowired
    public FileDownloadController(FileDownloadService fileDownloadService) {
        this.fileDownloadService = fileDownloadService;
    }

    @GetMapping("/download/{id}/{fileName}")
    public ResponseEntity<Resource> downloadFile(@PathVariable("id") Long id,@PathVariable String fileName) throws IOException, IOException {
        Resource resource = fileDownloadService.loadFileAsResource(id,fileName);

        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        "inline; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    }

    @GetMapping("/urls/{id}")
    public ResponseEntity<?> getAllFileUrls(@PathVariable Long id) {
        List<String> urls = fileDownloadService.generateFileUrls(id);


        ApiResponse<Object> success = ApiResponse.builder()
                .message("success")
                .status(HttpStatus.OK.value())
                .data(urls)
                .build();
//        Map<String, List<String>> response = new HashMap<>();
//        response.put("urls", urls);

        return ResponseEntity.ok(success);
    }
}

