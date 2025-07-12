package com.example.demo.controller;

import com.example.demo.dto.ApiResponse;
import com.example.demo.entity.Requests;
import com.example.demo.service.RequestsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/requests")
public class RequestsController {

    @Autowired
    private RequestsService requestsService;

    @GetMapping("/{id}")
    public ResponseEntity<?> getAllFileUrls(@PathVariable int id) {

        Optional<Requests> requests = requestsService.findRequestById(id);

        if (requests.isPresent()) {


            ApiResponse<Object> success = ApiResponse.builder()
                    .message("success")
                    .status(HttpStatus.OK.value())
                    .data(requests)
                    .build();
//        Map<String, List<String>> response = new HashMap<>();
//        response.put("urls", urls);

            return ResponseEntity.ok(success);
        } else {
            ApiResponse<Object> build = ApiResponse.builder()
                    .message("Bad request")
                    .status(HttpStatus.BAD_REQUEST.value())
                    .data(Collections.emptyList()).build();
            return new ResponseEntity<>(build,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @PostMapping("/update/{id}")
    public ResponseEntity<?> updateData(@PathVariable int id, @RequestBody Requests data) {

        boolean success = requestsService.updateData(id,data);

        if(success){
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.badRequest().build();

    }

    @PostMapping("/new")
    public ResponseEntity<?> insertRequest(@RequestBody Map<String, Object> record) {
        int id = requestsService.insertFromMap(record);
        return ResponseEntity.ok(Map.of("request_id", id));
    }

}
