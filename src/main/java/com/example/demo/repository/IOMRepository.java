package com.example.demo.repository;

import com.example.demo.entity.IOMRequestMaster;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IOMRepository extends JpaRepository<IOMRequestMaster,Long> {
}
