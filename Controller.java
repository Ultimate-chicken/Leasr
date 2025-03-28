import java.util.ArrayList;
import java.util.Scanner;
import java.util.Date;
import java.util.Random;

/** 
 * @author (your name)
 * @version ()
 */
public class Controller
{
    //arrayLists for contracts, products, and accounts. 
    public ArrayList<Contract> contractList;
    public ArrayList<Product> productList;
    public ArrayList<Account> accountList;
    public ArrayList<Product> shoppingCart;
    private viewClass view;
    Scanner userInput = new Scanner(System.in);

    // Constructor. Note that shopping cart array list is initiated in the createContract method. 
    public Controller()
    {
        contractList = new ArrayList<Contract>();
        productList = new ArrayList<Product>();
        accountList = new ArrayList<Account>();
        view = new viewClass();
        createTestProducts();
    }

    /** Admin view. Allows administrators to add/delete product, change/view product details, and show list of products/accounts/leases */
    public void adminView() {
        boolean exitTheMatrix = false;

        while (exitTheMatrix == false) {
            view.showSimpleMessage("\f\nPlease, select what you want to do: \n\n1. Add new product \n2. View product details \n3. Change product details \n4. Delete product \n5. Show catalogue \n6. Show stock report \n7. Exit. \n\nYou choice:");

            //We parse nextLine instead of using nextInt to avoid input errors which happen if we don't put nextLine() after nextInt().
            int userSelection = view.getUserChoice();
            switch (userSelection) {
                case 1:
                    addProduct();
                    break;
                case 2:
                    viewProductDetails();
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
                    view.showSimpleMessage("Invalid choice. Please enter a number between 1 and 7.");
                    break;
            }
        }
    }

    //gets index of product within arrayList, based on product ID.
    private int getProductIndex(String productID) {
        for(int arrayIndex = 0; arrayIndex<productList.size(); arrayIndex++) {
            if(productList.get(arrayIndex).getProductID().equals(productID)) {
                return arrayIndex;
            }
        }

        view.getOutputError(productID);
        return -1;
    }

    private void addProduct() {
        Random random = new Random();
        String productID = Integer.toString(1000 + random.nextInt(9999 - 1000));
        view.showSimpleMessage("\fPlease, insert details for the new product\n\n");
        view.getUserString(detailType);
        String productName = view.getUserString("name");
        System.out.print("product Name: "); String productName = userInput.nextLine();
        System.out.print("Product Description: "); String productDescription = userInput.nextLine();
        System.out.print("Product Supplier: "); String productSupplier = userInput.nextLine();
        System.out.print("Product Stock: "); int productStock = Integer.parseInt(userInput.nextLine().trim());
        System.out.print("Product Base Price: "); double productBasePrice = Double.parseDouble(userInput.nextLine().trim());

        for (Product productObject : productList) {
            if (productObject.getProductName().equals(productName)) {
                System.out.print("\nSorry, this product already exsits!");
                return;
            }
        }

        Product productObject = new Product (productID, productName, productDescription, productSupplier, productStock, productBasePrice);
        productList.add(productObject);
    }

    private void viewProductDetails() {
        System.out.print("\nPlease, select the product where you want to view details (type product ID): ");
        int targetIndex = getProductIndex(userInput.nextLine());
        System.out.print(productList.get(targetIndex).toString());
    }

