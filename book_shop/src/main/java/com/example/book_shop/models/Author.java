package com.example.book_shop.models;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Builder
@Getter
@Setter
@ToString
public class Author {
    private Integer id;
    private String name;
    private String surname;
}
