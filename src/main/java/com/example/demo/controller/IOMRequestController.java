package com.example.demo.controller;

import com.example.demo.dto.ApiResponse;
import com.example.demo.dto.IOMRequestDto;
import com.example.demo.entity.IOMRequestMaster;
import com.example.demo.repository.IomRequestRepository;
import com.example.demo.service.IOMRequestService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import java.util.OptionalInt;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/")
public class IOMRequestController {

    @Autowired
    IomRequestRepository iomRequestRepository;


    @PostMapping("createNewRequest")
    public ResponseEntity<?> createIomRequest(@RequestBody Map<String,String> iomRequestDto) {

        OptionalInt res = iomRequestRepository.insertRequest(iomRequestDto);

        if(res.isEmpty()){
            return ResponseEntity.badRequest().build();
        }

        return ResponseEntity.ok(res.getAsInt());

    }

}
