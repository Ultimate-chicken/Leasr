import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Date;
import java.util.Random;

//Note: the view class should handle all input and output (print, scanner

/**
 * Write a description of class ControllerClass here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class ControllerClass
{
    //arrayLists
    public ArrayList<Contract> contractList;
    public ArrayList<Product> productList;
    Random random = new Random();
    private ArrayList<Account> accountList;

    //constructor
    public ControllerClass()
    {
        contractList = new ArrayList<Contract>();
        productList = new ArrayList<Product>();

        createTestProducts();
    }

    //WORKS
    public void createTestProducts() {

        Product product1 = new Product("1001", "Refrigerator", "Large, stainless steel fridge", "House of Rising Sun", 25, 800);
        productList.add(product1);

        Product product2 = new Product("1002", "Washing Machine", "High-efficiency washer", "Extron", 30, 601);
        productList.add(product2);

        Product product3 = new Product("1003", "Dishwasher", "Quiet, energy-saving dishwasher", "Life's Paradigm", 15, 451);
        productList.add(product3);

        Product product4 = new Product("1004", "Microwave Oven", "Countertop microwave with sensor cooking", "Nexus", 50, 150);
        productList.add(product4);

        Product product5 = new Product("1005", "Sofa", "Comfortable three-seater sofa", "Leasr.", 10, 900);
        productList.add(product5);

        Product product6 = new Product("1006", "Dining Table", "Solid wood dining table for six", "Minecraft", 8, 701);
        productList.add(product6);

        Product product7 = new Product("1007", "Bed Frame", "Queen-size platform bed frame", "Brave New World", 20, 550);
        productList.add(product7);

        Product product8 = new Product("1008", "Bookshelf", "Tall, wooden bookshelf with adjustable shelves", "Rim of Sky", 35, 201);
        productList.add(product8);

        Product product9 = new Product("1009", "Toaster", "2 slice toaster, stainless steel", "Midlife Crisis", 60, 35);
        productList.add(product9);

        Product product10 = new Product("1010", "Blender", "High powered blender", "Kokot", 40, 100);
        productList.add(product10);
    }

    public void showCatalogue() {
        System.out.println("Catalogue of all products");
        for (Product productObject : productList) {
            System.out.println(productObject.toString());
            System.out.println();
        }
    }

    public void showStockReport() {
        System.out.println("\fCurrent stock levels for all products (Name/ID: Stock):\n");
        for (Product productObject : productList) {
            System.out.println(productObject.getProductName() + "/" + productObject.getProductID() + ": " + productObject.getProductStock());
        }
    }

    public int getProductIndex(String productID) {
        for(int arrayIndex = 0; arrayIndex<productList.size(); arrayIndex++) {
            if(productList.get(arrayIndex).getProductID().equals(productID)) {
                return arrayIndex;
            }
        }

        System.out.printf("Product ID %s was not found in our database.", productID);
        return -1;
    }
}

//SOURCES
//https://www.youtube.com/watch?v=H62Jfv1DJlU
//https://www.youtube.com/watch?v=QvHBHuuddYk
//https://www.youtube.com/watch?v=HvPlEJ3LHgE&list=WL&index=3
//https://www.youtube.com/watch?v=Qb_NUn0TSAU&list=WL&index=1
//https://www.youtube.com/watch?v=jhDUxynEQRI&list=WL&index=2 
