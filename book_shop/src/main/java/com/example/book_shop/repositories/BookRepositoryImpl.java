package com.example.book_shop.repositories;


import com.example.book_shop.models.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

@Repository
public class BookRepositoryImpl implements BookRepository {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public BookRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Book> findAll() {
        List<Book> books = jdbcTemplate.query("SELECT * FROM books", (ResultSet rs, int rowNum) ->
                Book.builder()
                        .id(rs.getInt("id"))
                        .author(rs.getInt("author_id"))
                        .title(rs.getString("title"))
                        .price(rs.getString("price"))
                        .priceOld(rs.getString("price_old"))
                        .build());
        return new ArrayList<>(books);
    }
}
