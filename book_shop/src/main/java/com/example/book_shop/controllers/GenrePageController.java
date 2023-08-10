package com.example.book_shop.controllers;

import com.example.book_shop.servises.book.BookServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
//@RequestMapping("/bookshop")
public class GenrePageController {

    private final BookServiceImpl bookService;

    @Autowired
    public GenrePageController(BookServiceImpl bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/genres")
    public String genrePage() {
        return "/genres/index";
    }

}
