import java.util.ArrayList;
import java.util.Scanner;
import java.util.Date;
import java.util.Random;

/** This is the brain of our program. It is responsible for all processing of input/output data and of linking the different 
 * classes together. The most important fields of this class are the contractList, productList, and accountList, which contain objects
 * of their respective types. These lists will be used throughout the program to access specific details or execute certain operations. 
 * Along with the aforementioned lists, a view object is also instantiated in the constructor, which will be used for all input and output.
 * The shopping cart objects will be created every time a contract is drafted. Within the controller class itself, we can see two main 
 * public methods: customer view and admin view. @author (Noah, Max, Ahmada) @version (28/03/2025) */
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

    /** Admin view. Allows administrators to add/delete product, change/view product details, and show list of products/accounts/leases.
       What is done in this class will later be reflected on the customer view, specially showCatalogue  and add/delete product. This class
       is a big switch statement that directs the user to the appropiate methods. It is implemented by using a while loop that keeps going
       unless the user chooses exit, in which case the boolean is set to true. @param none. @return none */
    public void adminView() {
        boolean exitTheMatrix = false;

        view.showSimpleMessage("\f");
        
        while (exitTheMatrix == false) {
            view.showSimpleMessage("\nPlease, select what you want to do: \n\n1. Add new product \n2. View product details \n3. Change product details \n4. Delete product \n5. Show catalogue \n6. Show stock report \n7. Show all contracts \n8. Exit. \n\nYou choice:");

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
                    showAllContracts();
                    break;
                case 8:
                    exitTheMatrix = true;
                    break;
                default:
                    view.showSimpleMessage("Invalid choice. Please enter a number between 1 and 7.");
                    break;
            }
        }
    }

    /** The getIndex functionality is the same for product, account, and contract, but with different lists and inputs. In this case, the 
       method is getting the product ID as a parameter and uses a for loop to check if the product ID of the product object currently
       in iteration equals the parameter productID. If it matches, it will return the index of this product within the product list. If 
       there are not matches in the entire for loop, the user will be informed. @param product ID (most likely typed by the user 
       when wanting to do something in particular with a specific product). @return index of product within array list.  */
    private int getProductIndex(String productID) {
        for(int arrayIndex = 0; arrayIndex<productList.size(); arrayIndex++) {
            if(productList.get(arrayIndex).getProductID().equals(productID)) {
                return arrayIndex;
            }
        }

        view.getOutputError(productID);
        return -1;
    }
    
    //shows ID, linkedAccount, and implementation of calculateTotalCost abstract method for all accounts (regardless of contract type).
    private void showAllContracts() {
        for (Contract contractObject : contractList) {
            view.showSimpleMessage(String.format("Contract ID: %s \n", contractObject.getContractID()));
            view.showSimpleMessage(String.format("Linked account: %s \n", contractObject.getLinkedAccount()));
            view.showSimpleMessage(String.format("Total cost: %.2f€ \n", contractObject.calculateTotalCost()));
        }
        
    }

    /** This method just gets different inputs from the user that are necessary to create a new product, checks that the product doesn't
       exit already, and if this is not the case, creates a new product object and adds it to the product list. @param none @return none */
    private void addProduct() {
        Random random = new Random();
        String productID = Integer.toString(1000 + random.nextInt(9999 - 1000));
        view.showSimpleMessage("Please, insert details for the new product\n\n");
        String productName = view.getUserString("name");
        String productDescription = view.getUserString("description");
        String productSupplier = view.getUserString("supplier");
        
        int productStock = view.getUserInt("stock");
        double productBasePrice = view.getUserDouble("basePrice");

        for (Product productObject : productList) {
            if (productObject.getProductName().equals(productName)) {
                view.showSimpleMessage("\nSorry, this product already exsits!");
                return;
            }
        }

        Product productObject = new Product (productID, productName, productDescription, productSupplier, productStock, productBasePrice);
        productList.add(productObject);
    }

    /** The pattern arrayList.get(getIndex()).method is used commonly throughout the program. In this case, we get the position of the 
       product that matches input product ID and execute the toString method from there. @param none @return none */
    private void viewProductDetails() {
        int targetIndex = getProductIndex(view.getUserString("productDetailsWithID"));
        view.showSimpleMessage(productList.get(targetIndex).toString());
    }

    /** This method uses the same getIndex method as viewProductDetails, and then prompts the user to choose which field it wants to 
       change. A switch statement then is used which executed different setters depending on the user choice. @param none @return none */
    private void changeProductDetails() {
        int targetIndex = getProductIndex(view.getUserString("changeDetailsWithID"));
        view.showSimpleMessage(productList.get(targetIndex).toString());
        view.showSimpleMessage("\nPlease, select the field you want to change (indicate the number): \n\n 1. Product ID: \n 2. Product name: \n 3. Product description: \n 4. Product supplier: \n 5. Product Stock: \n 6. Product Price: € \n\n Your choice: ");
        int selectedOption = view.getUserChoice();

        switch(selectedOption) {
            case 1: 
                String newID = view.getUserString("newProductID");
                productList.get(targetIndex).setProductID(newID);
                break;

            case 2:
                String newName = view.getUserString("newProductName"); 
                productList.get(targetIndex).setProductName(newName);
                break;

            case 3:
                String newDescription = view.getUserString("newProductDescription"); 
                productList.get(targetIndex).setProductDescription(newDescription);
                break;

            case 4:
                String newSupplier = view.getUserString("newProductSupplier"); 
                productList.get(targetIndex).setProductSupplier(newSupplier);
                break;

            case 5:
                int newStock = view.getUserInt("newStock");
                productList.get(targetIndex).setProductStock(newStock);
                break;

            case 6:
                double newPrice = view.getUserDouble("newPrice");
                productList.get(targetIndex).setProductBasePrice(newPrice);
                break;

        }

        view.showSimpleMessage("\n\nProduct details have been succesfully changed.");
    }

    /** This method let's the user select a product using product ID. If this product exists (index != -1), the user is prompted for 
       confirmation and if the user confirms, the product object is deleted from the array list of products. @param none @return none */
    private void deleteProduct() {
        String productID = view.getUserString("deleteProductWithID");

        if (getProductIndex(productID) != -1) {
            view.showFormattedMessage("\nAre you sure you want Delete product %s? Type y for yes and n for no.\n Y/N: ", productID);
            if (view.getUserString("productDeletionConfirmation").contains("y")) {
                productList.remove(getProductIndex(productID));
                view.showFormattedMessage("\nProduct %s was successfully removed.", productID);
                return;
            } else {
                return;
            }
        }
    }

    //Just calls toString for each product in the product list. 
    private void showCatalogue() {
        view.showSimpleMessage("\nCatalogue of all products\n");
        for (Product productObject : productList) {
            view.showSimpleMessage(productObject.toString() + "\n");
        }
    }

    ///For each product in product list, show formatted string including product id, product name, and product stock. 
    private void showStockReport() {
        view.showSimpleMessage("\f\nCurrent stock levels for all products (Name/ID: Stock):\n");
        for (Product productObject : productList) {
            view.showSimpleMessage(String.format (productObject.getProductName() + "/" + productObject.getProductID() + ": " + productObject.getProductStock() + "\n"));
        }
    }

    /** Customer view. Redirects customers to methods they want to execute, including showCatalogue, login, create account, or exit. Alike
       the admin view class, this method also uses a while loop to ensure that the user will remain in the same menu unless otherwise 
       specified. This may seem innecessary, but as we will see in other methods, return; will take you back to this menu. 
       @param none @return none */
    public void customerView() {
        view.showSimpleMessage("\f");
        boolean exitTheMatrix = false;

        while (!exitTheMatrix) {
            view.showSimpleMessage("\nPlease, choose what you want to do: \n1. Show all products \n2. Create an account \n3. Login \n4. Exit \n\nYour choice: ");
            int choice = view.getUserChoice();

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
                    view.showSimpleMessage("Invalid choice.");
            }
        }
    }

    //same as getProductIndex. 
    private int getAccountIndex(String accountID) {
        for(int arrayIndex = 0; arrayIndex<accountList.size(); arrayIndex++) {
            if(accountList.get(arrayIndex).getAccountID().equals(accountID)) {
                return arrayIndex;
            }
        }
        view.getOutputError(accountID);
        return -1;
    }

    // This is a submenu of the customer viwe class. It is executed after showCatalogue and let's the user decide whether to login or create account.
    private void promptLogin() {
        view.showSimpleMessage("\nIf you want to select products, you must log in or create an account. Type LOGIN or CREATE if you want to proceed.");
        String userSelection = view.getUserString("loginOrCreate");
        
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

    /** Create account. It wil get necessary inputs to create an account (some automatically generated, others typed by the user) and then
       will pass these inputs as parameters to the Account constructor. It will add the account to the account array list and will 
       automatically login the user, directing it to the logged in submenu. Here, we use "sessionIndex" as a cookie. It tracks a specific
       account within the arrayList (in this case, the one just created) so that actions can be directly undertaken in this object. 
       @param none @return none */
    private void createAccount() {
        Random random = new Random();
        String accountID = Integer.toString(1000 + random.nextInt(9999 - 1000));
        
        String fullName = view.getUserString("newAccountName");
        String userEmail = view.getUserString("newAccountEmail");

        Account newAccount = new Account(accountID, fullName, userEmail, new Date());
        accountList.add(newAccount);
        view.showFormattedMessage("\nAccount creation successful! Your account ID is %s", accountID);

        int sessionIndex = getAccountIndex(accountID);
        handleAccountMenu(sessionIndex);
    }

    //checks if account exists. If login is successfull, it will open the login menu. 
    private void loginToAccount() {
        String accountID = view.getUserString("login");
        int sessionIndex = getAccountIndex(accountID);

        if (sessionIndex > -1) {
            view.showSimpleMessage("\nLogin successful. ");
            handleAccountMenu(sessionIndex);
        }
    }

    /** This is a submenu that lets the logged in user execute actions on their account. We can see that a while loop is used here, and 
       when "exit" is chosen, the user will be sent back to the main menu. This is effectively a logout. Always using the sessionIndex,
       the user can decide to create/terminate a contract or view account details, which stores not only account details but also 
       details of contracts linked to this account. @param sessionIndex. sessionIndex let's the controller quickly determine the
       position of an account object within the account array list. @return none */
    private void handleAccountMenu(int sessionIndex) {
        boolean backToMainMenu = false;

        while (!backToMainMenu) {
            view.showSimpleMessage("\n\nPlease, select what you want to do: \n1. Purchase, lease, or rent-to-own products \n2. Terminate an existing contract \n3. View account details \n4. Exit \n\nYour choice: ");
            int userSelection = view.getUserChoice();

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
                    view.showSimpleMessage("Invalid choice.");
                    break;
            }
        }
    }

    /** This method shows a list of all recurring contracts (which excludes purchase contracts) that can be terminated. If there is more 
       than one such contracts associated to the current account (denoted by sessionIndex), the user is prompted to select contract ID. We
       implemented an additional failsafe so that users can only delete contracts associated with their account, which is checked by 
       matching the linkedAccount field of the contract object with the current loggedAccount (sessionIndex). If both of these checks are
       passed, the contract object will be deleted from the contract list. @param sessionIndex (same as handleAccountMenu) @return none */
    private void terminateContract(int sessionIndex) {        
        view.showSimpleMessage("\n\nYour active recurring contracts: ");

        int recurringContractCounter = 0;
        for (Contract contractObject : contractList) {
            if (contractObject instanceof RecurringContract) {
                view.showSimpleMessage(contractObject.toString());
                recurringContractCounter++;
            }
        }

        if (recurringContractCounter > 0) { 
            String userSelection = view.getUserString("terminateContract");
            Contract targetObject = contractList.get(getContractIndex(userSelection));

            if (targetObject.getLinkedAccount().equals(accountList.get(sessionIndex).getAccountID())) {
                contractList.remove(targetObject);
                view.showFormattedMessage("Contract %s was succesfully terminated. ", userSelection);
            } else {
                view.showFormattedMessage("\nContract %s is not linked to this account. Please recheck contract ID. ", userSelection);
            }
        } else {
            view.showSimpleMessage("\nCurrently, you don't have any active recurring contract that can be terminated.\n\n");
        }
    }

    //same as getProduct/Account index. 
    private int getContractIndex(String contractID) {
        for(int arrayIndex = 0; arrayIndex<contractList.size(); arrayIndex++) {
            if(contractList.get(arrayIndex).getContractID().equals(contractID)) {
                return arrayIndex;
            }
        }

        view.getOutputError(contractID);
        return -1;
    }   

    // This method will show all contracts associated to an account. Same mechanism as in terminate contract. 
    private void viewAccountDetails(int sessionIndex) {
        view.showSimpleMessage(accountList.get(sessionIndex).toString());
        view.showSimpleMessage("\n\nContracts associated to this account: ");

        int contractCounter = 0;
        for (Contract contractObject : contractList) {
            if (contractObject.getLinkedAccount().equals(accountList.get(sessionIndex).getAccountID())) {
                view.showSimpleMessage(contractObject.toString());
                contractCounter++;
            }
        }

        if (contractCounter < 1) {
            view.showSimpleMessage("\nNo Contracts currently associated to this account.\n ");
        }

    } 

    /** This is probably the most important method in the program. The first thing it does is create a new object of type shopping cart, 
       which will later on be passed as a parameter to the contract constructor. It shows a catalogue of products and lets the user pick
       which ones they want. The string is separated into elements of a string array, and then a foreach loop is employed to fetch 
       products containing the chosen product ID, and then add it to the shopping cart. Next, the user is prompted to select what type of
       contract they want, and redirected to the responsile method. @param sessionIndex (same as handleAccountMenu) @return none */
    private void createContract(int sessionIndex) {
        shoppingCart = new ArrayList<Product>();
        
        showCatalogue();
        view.showSimpleMessage("\n\nPlease, select the products you want (Write product IDs, separated by commas): ");
        
        String[] shoppingCartReferences = userInput.nextLine().split(",");
        
        for (String reference : shoppingCartReferences) {
            int productIndex = getProductIndex(reference.trim());
            if (productIndex != -1) {
                shoppingCart.add(productList.get(productIndex));
            } 
        }

        view.showSimpleMessage("Note that price of products may change depending on contract type. \n");
        view.showSimpleMessage("\nSelect contract type:\n1. Lease\n2. Rent-to-Own\n3. Purchase. \nYour choice: ");

        int contractType = view.getUserChoice();

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
                view.showSimpleMessage("Invalid contract type.");
        }
    }

    /** This method gets the necessary inputs to create a lease object, both automatic and typed. based on this object (which has been
     * created but not added to the arraylist of contracts, the deposit, monthly cost, and total cost of the lease is calculated and shown
     * to the user (calculations are executed within the lease and recurringContract classes). If the user accepts the terms, then the
     * object is added to the array list, otherwise the user returns to the previous menu. @param sessionIndex 
     * (same as handleAccountMenu) @return none */
    private void createLeaseContract(int sessionIndex) {
        int contractLengthMonths = view.getUserInt("contractLength");
        Random random = new Random();
        String contractID = Integer.toString(1000 + random.nextInt(9999 - 1000));
        String linkedAccount = accountList.get(sessionIndex).getAccountID();

        Date contractDate = new Date();
        Lease leaseContract = new Lease(contractID, shoppingCart, linkedAccount, contractLengthMonths, contractDate);

        String depositAmount = String.format("Deposit Amount: €%.2f\n", leaseContract.getDepositAmount());
        String monthlyCost = String.format("Monthly Payment: €%.2f\n", leaseContract.getAdjustedMonthlyCost());
        String totalCost = String.format("Total cost: €%.2f\n", leaseContract.calculateTotalCost());
        view.showSimpleMessage(depositAmount); view.showSimpleMessage(monthlyCost); view.showSimpleMessage(totalCost);
        
        String acceptance = view.getUserString("acceptTerms");

        if (acceptance.equals("y")) {
            contractList.add(leaseContract);
            view.showSimpleMessage(leaseContract.toString());
        } else {
            view.showSimpleMessage("\nContract creation cancelled. Returning to main menu.");
            return;
        }
    }

    // Method is similar to lease, only that calculations will differ as different ratios and fields are used. */
    private void createRentToOwnContract(int sessionIndex) {
        int contractLengthMonths = view.getUserInt("contractLength");

        String linkedAccount = accountList.get(sessionIndex).getAccountID();
        Random random = new Random();
        String contractID = Integer.toString(1000 + random.nextInt(9999 - 1000));
        Date contractDate = new Date();
        RentToOwn rentToOwnContract = new RentToOwn(contractID, shoppingCart, linkedAccount, contractLengthMonths, contractDate);

        String monthlyCost = String.format("Monthly Payment: €%.2f\n", rentToOwnContract.getMonthlyCostWithInterest());
        String totalCost = String.format("Total cost: €%.2f\n", rentToOwnContract.calculateTotalCost());
        view.showSimpleMessage(monthlyCost); view.showSimpleMessage(totalCost);
        String acceptance = view.getUserString("acceptTerms");

        if (acceptance.equals("y")) {
            contractList.add(rentToOwnContract);
            view.showSimpleMessage(rentToOwnContract.toString());
        } else {
            view.showSimpleMessage("\nContract creation cancelled. Returning to main menu.");
            return;
        }
    }

    //Same as lease and rent to own, with different fields and parameters. 
    private void createPurchaseContract(int sessionIndex) {
        String linkedAccount = accountList.get(sessionIndex).getAccountID();
        Random random = new Random();
        String contractID = Integer.toString(1000 + random.nextInt(9999 - 1000));

        Purchase purchaseContract = new Purchase(contractID, shoppingCart, linkedAccount);

        String purchaseCost = String.format("\nTotal Purchase Cost: €%.2f\n", purchaseContract.calculateTotalCost());
        String warrantyLength = String.format("Warranty Length: %d months\n", purchaseContract.getWarrantyLengthMonths());
        view.showSimpleMessage(purchaseCost); view.showSimpleMessage(warrantyLength);
        String acceptance = view.getUserString("acceptTerms");

        if (acceptance.equals("y")) {
            contractList.add(purchaseContract);
            view.showSimpleMessage(purchaseContract.toString());
        } else {
            view.showSimpleMessage("\nPurchase cancelled. Returning to main menu.");
            return;
        }
    }

    /** Class that creates dummy products, which will be used consistenly throughout the program.  @param none @return none*/
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
}