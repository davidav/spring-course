package com.example.book_shop.repositories;

import com.example.book_shop.models.Book;

import java.util.List;

public interface BookRepository {
    List<Book> findAll();

}
