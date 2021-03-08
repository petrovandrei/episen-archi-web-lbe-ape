import fr.upec.episen.proto.BookGrpc;
import fr.upec.episen.proto.BookQuantityReply;
import fr.upec.episen.proto.BookQuantityRequest;
import io.grpc.stub.StreamObserver;



import javax.inject.Singleton;

@Singleton
public class BookGrpcService extends BookGrpc.BookImplBase{
    @Override
    public void getBookQuantity(BookQuantityRequest request, StreamObserver<BookQuantityReply> responseObserver) {
        String isbn = request.getIsbn();
        String quantity = "";

        switch (isbn){
            case "978-1-933624-46-4":
                quantity = "5";
                break;
            case "978-1-933624-44-0":
                quantity = "6";
                break;
            case "978-1-933624-95-2":
                quantity = "17";
                break;
            default:
                quantity = "unkown";
                break;
        }

        responseObserver.onNext(BookQuantityReply
                .newBuilder()
                .setQuantity(quantity)
                .build()
        );

        responseObserver.onCompleted();
    }

}
