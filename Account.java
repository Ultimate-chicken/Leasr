import java.util.ArrayList;
import java.util.Date;


/** This class just keeps information about the account itself. It is does not store products or leases, as all of that is done within the
 * controller class. There is a field in each (abstract) contract which is called linkedAccount which is used to link contracts with 
 * accounts, but this class by itself only stores account details. @author (Noah, Max, Ahmada) @version (28/03/2025) */
public class Account
{
    private String accountID; 
    private String fullName;
    private String userEmail;
    private Date creationDate;

    public Account(String accountID, String fullName, String userEmail, Date creationDate) {
        this.accountID = accountID;
        this.fullName = fullName;
        this.userEmail = userEmail;
        this.creationDate = creationDate;
    }
    
    @Override
    public String toString() {
        return "Account details: \n\nAccount ID: " + getAccountID() +
                "\nFull Name: " + getFullName() +
                "\nEmail: " + getUserEmail() +
                "\nCreation Date: " + getCreationDate();
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
    
    // Setters
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }
  
} 