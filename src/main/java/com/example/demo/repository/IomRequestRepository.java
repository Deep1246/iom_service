package com.example.demo.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.OptionalInt;

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

//    public OptionalInt insertRequest(String title, String category, String subCategory, String description) {
    public OptionalInt insertRequest(Map<String,String> data) {
        String sql = "INSERT INTO requests (title, category, sub_category, description) VALUES (?, ?, ?, ?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();

        if((Objects.isNull(data.get("title")) && data.get("title").isEmpty()) || (Objects.isNull(data.get("category")) && data.get("category").isEmpty()) ||
                (Objects.isNull(data.get("subCategory")) && data.get("subCategory").isEmpty()) || (Objects.nonNull(data.get("description")) && data.get("description").isEmpty()) ){
            return OptionalInt.empty();
        }

        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, data.get("title"));
            ps.setString(2, data.get("category"));
            ps.setString(3, data.get("subCategory"));
            ps.setString(4, data.get("description"));
            return ps;
        }, keyHolder);

        Map<String, Object> keys = keyHolder.getKeys();
        if (keys != null && keys.containsKey("request_id")) {
            return OptionalInt.of(((Number) keys.get("request_id")).intValue());
        } else {
            return OptionalInt.empty();
        }
    }

}
