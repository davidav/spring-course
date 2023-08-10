package com.example.book_shop.repositories;

import com.example.book_shop.models.Author;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Repository
public class AuthorRepositoryImpl implements AuthorRepository {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public AuthorRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Map<String,List<Author>> findAll() {
        List<Author> authors = jdbcTemplate.query("SELECT * FROM authors", (ResultSet rs, int rowNum) ->
                Author.builder()
                        .id(rs.getInt("id"))
                        .name(rs.getString("name"))
                        .surname(rs.getString("surname"))
                        .build());
        return authors.stream().collect(Collectors.groupingBy((Author a) -> a.getSurname().substring(0,1)));
    }
}

