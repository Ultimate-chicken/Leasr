import java.util.Date;
import java.util.ArrayList;

/**
 * Write a description of class Purchase here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Purchase extends Contract
{
    private int warrantyLengthMonths;
    
    public Purchase(String contractID, ArrayList<String> productSelection, String linkedAccount, double totalCartCost, String productDetails) {
        super(contractID, productSelection, linkedAccount, totalCartCost, productDetails);
        this.warrantyLengthMonths = warrantyLengthMonths;
    }
    
    
    
    /*@Override
    public String toString() {
        return "\nPurchase details:\n" + "Purchase ID: " + getContractID() + "\nPurchase Date: " + getCurrentDate() +
        "\nTotal cost: " + String.format("%.2f€", getTotalCost()) + "\nWarranty length (months): " + getWarrantyLengthMonths();
    }*/
    
    //getters
    
    public int getWarrantyLengthMonths() {
        return warrantyLengthMonths;
    }
    
    }
