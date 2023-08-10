package com.example.book_shop.repositories;

import com.example.book_shop.models.Author;

import java.util.List;
import java.util.Map;

public interface AuthorRepository {
    Map<String,List<Author>> findAll();
}