    private void changeProductDetails() {
        int selectedOption;

        System.out.print("\nPlease, select the product where you want to change details (type product ID): ");
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

    private void deleteProduct() {
        System.out.print("\nPlease, insert the productID of the product you want to eliminate: "); String productID = userInput.nextLine();

        if (getProductIndex(productID) != -1) {
            System.out.printf("\nAre you sure you want Delete product %s? Type y for yes and n for no.\n Y/N: ", productID);
            if (userInput.nextLine().contains("y")) {
                productList.remove(getProductIndex(productID));
                System.out.printf("\nProduct %s was successfully removed.", productID);
                return;
            } else {
                return;
            }
        }
    }

    private void showCatalogue() {
        System.out.println("\nCatalogue of all products");
        for (Product productObject : productList) {
            System.out.println(productObject.toString());
            System.out.println();
        }
    }

    private void showStockReport() {
        System.out.println("\f\nCurrent stock levels for all products (Name/ID: Stock):\n");
        for (Product productObject : productList) {
            System.out.println(productObject.getProductName() + "/" + productObject.getProductID() + ": " + productObject.getProductStock());
        }
    }

    /** Customer view. Allows */
    public void customerView() {
        System.out.print("\f");
        boolean exitTheMatrix = false;

        while (!exitTheMatrix) {
            System.out.print("\nPlease, choose what you want to do: \n"
                + "1. Show all products \n"
                + "2. Create an account \n"
                + "3. Login \n"
                + "4. Exit \n\nYour choice: ");

            int choice = userInput.nextInt();
            userInput.nextLine();

            switch (choice) {
                case 1:
                    showCatalogue();
                    promptLogin();
                    break;
                case 2:
                    createAccount();
                    break;
                case 3:
                    loginToAccount(); 
                    break;
                case 4:
                    exitTheMatrix = true;
                    break;
                default:
                    System.out.println("Invalid choice.");
            }
        }
    }

    private int getAccountIndex(String accountID) {
        for(int arrayIndex = 0; arrayIndex<accountList.size(); arrayIndex++) {
            if(accountList.get(arrayIndex).getAccountID().equals(accountID)) {
                return arrayIndex;
            }
        }

        System.out.printf("Sorry, account %s was not found in our database. ", accountID);
        return -1;
    }

    private void promptLogin() {
        System.out.println("\nIf you want to select products, you must log in or create an account. Type LOGIN or CREATE if you want to proceed.");
        String userSelection = userInput.nextLine().toLowerCase();

        switch(userSelection) {
            case "login":
                loginToAccount();
                break;
            case "create":
                createAccount();
                break;
            default:
                //if none of the two option chosen, exit. 
                return;
        }
    }

    private void createAccount() {
        Random random = new Random();
        String accountID = Integer.toString(1000 + random.nextInt(9999 - 1000));
        System.out.print("Please, insert full name: "); 
        String fullName = userInput.nextLine();
        System.out.print("Please, insert user email: "); 
        String userEmail = userInput.nextLine();

        Account newAccount = new Account(accountID, fullName, userEmail, new Date());
        accountList.add(newAccount);
        System.out.printf("\nAccount creation successful! Your account ID is %s", accountID);

        int sessionIndex = getAccountIndex(accountID);
        handleAccountMenu(sessionIndex);
    }

    //checks if account exists. If login is successfull, it will open the login menu. 
    private void loginToAccount() {
        System.out.print("\nPlease, insert account ID: ");
        int sessionIndex = getAccountIndex(userInput.nextLine());
        if (sessionIndex > -1) {
            System.out.print("\nLogin successful. ");
            handleAccountMenu(sessionIndex);
        }
    }

    private void handleAccountMenu(int sessionIndex) {
        boolean backToMainMenu = false;

        while (!backToMainMenu) {
            System.out.print("\nPlease, select what you want to do: \n"
                + "1. Purchase, lease, or rent-to-own products \n"
                + "2. Terminate an existing contract \n"
                + "3. View account details \n"
                + "4. Exit \n\nYour choice: ");

            int userSelection = Integer.parseInt(userInput.nextLine().trim());

            switch (userSelection) {
                case 1:
                    createContract(sessionIndex);
                    break;
                case 2:
                    terminateContract(sessionIndex);
                    break;
                case 3:
                    viewAccountDetails(sessionIndex);
                    break;
                case 4:
                    backToMainMenu = true;
                default:
                    System.out.println("Invalid choice.");
                    break;
            }
        }
    }

    private void terminateContract(int sessionIndex) {        
        System.out.print("\n\nYour active recurring contracts: ");

        int recurringContractCounter = 0;
        for (Contract contractObject : contractList) {
            if (contractObject instanceof RecurringContract) {
                System.out.print(contractObject.toString());
                recurringContractCounter++;
            }
        }

        if (recurringContractCounter > 0) { 
            System.out.print("\n\nWhat contract would you like to terminate? Please write contractID: \nYour choice: ");
            String userSelection = userInput.nextLine().trim();
            Contract targetObject = contractList.get(getContractIndex(userSelection));

            if (targetObject.getLinkedAccount().equals(accountList.get(sessionIndex).getAccountID())) {
                contractList.remove(targetObject);
                System.out.printf("Contract %s was succesfully terminated. ", userSelection);
            } else {
                System.out.printf("\nContract %s is not linked to this account. Please recheck contract ID. ", userSelection);
            }
        } else {
            System.out.print("\nCurrently, you don't have any active recurring contract that can be terminated.\n\n");
        }
    }

    private int getContractIndex(String contractID) {
        for(int arrayIndex = 0; arrayIndex<contractList.size(); arrayIndex++) {
            if(contractList.get(arrayIndex).getContractID().equals(contractID)) {
                return arrayIndex;
            }
        }

        System.out.printf("Account ID %s was not found in our database.", contractID);
        return -1;
    }   

    private void viewAccountDetails(int sessionIndex) {

        System.out.print(accountList.get(sessionIndex).toString());
        System.out.print("\n\nContracts associated to this account: ");

        int contractCounter = 0;
        for (Contract contractObject : contractList) {
            if (contractObject.getLinkedAccount().equals(accountList.get(sessionIndex).getAccountID())) {
                System.out.print(contractObject.toString());
                contractCounter++;
            }
        }

        if (contractCounter < 1) {
            System.out.print("\nNo recurring contracts currently associated to this account.\n ");
        }

    } 

    private void createContract(int sessionIndex) {
        shoppingCart = new ArrayList<Product>();
        
        showCatalogue();
        System.out.print("\n\nPlease, select the products you want (Write product IDs, separated by commas): ");
        String[] shoppingCartReferences = userInput.nextLine().split(",");
        
        for (String reference : shoppingCartReferences) {
            int productIndex = getProductIndex(reference.trim());
            if (productIndex != -1) {
                shoppingCart.add(productList.get(productIndex));
            } 
        }

        System.out.print("Note that price of products may change depending on contract type. \n");
        System.out.print("\nSelect contract type:\n1. Lease\n2. Rent-to-Own\n3. Purchase. \nYour choice: ");
        int contractType = Integer.parseInt(userInput.nextLine().trim());

        switch (contractType) {
            case 1:
                createLeaseContract(sessionIndex);
                break;
            case 2:
                createRentToOwnContract(sessionIndex);
                break;
            case 3: 
                createPurchaseContract(sessionIndex);
                break;
            default:
                System.out.println("Invalid contract type.");
        }
    }

    private void createLeaseContract(int sessionIndex) {
        System.out.print("\nEnter lease duration (months): ");
        int contractLengthMonths = Integer.parseInt(userInput.nextLine().trim());

        Random random = new Random();
        String contractID = Integer.toString(1000 + random.nextInt(9999 - 1000));
        String linkedAccount = accountList.get(sessionIndex).getAccountID();

        Date contractDate = new Date();
        Lease leaseContract = new Lease(contractID, shoppingCart, linkedAccount, contractLengthMonths, contractDate);

        System.out.printf("Deposit Amount: €%.2f\n", leaseContract.getDepositAmount());
        System.out.printf("Monthly Payment: €%.2f\n", leaseContract.getAdjustedMonthlyCost());
        System.out.printf("Total cost: €%.2f\n", leaseContract.calculateTotalCost());
        
        System.out.print("Do you accept these terms? (y/n): ");
        String acceptance = userInput.nextLine().toLowerCase();

        if (acceptance.equals("y")) {
            contractList.add(leaseContract);
            System.out.println(leaseContract.toString());
        } else {
            System.out.println("\nContract creation cancelled. Returning to main menu.");
            return;
        }
    }

    private void createRentToOwnContract(int sessionIndex) {
        System.out.print("\nEnter rent term (months): ");
        int contractLengthMonths = userInput.nextInt();
        userInput.nextLine();

        String linkedAccount = accountList.get(sessionIndex).getAccountID();
        Random random = new Random();
        String contractID = Integer.toString(1000 + random.nextInt(9999 - 1000));
        Date contractDate = new Date();
        RentToOwn rentToOwnContract = new RentToOwn(contractID, shoppingCart, linkedAccount, contractLengthMonths, contractDate);

        System.out.printf("Monthly Payment: €%.2f\n", rentToOwnContract.getMonthlyCostWithInterest());
        System.out.printf("Total cost: €%.2f\n", rentToOwnContract.calculateTotalCost());
        
        System.out.print("Do you accept these terms? (y/n): ");
        String acceptance = userInput.nextLine().toLowerCase();

        if (acceptance.equals("y")) {
            contractList.add(rentToOwnContract);
            System.out.println(rentToOwnContract.toString());
        } else {
            System.out.println("\nContract creation cancelled. Returning to main menu.");
            return;
        }
    }

    private void createPurchaseContract(int sessionIndex) {
        String linkedAccount = accountList.get(sessionIndex).getAccountID();
        Random random = new Random();
        String contractID = Integer.toString(1000 + random.nextInt(9999 - 1000));

        Purchase purchaseContract = new Purchase(contractID, shoppingCart, linkedAccount);

        System.out.printf("\nTotal Purchase Cost: €%.2f\n", purchaseContract.calculateTotalCost());
        System.out.printf("Warranty Length: %d months\n", purchaseContract.getWarrantyLengthMonths());

        System.out.print("Do you accept these terms? (y/n): ");
        String acceptance = userInput.nextLine().toLowerCase();

        if (acceptance.equals("y")) {
            contractList.add(purchaseContract);
            System.out.println(purchaseContract.toString());
        } else {
            System.out.println("\nPurchase cancelled. Returning to main menu.");
            return;
        }
    }

    /** Test classes */
    private void createTestProducts() {

        Product product1 = new Product("1001", "Refrigerator", "Large, stainless steel fridge", "CoolTech", 25, 800);
        productList.add(product1);

        Product product2 = new Product("1002", "Washing Machine", "High-efficiency washer", "CleanCorp", 30, 601);
        productList.add(product2);

        Product product3 = new Product("1003", "Dishwasher", "Quiet, energy-saving dishwasher", "ShinyClean", 15, 451);
        productList.add(product3);

        Product product4 = new Product("1004", "Microwave Oven", "Countertop microwave with sensor cooking", "QuickHeat", 50, 150);
        productList.add(product4);

        Product product5 = new Product("1005", "Sofa", "Comfortable three-seater sofa", "CozyLiving", 10, 900);
        productList.add(product5);

        Product product6 = new Product("1006", "Dining Table", "Solid wood dining table for six", "WoodCraft", 8, 701);
        productList.add(product6);

        Product product7 = new Product("1007", "Bed Frame", "Queen-size platform bed frame", "SleepWell", 20, 550);
        productList.add(product7);

        Product product8 = new Product("1008", "Bookshelf", "Tall, wooden bookshelf with adjustable shelves", "ShelfMaster", 35, 201);
        productList.add(product8);

        Product product9 = new Product("1009", "Toaster", "2 slice toaster, stainless steel", "ToastTime", 60, 35);
        productList.add(product9);

        Product product10 = new Product("1010", "Blender", "High powered blender", "SmoothieKing", 40, 100);
        productList.add(product10);
    }

    /*private void createTestAccounts() {
        Account newAccount = new Account("9994", "Otto Von Bismarck", "bismarck@gmail.de", new Date());
        accountList.add(newAccount);
        System.out.print("Account creation successful! Your account ID is 9995");

        Account newAccount2 = new Account("9995", "Marie Curie", "marie.curie@science.org", new Date());
        accountList.add(newAccount2);
        System.out.printf("Account creation successful! Your account ID is %s\n", "1234");

        Account newAccount3 = new Account("9996", "Alan Turing", "alan.turing@computing.net", new Date());
        accountList.add(newAccount3);
        System.out.printf("Account creation successful! Your account ID is %s\n", "5678");

        Account newAccount4 = new Account("9997", "Rosa Parks", "rosa.parks@civilrights.info", new Date());
        accountList.add(newAccount4);
        System.out.printf("Account creation successful! Your account ID is %s\n", "0001");

        Account newAccount5 = new Account("9998", "Leonardo da Vinci", "leonardo@artandscience.it", new Date());
        accountList.add(newAccount5);
        System.out.printf("Account creation successful! Your account ID is %s\n", "4321");
    }

    /* private void createTestContracts() {
    RentToOwn rentToOwnContract = new RentToOwn(contractID, shoppingCart, linkedAccount, totalCartCost, productDetails, contractLengthMonths);
    RentToOwn rentToOwnContract = new RentToOwn("9994", null, "techFanatic", 799.99, "Premium Noise-Cancelling Headphones - Wireless, Bluetooth 5.0, 30-hour battery.", 24);
    Lease leaseContract = new Lease(contractID, shoppingCart, linkedAccount, totalCartCost, productDetails, contractLengthMonths);
    Purchase purchaseContract = new Purchase(contractID, shoppingCart, linkedAccount, totalCartCost, productDetails);
    }  

    private void createTestAccounts() {
        Account account1 = new Account("A1001", "John Doe", "john@example.com", new Date());
        Account account2 = new Account("A1002", "Jane Roe", "jane@example.com", new Date());
        Account account3 = new Account("A1003", "Von Doe", "von@example.com", new Date());
        Account account4 = new Account("A1004", "Dan Roe", "dan@example.com", new Date());
        accountList.add(account1);
        accountList.add(account2);
        accountList.add(account3);
        accountList.add(account4);
    }

    private void createTestLeases() {
        ArrayList<String> dummySelection = new ArrayList<>(); //Fake product selection

        if (productList.size() >= 2) {
            dummySelection.add(productList.get(0).getProductID());
            dummySelection.add(productList.get(1).getProductID());
        } else if (productList.size() == 1) {
            dummySelection.add(productList.get(0).getProductID());
        } else {
            System.out.println("\nNo products available to create test leases.");
            return;
        }

        // Sample product string with total cost
        String productDetails = "";
        double totalCartCost = 0;
        for (String id : dummySelection) {
            int index = getProductIndex(id);
            if(index != -1) {
                Product p = productList.get(index);
                productDetails += p.toString() + "\n";
                totalCartCost += p.getProductBasePrice();
            }
        } 

        //get a dummy account and if none exist use the id A1000
        String linkedAccount = accountList.size() > 0 ? accountList.get(0).getAccountID() : "A1000";

        String contractID1 = "L1001";
        Lease lease1 = new Lease(contractID1, dummySelection, linkedAccount, totalCartCost, productDetails, 12);

        String contractID2 = "L1002";
        Lease lease2 = new Lease(contractID2, dummySelection, linkedAccount, totalCartCost, productDetails, 24);

        contractList.add(lease1);
        contractList.add(lease2);

        System.out.println("Test leases created:");
        System.out.println(lease1.toString());
        System.out.println(lease2.toString());
    }     */
}