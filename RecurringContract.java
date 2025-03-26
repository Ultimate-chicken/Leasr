import java.util.ArrayList;
import java.util.Date;

/**
 * Write a description of class RecurringContract here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public abstract class RecurringContract extends Contract
{
    private String endDate; 
    private double monthlyCost;
    private int ContractDurationMonths;
    private boolean isContractActive;
    
    public RecurringContract(String contractID, Date purchaseDate, double totalCost, ArrayList<String> productSelection, String linkedAccount)
    {
        super(contractID, purchaseDate, totalCost, productSelection, linkedAccount);
        
        monthlyCost = totalCost / ContractDurationMonths;
        isContractActive = true;
    }

}
