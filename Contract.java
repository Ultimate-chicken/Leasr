//Add @override to abstract method. In abstract class: public abstract void methodName(); 
//In interfaces: public void methodName(); Every field in an interface is static and final. Also serve to implement common methods.
//JUnit testing: when a piece of code is tested by itself. For classes or methods. 
//for polymorphism: child extends parent. SUPER is used to call methods in the superclass and overide them on the child class.
//For example, super.makeNoise() calls the method from the parent class within the child class. 
//If you create a method in the super class and don't implement own version in child class, it will automatically get the super version.
//private methods in the superclass cannot be accessed with the super keyword by child classes. 
//in the constructor of subclasses, you can pass fields already constructed in the superclass with super(param1, param2). Only in constructor. Must be 1st line.
//As for products, because the array of products is in te controller, we will include references (getIndex) in the array of products of the contract subclasses.
//when you create a subclass, you get all fields and methods of the parent class. Polymorphism is about overiding methods in the parent class.
//overloading can be the same but with different paramters. 
//instanceof is to check the subclass. 


import java.util.ArrayList;

/**
 * Write a description of class Contract here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public abstract class Contract
{
    private String contractID;
    protected ArrayList<Product> productSelection;
    private Account linkedAccount;
    private double totalCost;
    private boolean isActive;
    
    /**
     * Constructor for objects of class Contract
     */
    public Contract(String contractID, ArrayList<Product> productSelection, Account linkedAccount)
    {
        this.contractID = contractID;
        this. productSelection = productSelection;
        this.linkedAccount = linkedAccount;
        
        for (Product product : productSelection) {
            this.totalCost += product.getProductBasePrice();
        }
        
        this.isActive = true;
    }
    
        @Override
    public String toString() {
        return "\nContract ID: " + getContractID() +
               "\nTotal Cost: " + getTotalCost() + "€" ;
    }

    public String getContractID() {
        return contractID;
    }
    
    public double getTotalCost() {
        return totalCost;
    }

    public boolean isActive() {
        return isActive;
    }
    
    public String getAccountDetails() {
        return linkedAccount.toString();
    }
    
    public String getAccountID() {
        return linkedAccount.getAccountID();
    }
    
    //OK. create arrayList of products and addProduct method in contract. Use a getIndex by using productID. 
}
