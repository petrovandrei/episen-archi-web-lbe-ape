import fr.upec.episen.proto.*;
import io.grpc.stub.StreamObserver;



import javax.inject.Singleton;
import java.util.ArrayList;
import java.util.List;

@Singleton
public class BookGrpcService extends BookGrpc.BookImplBase{



    @Override
    public void getBookQuantity(BookQuantityRequest request, StreamObserver<BookQuantityReply> responseObserver) {
        List<BookQuantity> books = new ArrayList<>();
        //books
        BookQuantity book1 = new BookQuantity("978-1-933624-46-4","15");
        BookQuantity book2 = new BookQuantity("978-1-933624-44-0","16");
        BookQuantity book3 = new BookQuantity("978-1-933624-95-2","17");
        books.add(book1);
        books.add(book2);
        books.add(book3);


        String isbn = request.getIsbn();
        String  quantity = "";

        switch (isbn){
            case "978-1-933624-46-4":
                quantity = books.get(0).quantity;
                break;
            case "978-1-933624-44-0":
                quantity = books.get(1).quantity;
                break;
            case "978-1-933624-95-2":
                quantity = books.get(2).quantity;
                break;
            default:
                quantity = "unknown";
                break;
        }

        responseObserver.onNext(BookQuantityReply
                .newBuilder()
                .setQuantity(quantity)
                .build()
        );

        responseObserver.onCompleted();
    }

    @Override
    public void removeBookQuantity(RemoveBookQuantityRequest request, StreamObserver<RemoveBookQuantityReply> responseObserver ){
        List<BookQuantity> booksBuy = new ArrayList<>();
        BookQuantity book1 = new BookQuantity("978-1-933624-46-4","15");
        BookQuantity book2 = new BookQuantity("978-1-933624-44-0","10");
        BookQuantity book3 = new BookQuantity("978-1-933624-95-2","17");
        booksBuy.add(book1);
        booksBuy.add(book2);
        booksBuy.add(book3);
        String isbn = request.getIsbn();
        String requestQuantity = request.getQuantity();
        String quantity = "";

        for (BookQuantity bookQuantity : booksBuy) {
            if (bookQuantity.getIsbn().equals(isbn)) {
                quantity = String.valueOf((Integer.parseInt(bookQuantity.getQuantity()) - Integer.parseInt(requestQuantity)));
                bookQuantity.setQuantity(quantity);
            }
        }

        responseObserver.onNext(RemoveBookQuantityReply
                        .newBuilder()
                        .setQuantity(quantity)
                        .build());
        responseObserver.onCompleted();
    }
}
