import java.util.*;

/**
 * Write a description of class AdminView here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class AdminView
{
    private ControllerClass controller;
    private ArrayList<Contract> contractList;
    private ArrayList<Product> productList;
    private ArrayList<Account> accountList;
    private Random random = new Random();

    public AdminView(ControllerClass controller) {
        this.controller = controller;
        this.productList = controller.productList;
        this.contractList = new ArrayList<>();
        this.accountList = new ArrayList<>();
    }
    
    public void adminView() {
        System.out.print("\f");
        boolean exitTheMatrix = false;
        Scanner userInput = new Scanner(System.in); 

        while (exitTheMatrix == false) {
            System.out.print("\nPlease, select what you want to do: \n\n1. Add new product \n2. Change product details \n3. Delete product \n4. Show catalogue \n5. Show stock report \n6. View product details \n7. Exit. \n\nYou choice:");

            switch (userInput.nextInt()) {
                case 1:
                    addProduct();
                    break;
                case 2:
                    changeProductDetails();
                    break;
                case 3:
                    deleteProduct();
                    break;
                case 4:
                    controller.showCatalogue();
                    break;
                case 5:
                    controller.showStockReport();
                    break;
                case 6:
                    viewProductDetails();
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

    /***** MAIN METHODS *****/

    private int getProductIndex(String productID) {
        for(int arrayIndex = 0; arrayIndex<productList.size(); arrayIndex++) {
            if(productList.get(arrayIndex).getProductID().equals(productID)) {
                return arrayIndex;
            }
        }

        System.out.printf("Product ID %s was not found in our database.", productID);
        return -1;
    }

    private void addProduct() {
        Scanner userInput = new Scanner(System.in); 
        System.out.print("\fPlease, insert details for the new product\n\n");
        String productID = Integer.toString(1000 + random.nextInt(9999 - 1000));
        System.out.print("product Name: "); String productName = userInput.nextLine();
        System.out.print("Product Description: "); String productDescription = userInput.nextLine();
        System.out.print("Product Supplier: "); String productSupplier = userInput.nextLine();
        System.out.print("Product Stock: "); int productStock = userInput.nextInt(); userInput.nextLine(); 
        System.out.print("Product Base Price: "); double productBasePrice = userInput.nextDouble(); userInput.nextLine();

        for (Product productObject : productList) {
            if (productObject.getProductName().equals(productName)) {
                System.out.print("Sorry, this product already exsits!");
                return;
            }
        }

        Product productObject = new Product (productID, productName, productDescription, productSupplier, productStock, productBasePrice);
        productList.add(productObject);

        //random.nextInt();
        //Random random = new Random();
    }

    private void changeProductDetails() {
        Scanner userInput = new Scanner(System.in); 
        int selectedOption;

        System.out.print("Please, select the product where you want to change details (type product ID): ");
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

    public void deleteProduct() {
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

    private void viewProductDetails() {
        Scanner userInput = new Scanner(System.in);
        System.out.print("Please, select the product where you want to view details (type product ID): ");
        int targetIndex = getProductIndex(userInput.nextLine());
        System.out.print(productList.get(targetIndex).toString());
    }

}
