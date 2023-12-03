
import java.util.ArrayList;
import java.util.Scanner;

// This class represents the customer, each Customer must have a name that is made up of letters only
public class Customer {
    private String name;

    public Customer(String name) {
        setName(name);
    }

    public String getName() {
        return name;
    }
//Checks if the name is invalid, doesn't take more than 1 name.
   public void setName(String name) {
        for (int i = 0; i < name.length(); i++) {
            if (!Character.isAlphabetic(name.charAt(i)) || name.length() == 0) {
                throw new IllegalArgumentException("Invalid Name, must only have letters.");
            }
        }
        this.name = name;
    }
     
}
