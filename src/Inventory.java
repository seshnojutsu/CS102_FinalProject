

import java.util.ArrayList;
import java.util.List;
//The inventory is a List of product that get updated after every sale
public class Inventory implements InventoryInterface {
    private List<Product> productList;

    public Inventory() {
        this.productList = new ArrayList<>();
    }

    @Override
    public void addProduct(Product product) {
        productList.add(product);
    }

    @Override
    public void removeProduct(Product product) {
        productList.remove(product);
    }

    @Override
    public List<Product> getProductList() {
        return productList;
    }

   @Override
    public Product findProductByName(Inventory inventory, String name) {
        for (Product product : inventory.getProductList()) {
            if (product.getName().equalsIgnoreCase(name)) {
                return product;
            }
        }
        return null;
    }

}