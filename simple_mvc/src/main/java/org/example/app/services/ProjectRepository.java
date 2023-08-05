package org.example.app.services;

import org.example.app.exceptions.BookShelfRegExSyntaxException;
import org.example.web.dto.RegEx;

import java.util.List;

public interface ProjectRepository<T> {
    List<T> retreiveAll();

    void store(T book);

    boolean removeItemById(Integer bookIdToRemove);

    void removeItemsByRegEx(RegEx regEx) throws BookShelfRegExSyntaxException;
}
