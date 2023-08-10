package com.example.book_shop.servises.author;

import com.example.book_shop.models.Author;

import java.util.List;
import java.util.Map;

public interface AuthorService {
    Map<String,List<Author>> getAll();
}
