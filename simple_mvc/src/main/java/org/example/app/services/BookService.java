package org.example.app.services;

import org.apache.log4j.Logger;
import org.example.web.dto.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    Logger logger = Logger.getLogger(BookService.class);
    private final ProjectRepository<Book> bookRepository;

    @Autowired
    public BookService(ProjectRepository<Book> bookRepository) {
        this.bookRepository = bookRepository;
    }

    public List<Book> getAllBooks() {
        return bookRepository.retreiveAll();
    }

    public boolean save(Book book) {
        if (book.getAuthor().isBlank() && book.getTitle().isBlank() && (book.getSize() == null)) {
            return false;
        }
        bookRepository.store(book);
        return true;
    }

    public boolean deleteById(Integer id) {
        return bookRepository.deleteById(id);
    }

    public boolean deleteByRegex(String regex) {
        boolean result = false;
        try {
        result = bookRepository.deleteByRegex(regex);
        } catch (Exception e) {
            logger.info(e.getMessage());
        }
        return result;
    }
}
