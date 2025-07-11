package com.example.demo.controller;

import com.example.demo.dto.ApiResponse;
import com.example.demo.dto.IOMRequestDto;
import com.example.demo.entity.IOMRequestMaster;
import com.example.demo.service.IOMRequestService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Collections;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/")
public class IOMRequestController {

    @Autowired
    private IOMRequestService iomRequestService;

    @PostMapping("iom-create")
    public ResponseEntity<?> createIomRequest(@RequestBody IOMRequestDto iomRequestDto){

        IOMRequestMaster iomRequest = iomRequestService.createIOMRequest(iomRequestDto);

        ApiResponse<Object> iomReuest = ApiResponse.builder()
                .status(HttpStatus.OK.value())
                .message("IOM request created successfully")
                .data(Collections.singletonMap("imo_req_id",iomRequest.getId()))
                .build();

        return ResponseEntity.ok(iomReuest);
    }

}
