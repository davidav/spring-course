package org.example.app.services;

import java.util.List;

public interface ProjectRepository<Book> {


    List<Book> retreiveAll();

    void store(Book book);

    boolean deleteById(Integer id);
}
