import java.util.ArrayList;
import java.util.Date;

/**
 * Write a description of class AccountClass here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class AccountClass
{
    private String accountID;   
    private String accountType;
    private String user;
    private Date date;
    private boolean isVerified;
    private ArrayList<Contract> contractList;

    /**
     * Constructor for objects of class AccountClass
     */
    public AccountClass(String accountID, String user,String accountType)
    {
        Date today = new Date();
        this.accountID = accountID;
        this.user = user;
        this.date = today;
        this.accountType = accountType;
        this.isVerified = false;
    }
     public void addContract(Contract contract) {
        contractList.add(contract);
    }
     public String toString() {
        return "Account Details:\n" +
               "ID: " + accountID + "\n" +
               "User: " + user + "\n" +
               "Date created: " + date + "\n" +
               "accountType: " + accountType + "\n" +
               "isVerified: " + (isVerified ? "Verified" : "Unverified") + "\n" +
               "Total Contracts: " + contractList.size();
    }
    //Getters
        public String getAccountID() {
            return accountID;
        }
         public String getUser() {
            return user;
        }
         public String accountType() {
            return accountType;
        }
        public boolean getisVerified() {
            return isVerified;
        }
    //Setters
        public String setAccountID(String id) {
            this.accountID = id;
        }
         public String setUser(String user) {
            this.user = user;
        }
         public String setaccountType(String type) {
             this.accountType = type;
        }
         public boolean setisVerified(boolean verified) {
            this.isVerified = verified;
        }


  
}
