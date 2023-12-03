
import java.util.Scanner;
// Products represent store items they have a name a price and a quantitiy
class Product implements ProductInterface{
    private String name;
    private double price;
    private int quantityInStock;
    private int quantityWanted;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantityInStock() {
        return quantityInStock;
    }

    public void setQuantityInStock(int quantityInStock) {
        this.quantityInStock = quantityInStock;
    }

    

   public void updateStock(int quantity) {
        if (this.quantityInStock >= quantity) {
        this.quantityInStock -= quantity;
    } else {
        throw new IllegalArgumentException("Not enough stock available for " + this.name);
    }
}

    public int getQuantityWanted() {
        return quantityWanted;
    }

    public void setQuantityWanted(int quantityWanted) {
        this.quantityWanted = quantityWanted;
    }
   


    @Override
    public String toString() {
        return name + " - Price: $" + price + " - Quantity in stock: " + quantityInStock;
    }
}