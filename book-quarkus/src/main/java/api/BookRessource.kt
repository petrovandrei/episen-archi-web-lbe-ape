package api

import fr.upec.episen.proto.BookGrpc
import fr.upec.episen.proto.BookQuantityRequest
import fr.upec.episen.proto.RemoveBookQuantityRequest
import io.quarkus.grpc.runtime.annotations.GrpcService
import model.Book
import java.util.*
import javax.inject.Inject
import javax.ws.rs.*
import javax.ws.rs.core.MediaType

@Path("/books")
class BookRessource {
    @Inject
    @GrpcService("quantity")
    var bookGrpcService: BookGrpc.BookBlockingStub? = null

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    fun getBooks(): List<Book> {
        val books: MutableList<Book> = ArrayList()


        // book 1
        val book1 = Book("Baby Elephant Goes for a Swim", "11", "978-1-933624-46-4", "")
        val isbn1: String = book1.isbn
        book1.quantity =(bookGrpcService!!.getBookQuantity(BookQuantityRequest.newBuilder()
                .setIsbn(isbn1)
                .build())
                .quantity
        )


        // book2
        val book2 = Book("Baby Elephant Runs Away", "15", "978-1-933624-44-0", "")
        val isbn2: String = book2.isbn
        book2.quantity = (bookGrpcService!!.getBookQuantity(BookQuantityRequest.newBuilder()
                .setIsbn(isbn2)
                .build())
                .quantity
        )

        // book 3
        val book3 = Book("Bats in Danny’s House", "19", "978-1-933624-95-2", "")
        val isbn3: String = book3.isbn
        book3.quantity =(bookGrpcService!!.getBookQuantity(BookQuantityRequest.newBuilder()
                .setIsbn(isbn3)
                .build())
                .quantity
        )

        // add books to list
        books.add(book1)
        books.add(book2)
        books.add(book3)
        return books
    }

    @POST
    @Path("/{isbn}/buy")
    @Consumes(MediaType.APPLICATION_JSON)
    fun buyBook(@PathParam("isbn") isbn: String?, quantity: String?): Book {
        val booksBuy: MutableList<Book> = ArrayList()

        // book 1
        val book1 = Book("Baby Elephant Goes for a Swim", "11", "978-1-933624-46-4", "")


        // book2
        val book2 = Book("Baby Elephant Runs Away", "15", "978-1-933624-44-0", "")

        // book 3
        val book3 = Book("Bats in Danny’s House", "19", "978-1-933624-95-2", "")

        // add books to list
        booksBuy.add(book1)
        booksBuy.add(book2)
        booksBuy.add(book3)
        for (book in booksBuy) {
            if (book.isbn.equals(isbn)) {
                book.quantity =(bookGrpcService!!.removeBookQuantity(RemoveBookQuantityRequest.newBuilder()
                        .setQuantity(quantity)
                        .setIsbn(isbn)
                        .build())
                        .quantity)
            }
        }
        return findByIsbn(isbn, booksBuy).get()
    }

    fun findByIsbn(isbn: String?, books: List<Book>): Optional<Book> {
        return books.stream().filter { book: Book -> book.isbn.equals(isbn) }.findFirst()
    }
}
