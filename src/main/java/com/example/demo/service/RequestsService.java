package com.example.demo.service;

import com.example.demo.entity.Requests;
import com.example.demo.repository.RequestsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RequestsService {

    @Autowired
    public RequestsRepository requestsRepository;

    public Optional<Requests> findRequestById(int id) {
       return requestsRepository.findById(id);
    }
}
