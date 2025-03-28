import java.util.ArrayList;
import java.util.Date;

/** This is a core class in our model. In the controller, we have a contract arrayList that includes objects of type contract, but we will have
 * objects of subclasses given that we cannot have objects of an abstract class. Essentially, this class acts as a middleman that gets common
 * fields among the different types of objects, but lets the subclasses execute their own fields and methods (through abstract and overrided
 * methods). 
 * This is a core class in our model. Objects of type contract are stored in an array list within the controller, but given that contract is 
 * abstract (and recurringContract, as we will see later), we will only store objects of type lease, purchase, or rent-to-own. The contract 
 * class acts as the middle man between the controller and the subclasses, using parameters from the controller (such as contract ID) by 
 * "shadowing them and instantiating them on the constructor. A key field here is productSelection, which is an arrayList of <Product> 
 * objects that can be used to calculate price, get details, etcetera. This is stored in the contract because it is a common attribute for
 * all subclasses. @author (Noah, Max) @version (28/03/2025) */
public abstract class Contract {
    protected String contractID;
    protected ArrayList<Product> productSelection;
    protected String linkedAccount;
    protected Date contractDate;
    protected boolean isActive;
    
    /** This is an abstract method for an abstract class. Implemented (non-abstractly) at each subclass. */
    public abstract double calculateTotalCost();

    public Contract(String contractID, ArrayList<Product> productSelection, String linkedAccount) {
        this.contractID = contractID;
        this.linkedAccount = linkedAccount;
        this.contractDate = new Date();
        this.isActive = true;

        this.productSelection = productSelection;

        calculateTotalCost();

    }

    protected abstract void calculateTotalCost();

    //Getters
    public String getContractID() { 
        return contractID;
    }

    public boolean isActive() { 
        return isActive; 
    }

    public String getLinkedAccount() { 
        return linkedAccount; 
    }
    
    public ArrayList<Product> getProductSelection() {

    public ArrayList<String> getProductSelection() {
        return productSelection;
    }
    
    protected String getContractType() {
        return "Undefined Contract";
    }
    
    public Date getContractDate() {
        return contractDate; 
    }
    

    /** This method is important. It will be called in the toString method of subclasses as the getter is not defined there. What it does 
     * is it creates a foreach loop and calls toString for each product in the product selection arrayList. These iterations are all
     * incorporated into a comprehensive appended string. @param none. @return An appended string that includes a combination of toStrings. 
     * This is for the "products included in this contract" section. */
    public String getProductDetails() {
        String appendedProductDetails = "";
        for (Product productObject : productSelection) {
            appendedProductDetails += String.format("\n%s", productObject.toString());
        }
        return appendedProductDetails;
    @Override
    public String toString() {
        StringBuilder contractDetails = new StringBuilder();

        contractDetails.append("\n===== Contract Details =====\n");
        contractDetails.append(String.format("Contract ID: %s\n", contractID));
        contractDetails.append(String.format("Contract Type: %s\n", getContractType()));
        contractDetails.append(String.format("Contract Date: %s\n", contractDate));

        contractDetails.append(String.format("\nProduct(s) in Contract:\n %s", productDetails));

        contractDetails.append(String.format("\nTotal Contract Value: %.2f€\n", totalCost));
        contractDetails.append(String.format("Contract Status: %s\n", isActive ? "Active" : "Inactive"));

        contractDetails.append("==============================");
        return contractDetails.toString();
    }

    /** toString method for the abstract contract class. Under ideal circumstances, it will not be called because it will get overriden
       by the toString methods in the subclasses. It includes boilerplate information from common contract attributes, but doens't
       include unique fields from subclasses. @param none. @return String toString when object is called. */
    @Override
    public String toString() {
        return String.format(
        "\n===== Contract Details =====\n" +
        "Contract ID: %s\n" +
        "Contract Type: %s\n" +
        "Contract Date: %s\n" +
        "\nProduct(s) in Contract:\n %s\n" +
        "Contract Status: %s\n" +
        "==============================\n",
        getContractID(), 
        getContractType(),
        getContractDate(),
        getProductDetails(),
        isActive()
        );
    }
}
