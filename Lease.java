import java.util.ArrayList;
import java.util.Date;

/** The lease class has 2 core unique fields: adjustedMonthlyCost and depositAmount. Moreover, constants are used to define ratios. The 
 * mechanism in this class is as follows: First, the monthlyPayment from the recurringContract class is adjusted by multiplying the number
 * by a ratio (LEASE_PRICE_REDUCTION_MULTIPLIER), which is done because leases are often shorter than the product lifespan. Then, the 
 * deposit is calculated by multiplying the adjusted payment by the number of payments and then multiplying the result by a ratio. The deposit
 * is a lump sum payment, but it will be reinbursed later on. @author (Noah, Max) @version (28/03/2025) */
public class Lease extends RecurringContract {
    private final double LEASE_PRICE_REDUCTION_MULTIPLIER = 0.6;
    private  double LEASE_DOWN_PAYMENT = 0.2;
    protected double depositAmount;
    protected double adjustedMonthlyCost;
    
    public Lease(String contractID, ArrayList<Product> productSelection, String linkedAccount, int contractLengthMonths, Date contractDate) {
        super(contractID, productSelection, linkedAccount, contractLengthMonths, contractDate);
        setAdjustedMonthlyCost(); setDepositAmount();
        calculateTotalCost();
    }

    //As we can see, getters are used from the contract class, the recurringContract class, and the lease class. 
    @Override
    public String toString() {
        return "\n\n=== Lease Details ===" +
           "\nLease ID: " + getContractID() +
           "\nMonthly Lease Cost: €" + String.format("%.2f", getAdjustedMonthlyCost()) +
           "\nLease Start Date: " + getContractStartDate() +
           "\nLease End Date: " + getContractEndDate() +
           "\nDeposit Amount: €" + String.format("%.2f", getDepositAmount()) +
           "\nTotal Lease Cost: €" + String.format("%.2f", calculateTotalCost()) +
           "\nProduct(s) in Contract:" + getProductDetails() + "\n=== ========= ===\n";
    }
    
    /** This is the implementation of the calculateTotalCost() abstract method. It will get adjusted monthly cost and multiply by number
       of months, and then it will add the deposit amount. @param none @return total cost of the contract, from start to finish. */
    public double calculateTotalCost() {
        double totalCost = (getAdjustedMonthlyCost() * getContractLengthMonths()) + getDepositAmount();
        return totalCost;
    }

    //getters
        public double getDepositAmount() {
        return depositAmount;
    }
    
    public double getAdjustedMonthlyCost() {
        return adjustedMonthlyCost;
    }
    
    //setters
    public void setAdjustedMonthlyCost() {
        this.adjustedMonthlyCost = getMonthlyPayment() * LEASE_PRICE_REDUCTION_MULTIPLIER;
    }
    
    public void setDepositAmount() {
        this.depositAmount = ((getAdjustedMonthlyCost() * getContractLengthMonths()) * LEASE_DOWN_PAYMENT);
    }
}
