import java.util.ArrayList;
import java.util.Date;


/**
 * Write a description of class AccountClass here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Account
{
    private String accountID; 
    private String fullName;
    private String userEmail;
    private Date creationDate;
    private boolean isAdmin;
    private boolean isActive;

    /**
     * Constructor for objects of class AccountClass
     */
    public Account(String accountID, String fullName, String userEmail, Date creationDate) {
        this.accountID = accountID;
        this.fullName = fullName;
        this.userEmail = userEmail;
        this.creationDate = creationDate;
        this.isAdmin = isAdmin;
        this.isActive = true;
    }
    
    @Override
    public String toString() {
        return "Account details: \n\nAccount ID: " + getAccountID() +
                "\nFull Name: " + getFullName() +
                "\nEmail: " + getUserEmail() +
                "\nCreation Date: " + getCreationDate() +
                "\nAdmin: " + isAdmin() +
                "\nActive: " + isActive();
    }
    
    // Getters
    public String getAccountID() {
        return accountID;
    }

    public String getFullName() {
        return fullName;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public boolean isActive() {
        return isActive;
    }
    
    // Setters!
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public void setAdmin(boolean isAdmin) {
        this.isAdmin = isAdmin;
    }

    public void setActive(boolean isActive) {
        this.isActive = isActive;
    }
  
} 
