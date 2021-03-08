package model;

import lombok.Data;

@Data
public class Book {

    private String title;
    private String price;
    private String isbn;

    public Book(String title, String price, String isbn) {
        this.title = title;
        this.price = price;
        this.isbn = isbn;
    }
}
