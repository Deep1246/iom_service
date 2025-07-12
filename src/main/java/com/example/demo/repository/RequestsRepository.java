package com.example.demo.repository;

import com.example.demo.entity.Requests;
import com.example.demo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RequestsRepository extends JpaRepository<Requests, Integer> {
    Optional<Requests> findByRequestId(Integer requestId);
    Optional<Requests> getAllByRequesterId(Integer requestId);
}
