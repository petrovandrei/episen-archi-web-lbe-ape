package model;

import lombok.Data;

@Data
public class Book {

    private String title;
    private String price;
    private String isbn;
    private String quantity;

    public Book(String title, String price, String isbn, String quantity) {
        this.title = title;
        this.price = price;
        this.isbn = isbn;
        this.quantity = quantity;
    }
}
