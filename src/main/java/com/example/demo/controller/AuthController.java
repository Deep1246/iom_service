package com.example.demo.controller;

import com.example.demo.dto.ApiResponse;
import com.example.demo.dto.UserResponseDto;
import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/index")
    public ResponseEntity<String> index(){
        return ResponseEntity.ok("API working fine...");
    }

    @PostMapping("login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> reqBody) {
        String email = reqBody.get("username");
        String password = reqBody.get("password");

        if (email == null || password == null)
            return ResponseEntity.badRequest().body("Invalid email or password!");

        Optional<User> userOpt = userRepository.findByEmailAndPasswordHash(email, password);

        if (userOpt.isPresent()) {

            User user = userOpt.get();

            UserResponseDto userResponse = UserResponseDto.builder()
                    .id(user.getId())
                    .email(user.getEmail())
                    .userName(user.getName())
                    .role("Associate")
                    .build();

            ApiResponse success = ApiResponse.builder()
                    .message("success")
                    .status(HttpStatus.OK.value())
                    .data(userResponse).build();
            return new ResponseEntity<Object>(success, HttpStatus.OK);
        }


        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(ApiResponse.builder()
                .status(HttpStatus.UNAUTHORIZED.value()).message("Invalid email or password!").build()
        );
    }
}
