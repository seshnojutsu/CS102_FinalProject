
import java.util.*;
// Sales represent transactions and return an invoice
public class Sale extends Cart implements Discountable {
    private double totalPrice;
    private double discountAmount;
    private String discountCode;

    public Sale() {
        super(new ArrayList<>(), "");
        totalPrice = 0;
    }

    public String getDiscountCode() {
        return discountCode;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        if (!(totalPrice == 0)) {
            this.totalPrice = totalPrice;
    }
}
   
    public void checkout(Inventory inventory, ArrayList<Customer> customers) {
        if (customers.isEmpty()) {
            return;
        }
        // Process the first customer in the list
        Cart currentCustomer = (Cart)customers.get(0);
        setName(currentCustomer.getName());
        setCart(currentCustomer.getCart());
        Scanner sc = new Scanner(System.in);
        //Getting Customer name.
        String customerName = sc.nextLine();
        setName(customerName);
        System.out.println("-------------------------------------------------------------------");
        // Calculate the total price before discount
        double totalPriceBeforeDiscount = 0;
        for (Product cartProduct : getCart()) {
            double productPrice = cartProduct.getPrice() * cartProduct.getQuantityWanted();
            totalPriceBeforeDiscount += productPrice;
        }
        // Prompt the user to enter the discount code
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter discount code (or leave blank for no discount): ");
        discountCode = scanner.nextLine();
        // Apply the discount
        applyDiscount(totalPriceBeforeDiscount);
        // Calculate the total price after discount
        double totalPriceAfterDiscount = totalPriceBeforeDiscount - discountAmount;
        // Print the total price before and after discount, and the discount amount
        System.out.println("-------------------------------------------------------------------");
        System.out.println("Invoice for " + getName() + ":");
        for (Product cartProduct : getCart()) {
            double productPrice = cartProduct.getPrice() * cartProduct.getQuantityWanted();
            System.out.println(cartProduct.getName() + " - Price: $" + productPrice +
                    " - Quantity bought: " + cartProduct.getQuantityWanted());           
        }
        System.out.println("Total Price before discount: " + totalPriceBeforeDiscount + "$");
        if (totalPriceBeforeDiscount - totalPriceAfterDiscount > 0) {
            System.out.println("Total Price after discount: " + totalPriceAfterDiscount + "$");
            System.out.println("You saved: " + discountAmount + "$ on this purchase.");
        } else {
            System.out.println("No discount applied.");
            System.out.println("Total Price: " + totalPriceAfterDiscount + "$");
        }
        getCart().clear();
        customers.remove(0);
        // Recursive call for the next customer
        checkout(inventory, customers);
    }

    public void applyDiscount(double tp) {
        if (getDiscountCode().compareTo("winterSale") == 0 || getDiscountCode().compareTo("15Off") == 0) {

            discountAmount = tp * 0.15; 
            tp -= discountAmount;
            setTotalPrice(tp);
        } else {
            discountAmount = 0;
        }
    }
}