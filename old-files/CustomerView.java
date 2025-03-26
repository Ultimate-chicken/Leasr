import java.util.*;

/**
 * Write a description of class CustomerView here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */

public class CustomerView
{
    //TODO:
    // - handle controller referencing mechanism LATER
    // - create deposit calculation mechanism
    // - add validation to user input
    // - change messages to more user-friendly tone + add info like calculation etc
    // - reduce repeatable code
    // - add deposit calculation method
    // - add lease calculation based on duration
    // - add feature adding multiple products into the busket before logging in
    // - add feature to return to the log in screen if something went bad
    // - check if after leasing the item it actually is taken from the stock
    // - after product was not found message add line for design purposes
    // - add unified view for both admin and user
    // - add separate line so its easier to see different products
    // - after login or create add \n (both when asking after product and in general)
    // - all contracts should show calculated monthly price with added details such as interest rate etc, no user input, only if user agrees to terms
    // - user is asked firstly for how long he wants the item and then price is calculated
    // - create the check if user is already logged in, if he is then create a different view for logged in user

    private ControllerClass controller;
    private Scanner userInput = new Scanner(System.in);
    private ArrayList<Account> accountList;
    private ArrayList<Contract> contractList;
    private ArrayList<Product> shoppingCart;
    private Account currentAccount = null;    

    public CustomerView(ControllerClass controller) {
        this.controller = controller;
        this.accountList = new ArrayList<>();
        this.contractList = new ArrayList<>();
        this.shoppingCart = new ArrayList<>();
    }

    /***** VIEWS *****/

