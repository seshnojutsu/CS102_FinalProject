
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;


public class GroceryStoreApp {

    public static void main(String[] args) throws IOException {
        //Loads product from inventory.
        Inventory inventory = loadProducts("ProductList.txt");
        // Takes in amount of customers.
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the number of customers to process: ");
        int numCustomers = getValidQuantity(scanner);
        //Process customers in a recurssion.
        ArrayList<Customer> customers = new ArrayList<>();
        processCustomers(inventory, customers, numCustomers);
        scanner.close();
    }

      private static void processCustomers(Inventory inventory, ArrayList<Customer> customers, int remainingCustomers) {
        if (remainingCustomers <= 0) {
            System.out.println("Processing complete.");
            return;
        }
        Sale customer = new Sale();
        System.out.println("Available products:");
        // Print table header
        System.out.printf("%-20s%-15s%-15s\n", "Product Name", "Price", "Quantity in Stock");
        System.out.println("--------------------------------------------------------");
        for (Product product : inventory.getProductList()) {
            // Print product details
            System.out.printf("%-20s%-15.2f%-15d\n",
                    product.getName(), product.getPrice(), product.getQuantityInStock());
        }
        try (Scanner scanner = new Scanner(System.in)) {
            String userInput;
        do {
            System.out.print("Enter a product name to add to your cart (or type 'checkout' to complete your purchase): ");
            userInput = scanner.nextLine().trim();
            if (!userInput.equalsIgnoreCase("checkout")) {
        // Find the product in the inventory
        Product selectedProduct = findProductByName(inventory, userInput);
        if (selectedProduct != null) {
            // Ask for quantity
            System.out.print("Enter the quantity: ");
            int amount;
            try {
                amount = Integer.parseInt(scanner.nextLine());
                if (amount > 0 && amount <= selectedProduct.getQuantityInStock()) {
                    // Set the quantity wanted
                    selectedProduct.setQuantityWanted(amount);
                    // Add to the cart
                    customer.addToCart(selectedProduct, selectedProduct.getQuantityWanted());
                    selectedProduct.setQuantityInStock(selectedProduct.getQuantityInStock() - selectedProduct.getQuantityWanted()); // Set the quantity in the cart
                } else {
                    System.out.println("Invalid quantity. Please enter a valid quantity.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid quantity. Please enter a valid quantity.");
            }
        } else {
            System.out.println("Product not found. Please enter a valid product name.");
        }
    }
    } 
        while (!userInput.equalsIgnoreCase("checkout"));
    System.out.print("Enter your name: ");
            // Cart checks out
            customers.add(customer);
            customer.checkout(inventory, customers);
            processCustomers(inventory, customers, remainingCustomers - 1);
            
        }
    }
    
    public static Inventory loadProducts(String filename) throws IOException {
        //Create a new inventory Product
        Inventory inventory = new Inventory();
        
        //Loads the products into the inventory.
        try (Scanner scanner = new Scanner(new FileInputStream(new File(filename)))) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] parts = line.split(" ");
                if (parts.length == 3) {
                    String name = parts[0];
                    double price = Double.parseDouble(parts[1]);
                    int quantity = Integer.parseInt(parts[2]);
                    Product product = new Product();
                    product.setName(name);
                    product.setPrice(price);
                    product.setQuantityInStock(quantity);
                    inventory.addProduct(product);
                }
            }
        } catch (IOException e) {
        }
        return inventory;
    }
//Method to find products by name.
    private static Product findProductByName(Inventory inventory, String name) {
        for (Product product : inventory.getProductList()) {
            if (product.getName().equalsIgnoreCase(name)) {
                return product;
            }
        }
        return null;
    }
//Checks the validty of the quantity.
    private static int getValidQuantity(Scanner scanner) {
        int quantity;
        while (true) {
            try {
                quantity = Integer.parseInt(scanner.nextLine());
                if (quantity > 0) {
                    break;
                } else {
                    System.out.println("Please enter a valid positive quantity.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number.");
            }
        }
        return quantity;
    }
}