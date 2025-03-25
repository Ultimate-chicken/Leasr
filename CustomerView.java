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
    // - handle \f during first message output (create it as a separate method or something
    // - handle controller referencing mechanism
    // - create deposit calculation mechanism
    // - add validation to user input
    // - change messages to more user-friendly tone + add info like calculation etc
    // - reduce repeatable code
    // - add deposit calculation method
    // - add lease calculation based on duration
    // - add feature adding multiple products into the busket before logging in
    // - add feature to return to the log in screen if something went bad
    // - check if after leasing the item it actually is taken from the stock
    
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

    public void customerView() {
        boolean exitTheMatrix = false;

        while (!exitTheMatrix) {
            System.out.print("Please, choose what you want to do: \n"
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

    public int loginToAccount() {
        System.out.print("Please, insert account ID: ");
        String userInputString = userInput.nextLine();
        
        if (getAccountIndex(userInputString) > -1) {
            System.out.print("Login successful. ");
            return getAccountIndex(userInputString);
        } else {
            return -1;
            }
    }

    private void handleProductSelection() {
        System.out.print("\nDo you want to select a product? (y/n): ");
        String response = userInput.nextLine().toLowerCase();

        if (response.equals("y")) {
            System.out.print("Enter product ID: ");
            String productID = userInput.nextLine();

            int productIndex = controller.getProductIndex(productID);
            if (productIndex != -1) {
                Product selectedProduct = controller.productList.get(productIndex);
                shoppingCart.add(selectedProduct);
                System.out.println("Product added to cart.");

                processShoppingCart();
            } else {
                System.out.println("Product not found.");
            }
        }
    }

    private void processShoppingCart() {
        if (currentAccount == null) {
            System.out.println("You must log in or create an account to proceed.");
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
        System.out.print("Enter monthly lease cost: ");
        double monthlyLeaseCost = userInput.nextDouble();

        System.out.print("Enter lease duration (months): ");
        int leaseDuration = userInput.nextInt();

        System.out.print("Enter deposit amount: ");
        double depositAmount = userInput.nextDouble();

        String contractID = generateUniqueContractID();

        Lease leaseContract = new Lease(contractID, shoppingCart, currentAccount, monthlyLeaseCost, leaseDuration, depositAmount);
        
        contractList.add(leaseContract);
        System.out.println("Lease contract created: " + contractID);
        clearShoppingCart();
    }

    private void createRentToOwnContract() {
        System.out.print("Enter monthly payment: ");
        double monthlyPayment = userInput.nextDouble();

        System.out.print("Enter total payment periods: ");
        int totalPaymentPeriods = userInput.nextInt();

        String contractID = generateUniqueContractID();

        RentToOwn rentToOwnContract = new RentToOwn(contractID, shoppingCart, currentAccount, monthlyPayment, totalPaymentPeriods);
        contractList.add(rentToOwnContract);
        System.out.println("Rent-to-Own contract created: " + contractID);
        clearShoppingCart();
    }
    
    private void createPurchase() {
        String contractID = generateUniqueContractID();
        Date currentDate = new Date();
        double totalCartCost = calculateTotalCartCost();
        
        //Purchase purchaseContract = new Purchase(contractID, currentDate, totalCartCost, shoppingCart, 24, currentAccount.getAccountID());
        //contractList.add(purchaseContract);
        System.out.println("Purchase executed successfully!");
        clearShoppingCart();
    }

    private double calculateTotalCartCost() {
        double totalCost = 0;
        for (Product product : shoppingCart) {
            totalCost += product.getProductBasePrice();
        }
        return totalCost;
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