package com.example.book_shop.models;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Builder
@Getter
@Setter
@ToString
public class Book {
    private Integer id;
    private Integer author;
    private String title;
    private String price;
    private String priceOld;

}
