package org.example.app.services;

import org.apache.log4j.Logger;
import org.example.web.dto.Book;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Repository
public class BookRepository implements ProjectRepository<Book> {

    Logger logger = Logger.getLogger(BookRepository.class);
    private final List<Book> repo = new ArrayList<>();

    @Override
    public List<Book> retreiveAll() {
        return new ArrayList<>(repo);
    }

    @Override
    public void store(@NotNull Book book) {
        book.setId(book.hashCode());
        logger.info("store new book: " + book);
        repo.add(book);
    }

    @Override
    public boolean deleteById(Integer id) {
        for (Book book : retreiveAll()) {
            if (book.getId().equals(id)) {
                logger.info("remove book completed: " + book);
                return repo.remove(book);
            }
        }
        return false;
    }

    @Override
    public boolean deleteByRegex(String regex) {
        if (regex.isEmpty()) return false;
        Pattern pattern = Pattern.compile(regex);
        List<Book> listToDelete = new ArrayList<>();

            for (Book book : repo) {
                if (book.getAuthor() != null) {
                    Matcher authorMatcher = pattern.matcher(book.getAuthor());
                    if (authorMatcher.matches()) {
                        logger.info("remove by author book: " + book);
                        listToDelete.add(book);
                    }
                }
                if (book.getTitle() != null) {
                    Matcher titleMatcher = pattern.matcher(book.getTitle());
                    if (titleMatcher.matches()) {
                        logger.info("remove by title book: " + book);
                        listToDelete.add(book);
                    }
                }
                if (book.getSize() != null) {
                    Matcher sizeMatcher = pattern.matcher(Integer.toString(book.getSize()));
                    if (sizeMatcher.matches()) {
                        logger.info("remove by size book: " + book);
                        listToDelete.add(book);
                    }
                }

            }


        if (!listToDelete.isEmpty()) {
            repo.removeAll(listToDelete);
            return true;
        }
        return false;
    }

}
