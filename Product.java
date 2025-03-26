import java.util.ArrayList;
import javax.swing.JOptionPane;
import java.util.HashMap;

//for features, consider using sets instead of arrayList because it doesn't allow for duplicates. 
//Adding elements to set: set.add(). Relate to input. 
//Ok so for the features array, create 2 parallel arrays: 1 with features, other with content. Relate content to index in features. 

/**
 * Write a description of class Product here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Product
{
    private String productID;
    private String productName;
    private String productDescription;
    private String productSupplier;
    private int productStock;
    private double productBasePrice;
    private boolean productStatus;
    
    /** Constructor for objects of class Product */
    public Product(String productID, String productName, String productDescription, String productSupplier, int productStock,
    double productBasePrice)
    {
        this.productID = productID;
        this.productName = productName;
        this.productDescription = productDescription;
        this.productSupplier = productSupplier;
        this.productStock = productStock;
        this.productBasePrice = productBasePrice;
        this.productStatus = true;
        
        System.out.println("Product Creation successfull!\n");
        System.out.println(this.toString());
        System.out.println("\n\n");
    }
    
    //WORKS
    @Override
    public String toString() {
        return "\nID: " + this.getProductID() + 
           " Name: " + this.getProductName() +
           " Description: " + this.getProductDescription() +
           " Supplier: " + this.getProductSupplier() + 
           " Stock: " + this.getProductStock() + 
           " Base Price: " + String.format("%.2f€", this.getProductBasePrice()) +
           " Status: " + this.getProductStatus();
}
    
    //getters
    public String getProductID() {
    return productID;
}

public String getProductName() {
    return productName;
}

public String getProductDescription() {
    return productDescription;
}

public String getProductSupplier() {
    return productSupplier;
}

public int getProductStock() {
    return productStock;
}

public double getProductBasePrice() {
    return productBasePrice;
}

public boolean getProductStatus() {
    return productStatus;
}

//setters
public void setProductID(String productID) {
    this.productID = productID;
}

public void setProductName(String productName) {
    this.productName = productName;
}

public void setProductDescription(String productDescription) {
    this.productDescription = productDescription;
}

public void setProductSupplier(String productSupplier) {
    this.productSupplier = productSupplier;
}

public void setProductStock(int newStock) {
    this.productStock = newStock;
}

public void setProductBasePrice(double productBasePrice) {
    this.productBasePrice = productBasePrice;
}

public void setProductStatus(boolean productStatus) {
    this.productStatus = productStatus;
}

}
