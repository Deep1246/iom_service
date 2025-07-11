package com.example.demo.controller;

import com.example.demo.service.GenerateDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

@RestController
public class FetchDataForDocument {
    @Autowired
    GenerateDocument generateDocument;

    @GetMapping("getDoc/{requestId}")
    public  ResponseEntity<?> fetchDataAndGenerateDoc(@PathVariable String requestId) {

        var response = generateDocument.getData(requestId);

        var headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType("application/vnd.openxmlformats-officedocument.wordprocessingml.document"));

        headers.setContentDisposition(
                ContentDisposition
                        .attachment()
                        .filename("output.docx").build());
        if(Objects.nonNull(response)){
            return ResponseEntity.ok().headers(headers).body(response);
        }
        return ResponseEntity.internalServerError().build();
    }


}
