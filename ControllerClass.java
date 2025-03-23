import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Date;

//Note: the view class should handle all input and output (print, scanner

/**
 * Write a description of class ControllerClass here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class ControllerClass
{
    //arrayLists
    private ArrayList<Contract> contractList;
    private ArrayList<Product> productList;
    private ArrayList<Account> accountList;

    //constructor
public ControllerClass()
{
    contractList = new ArrayList<Contract>();
    productList = new ArrayList<Product>();
    accountList = new ArrayList<Account>();
}
    
    public void adminView() {
        System.out.print("\f");
        boolean exitTheMatrix = false;
        Scanner userInput = new Scanner(System.in); 
        
         while (exitTheMatrix == false) {
        System.out.print("Please, select what you want to do: \n\n1. Create test products \n2. Add new product \n3. Change product details \n4. Delete product \n5. Show catalogue \n6. Show stock report \n7. Exit. \n\nYou choice:");
 
            switch (userInput.nextInt()) {
            case 1:
            createTestProducts();
            break;
            case 2:
            addProduct();
            break;
            case 3:
            changeProductDetails();
            break;
            case 4:
            deleteProduct();
            break;
            case 5:
            showCatalogue();
            break;
            case 6:
            showStockReport();
            break;
            case 7:
            exitTheMatrix = true;
            break;
            default:
            System.out.println("Invalid choice. Please enter a number between 1 and 7.");
            break;
        }
    }
}

public void customerView() {
    Scanner userInput = new Scanner(System.in);
    boolean exitTheMatrix = false;
    int sessionIndex;
    System.out.print("\f");
    
     System.out.print("Please, create an account or login: \n1.Create an account 2.Login. \n\nYour choice: ");
     
     if (userInput.nextInt() == 1) {
         sessionIndex = getAccountIndex(createAccount());
     } else if (userInput.nextInt() == 2) {
         System.out.print("Please, insert account ID: "); 
         
         String userInputString= userInput.nextLine();
         if (getAccountIndex(userInputString) > -1) {
             sessionIndex = getAccountIndex(userInputString);
         } else {
             return;
         }
         
     } else {
         return;
     }
     
         while (exitTheMatrix == false) {
        System.out.print("\n\nPlease, select what you want to do: \n\n1. Create a new contract \n2. View account details \n3. Exit. \n\nYou choice:");
 
            switch (userInput.nextInt()) {
            case 1:
            createContract(sessionIndex);
            break;
            case 2:
            viewAccountDetails(sessionIndex);
            break;
            case 3:
            exitTheMatrix = true;
            break;
            default:
            System.out.println("Invalid choice. Please enter a number between 1 and 3.");
            break;
        }
    }
}
    
    //WORKS. GET INDEX (with overloading)
    private int getProductIndex(String productID) {
    for(int arrayIndex = 0; arrayIndex<productList.size(); arrayIndex++) {
        if(productList.get(arrayIndex).getProductID().equals(productID)) {
        return arrayIndex;
        }
    }
    
    System.out.printf("Product ID %s was not found in our database.", productID);
    return -1;
}

    private int getAccountIndex(String accountID) {
        for(int arrayIndex = 0; arrayIndex<accountList.size(); arrayIndex++) {
            if(accountList.get(arrayIndex).getAccountID().equals(accountID)) {
                return arrayIndex;
            }
        }
    
        System.out.printf("Account ID %s was not found in our database.", accountID);
        return -1;
    }
    
    private void viewAccountDetails(int sessionIndex) {
        System.out.print(accountList.get(sessionIndex).toString());
        
        System.out.print("\n\nContracts associated to this account: ");
        
            for (Contract contractObject : contractList) {
                if (contractObject.getAccountID().equals(accountList.get(sessionIndex).getAccountID())) {
                    System.out.print(contractObject.toString());
        }
    }
}
    
    private String createAccount() {
        Scanner userInput = new Scanner(System.in);
        System.out.print("Please, insert account ID: "); String accountID = userInput.nextLine();
         System.out.print("Please, insert full name: "); String fullName = userInput.nextLine();
         System.out.print("Please, insert user email: "); String userEmail = userInput.nextLine();
         accountList.add(new Account(accountID, fullName, userEmail, new Date()));
         System.out.print("Account creation successfull!");
         
         return accountID;
    }
    
    //WORKS. 
    private void addProduct() {
        Scanner userInput = new Scanner(System.in); 
        System.out.print("\fPlease, insert details for the new product\n\n");
        System.out.print("product ID: "); String productID = userInput.nextLine();
        System.out.print("product Name: "); String productName = userInput.nextLine();
        System.out.print("Product Description: "); String productDescription = userInput.nextLine();
        System.out.print("Product Supplier: "); String productSupplier = userInput.nextLine();
        System.out.print("Product Stock: "); int productStock = userInput.nextInt(); userInput.nextLine(); 
        System.out.print("Product Base Price: "); double productBasePrice = userInput.nextDouble(); userInput.nextLine();
        

        Product productObject = new Product (productID, productName, productDescription, productSupplier, productStock, productBasePrice);
        productList.add(productObject);
    }
    
    //WORKS
    private void createTestProducts() {
        
        Product product1 = new Product("101", "Refrigerator", "Large, stainless steel fridge", "CoolTech", 25, 800);
        productList.add(product1);

        Product product2 = new Product("102", "Washing Machine", "High-efficiency washer", "CleanCorp", 30, 601);
        productList.add(product2);

        Product product3 = new Product("103", "Dishwasher", "Quiet, energy-saving dishwasher", "ShinyClean", 15, 451);
        productList.add(product3);

        Product product4 = new Product("104", "Microwave Oven", "Countertop microwave with sensor cooking", "QuickHeat", 50, 150);
        productList.add(product4);

        Product product5 = new Product("105", "Sofa", "Comfortable three-seater sofa", "CozyLiving", 10, 900);
        productList.add(product5);

        Product product6 = new Product("106", "Dining Table", "Solid wood dining table for six", "WoodCraft", 8, 701);
        productList.add(product6);

        Product product7 = new Product("107", "Bed Frame", "Queen-size platform bed frame", "SleepWell", 20, 550);
        productList.add(product7);

        Product product8 = new Product("108", "Bookshelf", "Tall, wooden bookshelf with adjustable shelves", "ShelfMaster", 35, 201);
        productList.add(product8);

        Product product9 = new Product("109", "Toaster", "2 slice toaster, stainless steel", "ToastTime", 60, 35);
        productList.add(product9);

        Product product10 = new Product("110", "Blender", "High powered blender", "SmoothieKing", 40, 100);
        productList.add(product10);
    }
    
    //WORKS
    private void showCatalogue() {
        System.out.println("Catalogue of all products");
        for (Product productObject : productList) {
            System.out.println(productObject.toString());
            System.out.println();
        }
    }

    //WORKS.
    private void deleteProduct() {
        Scanner userInput = new Scanner(System.in); 
        System.out.print("\nPlease, insert the productID of the product you want to eliminate: "); String productID = userInput.nextLine();
        
        
        if (getProductIndex(productID) != -1) {
            System.out.printf("Are you sure you want Delete product %s? Type y for yes and n for no.\n Y/N: ", productID);
            if (userInput.nextLine().contains("y")) {
                productList.remove(getProductIndex(productID));
                System.out.printf("\nProduct %s was successfully removed.", productID);
                return;
            } else {
                return;
            }
        }
    }
    
    //WORKS. NEED TO VALIDATE FOR EXISTING PRODUCT.
    private void changeProductDetails() {
        Scanner userInput = new Scanner(System.in); 
        int selectedOption;
        
        System.out.println("Please, select the product where you want to change details: ");
        int targetIndex = getProductIndex(userInput.nextLine());
        
        System.out.printf("\nPlease, select the field you want to change (indicate the number): \n\n 1. Product ID: %s \n 2. Product name: %s \n 3. Product description: %s \n 4. Product supplier: %s \n 5. Product Stock: %d \n 6. Product Price: %.2f€ \n\n Your choice:", 
        productList.get(targetIndex).getProductID(), productList.get(targetIndex).getProductName(), productList.get(targetIndex).getProductDescription(), productList.get(targetIndex).getProductSupplier(), productList.get(targetIndex).getProductStock(),
        productList.get(targetIndex).getProductBasePrice(), productList.get(targetIndex).getProductStatus());
        selectedOption = Integer.parseInt(userInput.nextLine().trim());
        
        switch(selectedOption) {
            
            case 1: 
            System.out.print("\n\n New ID: ");
            productList.get(targetIndex).setProductID(userInput.nextLine());
            break;
            
            case 2:
            System.out.print("\n\n New Product Name: ");
            productList.get(targetIndex).setProductName(userInput.nextLine());
            break;
            
            case 3:
            System.out.print("\n\n New Product Description: ");
            productList.get(targetIndex).setProductDescription(userInput.nextLine());
            break;
            
            case 4:
            System.out.print("\n\n New Product Supplier: ");
            productList.get(targetIndex).setProductSupplier(userInput.nextLine());
            break;
            
            case 5:
            System.out.print("\n\n New Product Stock: ");
            productList.get(targetIndex).setProductStock(Integer.parseInt(userInput.nextLine()));
            break;
            
            case 6:
            System.out.print("\n\nNew Product Base Price: ");
            productList.get(targetIndex).setProductBasePrice(Double.parseDouble(userInput.nextLine()));
            break;
            
        }
        
        System.out.println("\n\nProduct details have been succesfully changed.");
    }
    
    //WORKS
    private void showStockReport() {
        System.out.println("\fCurrent stock levels for all products (Name/ID: Stock):\n");
        for (Product productObject : productList) {
            System.out.println(productObject.getProductName() + "/" + productObject.getProductID() + ": " + productObject.getProductStock());
        }
    }
    
    private void createContract(int sessionIndex) {
        Scanner userInput = new Scanner(System.in); 
        System.out.print("\fPlease, select type of contract you would like to create (indicate number): \n\n 1.Purchase 2.Lease 3.Rent-to-own: ");
        
        switch (userInput.nextInt()) {
            case 1:
            createPurchase(sessionIndex);
            break;
            case 2:
            //
            break;
            case 3:
            //
            break;
            default:
            System.out.println("Please, select a valid option.");
            break;
        }
        

    }
    
    private void createPurchase(int sessionIndex) {
        //.clear to empty arrayList
        Scanner userInput = new Scanner(System.in); 
        ArrayList<Product> shoppingCart = new ArrayList<>();
        
        System.out.print("\n\nPlease, insert the details of the new purchase: \n");
        System.out.print("ContractID: "); String contractID = userInput.nextLine();
        System.out.print("Purchase date (YY/mm/dd): "); String[] dateInputted = userInput.nextLine().split("/");
        showCatalogue();
        System.out.print("\n\nPlease, select the products you want to include in this purchase (Write product IDs, separated by commas): ");
        
        String[] shoppingCartReferences = userInput.nextLine().split(",");
        
        for (String reference : shoppingCartReferences) {
            Product selectedProduct = productList.get(getProductIndex(reference));
            shoppingCart.add(selectedProduct);
        }
        
        contractList.add(new Purchase(contractID, new Date(Integer.parseInt(dateInputted[0])+100, Integer.parseInt(dateInputted[1])-1, Integer.parseInt(dateInputted[2])), shoppingCart, 12, accountList.get(sessionIndex)));
        
        //foreach contract, check if subtype purchase, cast contract to purchase, and use getter
        for(Contract contract : contractList) {
            if(contract instanceof Purchase) {
            System.out.println(((Purchase)contract).toString());
            }
        }
    }
}

//SOURCES
//https://www.youtube.com/watch?v=H62Jfv1DJlU
//https://www.youtube.com/watch?v=QvHBHuuddYk
//https://www.youtube.com/watch?v=HvPlEJ3LHgE&list=WL&index=3
//https://www.youtube.com/watch?v=Qb_NUn0TSAU&list=WL&index=1
//https://www.youtube.com/watch?v=jhDUxynEQRI&list=WL&index=2 
