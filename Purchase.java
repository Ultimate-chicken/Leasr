import java.util.Date;
import java.util.ArrayList;

/** This is a subclass of contract (not of recurringContract). Its main functionalities are to override toString from contract class, execute
 * the calculateTotalCost() abstract method, and calculate warranty of the product bundle. A simple if statement is used for this. We can
 * see though that at the toString method we employ getters from the contract class, as they are protected (not private). This prevents us
 * from having to do everything over again locally. Because we have no local methods with the same name/signature as those from the contract
 * class, we don't need to specify super.method(), but it won't harm us if we do. @author (Noah, Max) @version (28/03/2025) */
public class Purchase extends Contract
{
    private int warrantyLengthMonths;
    
    public Purchase(String contractID, ArrayList<Product> productSelection, String linkedAccount) {
        super(contractID, productSelection, linkedAccount);
        calculateTotalCost();
        calculateWarranty();
    }
    
    @Override
    public String toString() {
        return String.format(
        "\n===== Purchase details =====\n" +
        "Purchase ID: %s\n" +
        "Purchase Date: %s\n" +
        "Warranty Length: %d months\n" +
        "\nProduct(s) in Contract: %s\n" +
        "\nTotal Contract Value: %.2f€\n" +
        "==============================\n",
        getContractID(), 
        getContractDate(),
        getWarrantyLengthMonths(), 
        getProductDetails(),
        calculateTotalCost());
    }
    
    public double calculateTotalCost() {
        double runningTotal = 0;
        
        for (Product productObject : super.productSelection) {
            runningTotal += productObject.getProductBasePrice();
        }
        return runningTotal;
        }
    
    //if total cost of purchase is more than 500, apply longer warranty. 
    public void calculateWarranty() {
        if (calculateTotalCost() < 500) {
            this.warrantyLengthMonths = 6;
        } else {
            this.warrantyLengthMonths = 12;
        }
    }

    public int getWarrantyLengthMonths() {
        return warrantyLengthMonths;
    }
    
    }
