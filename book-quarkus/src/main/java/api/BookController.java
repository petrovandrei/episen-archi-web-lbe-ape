package api;

import model.Book;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.*;

@Path("/books")
public class BookController {
    List<Book> books = new ArrayList<>();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Book> getBooks() {

       Book book1 = new Book("Baby Elephant Goes for a Swim","11","978-1-933624-46-4");
       Book book2 = new Book("Baby Elephant Runs Away","15","978-1-933624-44-0");
       Book book3 = new Book("Bats in Danny’s House","19","978-1-933624-95-2");
       books.add(book1);
       books.add(book2);
       books.add(book3);
       return books;
    }
}
