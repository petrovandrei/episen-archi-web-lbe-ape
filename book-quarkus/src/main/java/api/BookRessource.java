package api;

import fr.upec.episen.proto.BookGrpc;
import fr.upec.episen.proto.BookQuantityRequest;
import io.quarkus.grpc.runtime.annotations.GrpcService;
import model.Book;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.*;

@Path("/books")
public class BookRessource {

    @Inject
    @GrpcService("quantity")
    BookGrpc.BookBlockingStub bookGrpcService;

    List<Book> books = new ArrayList<>();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Book> getBooks() {

        // book 1
        Book book1 = new Book("Baby Elephant Goes for a Swim", "11", "978-1-933624-46-4", "");
        String isbn1 = book1.getIsbn();
        book1.setQuantity(bookGrpcService.
                getBookQuantity(BookQuantityRequest.newBuilder()
                        .setIsbn(isbn1)
                        .build())
                .getQuantity()
        );


        // book2
        Book book2 = new Book("Baby Elephant Runs Away", "15", "978-1-933624-44-0", "");
        String isbn2 = book2.getIsbn();
        book2.setQuantity(bookGrpcService.
                getBookQuantity(BookQuantityRequest.newBuilder()
                        .setIsbn(isbn2)
                        .build())
                .getQuantity()
        );

        // book 3
        Book book3 = new Book("Bats in Danny’s House", "19", "978-1-933624-95-2", "");
        String isbn3 = book3.getIsbn();
        book3.setQuantity(bookGrpcService.
                getBookQuantity(BookQuantityRequest.newBuilder()
                        .setIsbn(isbn3)
                        .build())
                .getQuantity()
        );

        // add books to list
        books.add(book1);
        books.add(book2);
        books.add(book3);

        return books;
    }
}
