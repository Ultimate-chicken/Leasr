import java.util.ArrayList;

/** This class contains fields and methods regarding products that are included in leases. It includes a constructor, toString, getters,
 * and setters. The fields used are simple strings used to describe a product and a product price, which will be used to calculate total
 * contract cost. The most important field here is product ID, which is generated automatically when a product is created, but will be used
 * throughout the program in order to locate products within the productList arrayList (specifically, with the getProductIndex() method.
 * Two extra fields (stock and product status) can be utilised in the future to expand program functionalities by toggling/untoggling 
 * product status and by changing stock once a lease is executed.
 * @author (Noah, Max) @version (28/03/2025) */
public class Product
{
    private String productID;
    private String productName;
    private String productDescription;
    private String productSupplier;
    private double productBasePrice;
    
    //for the future
    private int productStock;
    private boolean productStatus;

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

        System.out.println("Product Creation successfull!");
        System.out.println(this.toString());
        System.out.println("\n\n");
    }

    @Override
    public String toString() {
        return "ID: " + this.getProductID() + 
        " Name: " + this.getProductName() +
        " Description: " + this.getProductDescription() +
        " Supplier: " + this.getProductSupplier() + 
        " Stock: " + this.getProductStock() + 
        " Cash Price: " + String.format("%.2f€", this.getProductBasePrice()) +
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
