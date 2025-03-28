import java.util.Scanner;

/**
 * Write a description of class customerView here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class viewClass
{
    Scanner userInput = new Scanner(System.in);
    
    public viewClass()
    {
        
    }
    
    //user input with validation. Only input. 
    public int getUserChoice() {
        int userInputNumber = Integer.parseInt(userInput.nextLine().trim());
            try {
            return userInputNumber;
        } catch (Exception e) {
            return -1; 
        }
    }
    
    //for searching and finding. gets input and output. 
    public void getOutputError(String ID) {
        System.out.printf("\nID %s was not found in our database.", ID);
    }
    
    //get output and then input. Returns value of input. 
    public String getUserString(String detailType) {
        switch (detailType) {
            case "name":
                System.out.print("Product Name: ");
                return userInput.nextLine();
            case "description":
                System.out.print("Product Description: ");
                return userInput.nextLine();
            case "supplier":
                System.out.print("Product Supplier: ");
                return userInput.nextLine();
            case "productDetailsWithID":
                System.out.print("\nPlease, select the product where you want to view details (type product ID): ");
                return userInput.nextLine();
            case "changeDetailsWithID":
                System.out.print("\nPlease, select the product where you want to change details (type product ID): ");
                return userInput.nextLine();
            case "newProductID":
                System.out.print("\n\n New ID: ");
                return userInput.nextLine();
            case "newProductName":
                System.out.print("\n\n New Name: ");
                return userInput.nextLine();
            case "newProductDescription":
                System.out.print("\n\n New Description: ");
                return userInput.nextLine();
            case "newProductSupplier":
                System.out.print("\n\n New Supplier: ");
                return userInput.nextLine();
            case "deleteProductWithID":
                System.out.print("\nPlease, insert the productID of the product you want to eliminate: ");
                return userInput.nextLine();
            case "productDeletionConfirmation":
                return userInput.nextLine();
            case "loginOrCreate":
                return userInput.nextLine().toLowerCase();
            case "newAccountName":
                System.out.print("Please, insert full name: ");
                return userInput.nextLine();
            case "newAccountEmail":
                System.out.print("Please, insert user email: "); 
                return userInput.nextLine();
            case "login":
                System.out.print("\nPlease, insert account ID: ");
                return userInput.nextLine();
            case "terminateContract":
                System.out.print("\n\nWhat contract would you like to terminate? Please write contractID: \nYour choice: ");
                return userInput.nextLine().trim();
            case "acceptTerms":
                System.out.print("Do you accept these terms? (y/n): ");
                return userInput.nextLine().trim().toLowerCase();
            default:
                return null;
        }
    }
    
    public int getUserInt(String detailType) {
        switch (detailType) {
            case "stock":
                System.out.print("Product Stock: ");
                return Integer.parseInt(userInput.nextLine().trim());
            case "newStock":
                System.out.print("\n\n New Product Stock: ");
                return Integer.parseInt(userInput.nextLine().trim());
            case "contractLength":
                System.out.print("\nEnter contract duration (months): ");
                return Integer.parseInt(userInput.nextLine().trim());
            default:
                return -1;
        }
    }
    
    public double getUserDouble(String detailType) {
        switch (detailType) {
            case "basePrice":
                System.out.print("Product Base Price: ");
                return Double.parseDouble(userInput.nextLine().trim());
            case "newPrice":
                System.out.print("\n\nNew Product Base Price: ");
                return Double.parseDouble(userInput.nextLine().trim());
            default:
                return -1;
        }
    }
    
    //when only output needs to be shown. 
    public void showSimpleMessage(String simpleMessage) {
        System.out.print(simpleMessage);
    }
    
    public void showFormattedMessage(String message, String reference) {
        System.out.printf(message, reference);
    }
    
}
