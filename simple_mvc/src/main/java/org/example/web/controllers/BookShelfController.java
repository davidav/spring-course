package org.example.web.controllers;

import org.apache.log4j.Logger;
import org.example.app.services.BookService;
import org.example.web.dto.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@RequestMapping(value = "/books")
public class BookShelfController {

    Logger logger = Logger.getLogger(BookShelfController.class);
    private final BookService bookService;

    @Autowired
    public BookShelfController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/shelf")
    public String books(Model model) {
        logger.info("got book shelf");
        model.addAttribute("book", new Book());
        model.addAttribute("bookList", bookService.getAllBooks());
        return "book_shelf";
    }

    @PostMapping("/save")
    public String save(Book book, Model model) {
        if (bookService.save(book)) {
            logger.info("current repository size: " + bookService.getAllBooks().size());
            return "redirect:/books/shelf";
        }
        model.addAttribute("book", new Book());
        model.addAttribute("bookList", bookService.getAllBooks());
        model.addAttribute("savedError", "fill in at least one field");
        return "book_shelf";


    }

    @PostMapping("/removeById")
    public String delete(@RequestParam(value = "id", required = false) Integer id, Model model) {
        model.addAttribute("book", new Book());
        model.addAttribute("bookList", bookService.getAllBooks());
        if (bookService.deleteById(id)) {
            return "redirect:/books/shelf";
        } else {
            model.addAttribute("deleteError", "not in database");
            return "book_shelf";
        }
    }

    @PostMapping("/removeByRegex")
    public String delete(@RequestParam(value = "queryRegex") String regex, Model model) {
        if (bookService.deleteByRegex(regex)) {
            return "redirect:/books/shelf";
        } else {
            model.addAttribute("book", new Book());
            model.addAttribute("bookList", bookService.getAllBooks());
            model.addAttribute("regexError", "no matching condition");
            return "book_shelf";
        }
    }






}
