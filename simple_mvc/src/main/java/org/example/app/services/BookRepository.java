package org.example.app.services;

import org.apache.log4j.Logger;
import org.example.app.exceptions.BookShelfLoginException;
import org.example.app.exceptions.BookShelfRegExSyntaxException;
import org.example.web.dto.Book;
import org.example.web.dto.RegEx;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.validation.Valid;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

@Repository
public class BookRepository implements ProjectRepository<Book>, ApplicationContextAware {

    private final Logger logger = Logger.getLogger(BookRepository.class);

    private ApplicationContext context;

    private final NamedParameterJdbcTemplate jdbcTemplate;

    @Autowired
    public BookRepository(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Book> retreiveAll() {
        List<Book> books = jdbcTemplate.query("SELECT * FROM books", (ResultSet rs, int rowNum) -> {
            Book book = new Book();
            book.setId(rs.getInt("id"));
            book.setAuthor(rs.getString("author"));
            book.setTitle(rs.getString("title"));
            book.setSize(rs.getInt("size"));
            return book;
        });
        return new ArrayList<>(books);
    }

    @Override
    public void store(Book book) {
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue("author", book.getAuthor());
        parameterSource.addValue("title", book.getTitle());
        parameterSource.addValue("size", book.getSize());
        jdbcTemplate.update("INSERT INTO books(author,title,size) VALUES(:author, :title, :size)", parameterSource);
        logger.info("store new book: " + book);
    }

    @Override
    public boolean removeItemById(Integer bookIdToRemove) {
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue("id", bookIdToRemove);
        jdbcTemplate.update("DELETE FROM books WHERE id = :id", parameterSource);
        logger.info("remove book completed");
        return true;
    }

    @Override
    public void removeItemsByRegEx(@Valid RegEx regEx) throws BookShelfRegExSyntaxException {
        Pattern pattern;
        try {
            pattern = Pattern.compile(regEx.getExpRegEx());
        } catch (PatternSyntaxException e) {
            throw new BookShelfRegExSyntaxException("Invalid regular expression");
        }

        List<Integer> idBooksToDelete = new ArrayList<>();
        for (Book book : retreiveAll()) {
            Matcher authorMatcher = pattern.matcher(book.getAuthor());
            if (authorMatcher.matches()) {
                idBooksToDelete.add(book.getId());
            }
            Matcher titleMatcher = pattern.matcher(book.getTitle());
            if (titleMatcher.matches()) {
                idBooksToDelete.add(book.getId());
            }
            Matcher sizeMatcher = pattern.matcher(Integer.toString(book.getSize()));
            if (sizeMatcher.matches()) {
                idBooksToDelete.add(book.getId());
            }
        }
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue("booksToDelete", idBooksToDelete);
        jdbcTemplate.update("DELETE FROM books WHERE id in (:booksToDelete)", parameterSource);
        logger.info("remove " + idBooksToDelete.size() + " books");
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.context = applicationContext;
    }

    public void defaultInit() {
        logger.info("default INIT in book repo bean");
    }

    public void defaultDestroy() {
        logger.info("default DESTROY in book repo bean");
    }



}
