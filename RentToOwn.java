import java.util.ArrayList;
import java.util.Date;

/** This class is similar to the lease class, but on the inverse. It has no deposit, but it revises monthly payments from the recurring
 * contract class upwards by applying an interest rate. Besides that, it has a similar constructor, its own version of the toString method,
 * and the implementation of the calculate total price abstract method. 
 * @author (Noah, Max)
 * @version (28/03/2025)
 */

public class RentToOwn extends RecurringContract {
    protected final double INTEREST_APPLIED = 0.3;
    protected double monthlyCostWithInterest;
    
    public RentToOwn(String contractID, ArrayList<Product> productSelection, String linkedAccount, int contractLengthMonths, Date contractDate) {
        super(contractID, productSelection, linkedAccount, contractLengthMonths, contractDate);
        setMonthlyCostWithInterest();
    }

    @Override
    public String toString() {
        return "\n\n=== Rent-To-Own Details ===" +
           "\nRent-To-OwnID: " + getContractID() +
           "\nMonthly RTO Cost: €" + String.format("%.2f", getMonthlyCostWithInterest()) +
           "\nRTO Start Date: " + getContractStartDate() +
           "\nRTO End Date: " + getContractEndDate() +
           "\nTotal RTO Cost: €" + String.format("%.2f",calculateTotalCost()) +
           "\nProduct(s) in Contract:" + getProductDetails() + "\n=== ============ ===\n";
    }
    
    
    public void setMonthlyCostWithInterest() {
        this.monthlyCostWithInterest = getMonthlyPayment() * (1+ INTEREST_APPLIED);
    }
    
    public double calculateTotalCost() {
        double totalCost = getMonthlyCostWithInterest() * contractLengthMonths;
        return totalCost;
    }
    
    public double getMonthlyCostWithInterest() {
        return this.monthlyCostWithInterest;
    }
}