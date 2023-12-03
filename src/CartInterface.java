
import java.util.List;


public interface CartInterface {
    void addToCart(Product product, int quantity);
    void displayCart();
    List<Product> getCart();
}
