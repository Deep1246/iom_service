package com.example.demo.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class IomRequestRepository {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public IomRequestRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Map<String, Object>> findAll(String requestId) {
        String query = "SELECT * FROM requests where request_id = "+requestId;
        var data = jdbcTemplate.queryForList(query);
        return data;
    }

}
