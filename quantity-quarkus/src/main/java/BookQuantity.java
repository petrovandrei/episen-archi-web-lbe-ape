import lombok.Data;

@Data
public class BookQuantity {
    String isbn;
    String quantity;

    public BookQuantity(String isbn, String quantity) {
        this.isbn = isbn;
        this.quantity = quantity;
    }
}
