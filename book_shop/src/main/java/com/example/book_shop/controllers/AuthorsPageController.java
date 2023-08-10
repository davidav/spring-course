package com.example.book_shop.controllers;

import com.example.book_shop.servises.author.AuthorService;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AuthorsPageController {

    private final AuthorService authorService;

    @Autowired
    public AuthorsPageController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @GetMapping("/authors")
    public String genrePage(@NotNull Model model) {
        model.addAttribute("authors", authorService.getAll());
        return "/authors/index";
    }

}
