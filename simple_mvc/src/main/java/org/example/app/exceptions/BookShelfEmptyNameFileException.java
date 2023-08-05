package org.example.app.exceptions;

public class BookShelfEmptyNameFileException extends Exception{

    private final String message;

    public BookShelfEmptyNameFileException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }

}
