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
    private Date purchaseDate;
    private int warrantyLengthMonths;
    
    public Purchase(String contractID, Date purchaseDate, ArrayList<Product> productSelection, int warrantyLengthMonths, Account linkedAccount)
    {
        super(contractID, productSelection, linkedAccount);
        this.purchaseDate = purchaseDate;
        this.warrantyLengthMonths = warrantyLengthMonths;
    }
    
    @Override
    public String toString() {
        return "\n\nPurchase details: \nPurchase ID: " + getContractID() + "\n\nContract details: " + super.toString() + ". Product details:\n" + getProductDetails() + "Account ID: " + getAccountID() + "\nTotal cost: " + String.format("%.2f", getTotalCost()) + "€\nPurchase date: " 
        + getPurchaseDate() + "\nWarranty length (months): " + getWarrantyLengthMonths();
    }
    
    //getters
    
    public String getProductDetails() {
        String appededProductDetails ="";
        for (Product product : productSelection) {
            String productDetails= String.format(
            "Product ID: %s. Product Name: %s. Product Description: %s. Product Supplier: %s. Product Stock: %d. Product Base Price: %.2f\n",
            product.getProductID(),
            product.getProductName(),
            product.getProductDescription(),
            product.getProductSupplier(),
            product.getProductStock(),
            product.getProductBasePrice()
            );
            
            appededProductDetails += productDetails;
        }
        return appededProductDetails;
    }
    
    public Date getPurchaseDate() {
        return purchaseDate;
    }
    
    public int getWarrantyLengthMonths() {
        return warrantyLengthMonths;
    }
    
    }

