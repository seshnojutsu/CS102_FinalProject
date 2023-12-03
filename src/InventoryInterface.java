import java.util.List;

public interface InventoryInterface {
    void addProduct(Product product);

    void removeProduct(Product product);

    List<Product> getProductList();

    Product findProductByName(Inventory inventory, String name);
}