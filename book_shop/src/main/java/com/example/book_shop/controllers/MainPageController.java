package com.example.book_shop.controllers;


import com.example.book_shop.servises.book.BookServiceImpl;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/bookshop")
public class MainPageController {
    private final BookServiceImpl bookService;

    @Autowired
    public MainPageController(BookServiceImpl bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/main")
    public String mainPage(@NotNull Model model) {
        model.addAttribute("books", bookService.getBooks());
        return "index";
    }

}
