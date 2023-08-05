package org.example.app.exceptions;

public class BookShelfRegExSyntaxException extends Exception {
    private final String message;

    public BookShelfRegExSyntaxException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