    public void customerView() {
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
                    controller.showCatalogue();
                    handleProductSelection();
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

    private void handleAccountMenu(int sessionIndex) {
        boolean backToMainMenu = false;

        while (!backToMainMenu) {
            System.out.print("\nPlease, select what you want to do: \n"
                + "1. Create a new contract \n"
                + "2. View account details \n"
                + "3. Exit \n\nYour choice: ");

            int choice = userInput.nextInt();
            userInput.nextLine();

            switch (choice) {
                case 1:
                    createContract(sessionIndex);
                    break;
                case 2:
                    viewAccountDetails(sessionIndex);
                    break;
                case 3:
                    backToMainMenu = true;
                    break;
                default:
                    System.out.println("Invalid choice.");
                    break;
            }
        }
    }

    /***** METHODS *****/

    private int loginToAccount() {
        System.out.print("\nPlease, insert account ID: ");
        String userInputString = userInput.nextLine();

        if (getAccountIndex(userInputString) > -1) {
            System.out.print("Login successful. ");
            return getAccountIndex(userInputString);
        } else {
            return -1;
        }
    }

    private void handleProductSelection() {
        System.out.print("\nDo you want to select product(s)? (y/n): ");
        String response = userInput.nextLine().toLowerCase();
        if (response.equals("y")) {
            System.out.print("Enter product ID(s) separated by commas: ");
            String productIDs = userInput.nextLine();
            String[] productIDArray = productIDs.split(",");

            int productsAdded = 0;
            for (String productID : productIDArray) {
                int productIndex = controller.getProductIndex(productID.trim());
                if (productIndex != -1) {
                    Product selectedProduct = controller.productList.get(productIndex);
                    shoppingCart.add(selectedProduct);
                    productsAdded++;
                } else {
                    System.out.println("Product with ID '" + productID.trim() + "' not found.");
                }
            }

            if (productsAdded > 0) {
                System.out.println("Product(s) added to cart.");
                processShoppingCart();
            } else {
                System.out.println("No valid products were added to the cart.");
            }
        }
    }

    private void processShoppingCart() {
        if (currentAccount == null) {
            System.out.println("\nYou must log in or create an account to proceed.");
            System.out.print("Do you want to log in or create an account? (login/create): ");
            String action = userInput.nextLine().toLowerCase();

            if (action.equals("login")) {
                loginToAccount();
            } else if (action.equals("create")) {
                createAccount();
            }
        }

        if (currentAccount != null) {
            chooseContractType();
        }
    }

    private void chooseContractType() {
        System.out.println("\nChoose contract type:");
        System.out.println("1. Lease");
        System.out.println("2. Rent-to-Own");
        System.out.println("3. Purchase");
        System.out.print("Your choice: ");

        int contractChoice = userInput.nextInt();
        userInput.nextLine();

        switch (contractChoice) {
            case 1:
                createLeaseContract();
                break;
            case 2:
                createRentToOwnContract();
                break;
            case 3:
                createPurchase();
                break;
            default:
                System.out.println("Invalid contract type.");
        }
    }

    private void createLeaseContract() {
        System.out.print("Enter lease duration (months): ");
        int leaseDuration = userInput.nextInt();
        userInput.nextLine();

        String contractID = generateUniqueContractID();
        Lease leaseContract = new Lease(contractID, shoppingCart, currentAccount, leaseDuration);

        System.out.printf("Deposit Amount: €%.2f\n", leaseContract.getDepositAmount());
        System.out.printf("Monthly Payment: €%.2f\n", leaseContract.getMonthlyPayment());

        System.out.print("Do you accept these terms? (y/n): ");
        String acceptance = userInput.nextLine().toLowerCase();

        if (acceptance.equals("y")) {
            contractList.add(leaseContract);
            System.out.println(leaseContract.toString());
            clearShoppingCart();
        } else {
            System.out.println("Contract creation cancelled. Returning to main menu.");
            clearShoppingCart();
            return;
        }
    }

    private void createRentToOwnContract() {
        System.out.print("Enter rent term (months): ");
        int totalPaymentPeriods = userInput.nextInt();
        userInput.nextLine();

        String contractID = generateUniqueContractID();
        RentToOwn rentToOwnContract = new RentToOwn(contractID, shoppingCart, currentAccount, totalPaymentPeriods);

        System.out.printf("Deposit Amount: €%.2f\n", rentToOwnContract.getDepositAmount());
        System.out.printf("Monthly Payment: €%.2f\n", rentToOwnContract.getMonthlyPayment());

        System.out.print("Do you accept these terms? (y/n): ");
        String acceptance = userInput.nextLine().toLowerCase();

        if (acceptance.equals("y")) {
            contractList.add(rentToOwnContract);
            System.out.println(rentToOwnContract.toString());
            clearShoppingCart();
        } else {
            System.out.println("Contract creation cancelled. Returning to main menu.");
            clearShoppingCart();
            return;
        }
    }

    private void createPurchase() {
        String contractID = generateUniqueContractID();

        Purchase purchaseContract = new Purchase(contractID, shoppingCart, currentAccount, 24);

        System.out.printf("\nTotal Purchase Cost: €%.2f\n", purchaseContract.getTotalCost());
        System.out.printf("Warranty Length: %d months\n", purchaseContract.getWarrantyLengthMonths());

        System.out.print("Do you accept these terms? (y/n): ");
        String acceptance = userInput.nextLine().toLowerCase();

        if (acceptance.equals("y")) {
            contractList.add(purchaseContract);
            System.out.println(purchaseContract.toString());
            clearShoppingCart();
        } else {
            System.out.println("Purchase cancelled. Returning to main menu.");
            clearShoppingCart();
            return;
        }
    }

    private double calculateTotalCartCost() {
        double totalCost = 0;

        for (Product product : shoppingCart) {
            totalCost += product.getProductBasePrice();
        }
        return totalCost;
    }

    private String createAccount() {
        System.out.print("Please, insert account ID: "); 
        String accountID = userInput.nextLine();

        System.out.print("Please, insert full name: "); 
        String fullName = userInput.nextLine();

        System.out.print("Please, insert user email: "); 
        String userEmail = userInput.nextLine();

        Account newAccount = new Account(accountID, fullName, userEmail, new Date());
        accountList.add(newAccount);
        currentAccount = newAccount;

        System.out.println("Account creation successful!");
        return accountID;
    }

    private String generateUniqueContractID() {
        return "CONTRACT_" + new Random().nextInt(9999);
    }

    private void clearShoppingCart() {
        shoppingCart.clear();
    }

    public void viewAccountDetails(int sessionIndex) {
        System.out.println(accountList.get(sessionIndex).toString());

        System.out.println("\nContracts associated with this account:");
        for (Contract contractObject : contractList) {
            if (contractObject.getAccountID().equals(accountList.get(sessionIndex).getAccountID())) {
                System.out.println(contractObject.toString());
            }
        }
    }

    public int getAccountIndex(String accountID) {
        for (int i = 0; i < accountList.size(); i++) {
            if (accountList.get(i).getAccountID().equals(accountID)) {
                currentAccount = accountList.get(i);
                return i;
            }
        }
        System.out.printf("Account ID %s was not found in our database.\n", accountID);
        return -1;
    }

    public void createContract(int sessionIndex) {
        shoppingCart.clear(); // Clear previous cart items
        controller.showCatalogue();
        System.out.print("\n\nPlease, select the products you want (Write product IDs, separated by commas): ");
        String[] shoppingCartReferences = userInput.nextLine().split(",");

        for (String reference : shoppingCartReferences) {
            int productIndex = controller.getProductIndex(reference.trim());
            if (productIndex != -1) {
                Product selectedProduct = controller.productList.get(productIndex);
                shoppingCart.add(selectedProduct);
            }
        }

        System.out.print("\nSelect contract type:\n1. Lease\n2. Rent-to-Own\nYour choice: ");
        int contractType = userInput.nextInt();
        userInput.nextLine();

        switch (contractType) {
            case 1:
                createLeaseContract();
                break;
            case 2:
                createRentToOwnContract();
                break;
            default:
                System.out.println("Invalid contract type.");
        }
    }
}
