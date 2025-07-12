//package com.example.demo.controller;
//
//import com.example.demo.dto.ApiResponse;
//import com.example.demo.dto.IOMRequestDto;
//import com.example.demo.dto.WorkflowActionDto;
//import com.example.demo.entity.IOMRequestMaster;
//import com.example.demo.repository.IomRequestRepository;
//import com.example.demo.service.IOMRequestService;
//import org.apache.coyote.Response;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.*;
//
//@RestController
//@CrossOrigin(origins = "*")
//@RequestMapping("/api/")
//public class IOMRequestController {
//
//    @Autowired
//    IomRequestRepository iomRequestRepository;
//
//
//    @PostMapping("createNewRequest")
//    public ResponseEntity<?> createIomRequest(@RequestBody Map<String,String> iomRequestDto) {
//
//        OptionalInt res = iomRequestRepository.insertRequest(iomRequestDto);
//
//        if(res.isEmpty()){
//            return ResponseEntity.badRequest().build();
//        }
//        Map<String, Object> response = new HashMap<>();
//        response.put("request_id", res.getAsInt());
//        return ResponseEntity.ok().body(response);
//
//    }
//
////    @PostMapping("workflow-action")
////    public ResponseEntity<?> approveWorkflow(@RequestBody WorkflowActionDto workflowActionDto){
////
////        return ResponseEntity.ok();
////    }
//
//}
