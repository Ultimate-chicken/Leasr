import java.util.ArrayList;
import java.util.Date;
import java.util.Calendar;

/** Another abstract class, but it is a subclass of contract. It includes all fields from contract plus fields that can be applicable to 
 * both lease and rent to own. All recurring contracts have a monthly payment (total/contract length) plus a start/end date of the contract.
    @author (Noah, Max) @version (28/03/2025) */

public abstract class RecurringContract extends Contract {
    protected double monthlyPayment;
    protected Date contractStartDate;
    protected Date contractEndDate;
    protected int contractLengthMonths;
    
    /** These fields are instantiated every time a recurringContract is made. The parameter list looks complex, but it is just getting
       fields from the contract class plus contractLengtMonths. We use the super() operator to pass parameters from the contract class,
       and the other fields are calculated locally (contract end date and monthlyPayment). @param contractDate is the date the contract
       was executed, and we take it as the starting date. @return none. */
    public RecurringContract(String contractID, ArrayList<Product> productSelection, String linkedAccount, int contractLengtMonths, Date contractDate) {
        super(contractID, productSelection, linkedAccount);
        this.contractStartDate = contractDate;
        this.contractLengthMonths = contractLengtMonths;
        this.contractEndDate = getContractEndDate();
    protected int remainingMonths;
    protected double remainingTotalCost;
    
    public RecurringContract(String contractID, ArrayList<String> productSelection, String linkedAccount, double totalCartCost, String productDetails, int contractLengthMonths) {
        super(contractID, productSelection, linkedAccount, totalCartCost, productDetails);
        this.contractLengthMonths = contractLengthMonths;
        this.remainingMonths = contractLengthMonths;
        this.remainingTotalCost = totalCartCost;
    }

    
    /** This method calculates total cost from the array list of product objects and then divides by the length of the contract that the
       user selected. This serves as the basis for the subclasses, as monthly payments are adjusted downwards for lease and upwards for
       rent to own. @param none @return monthly payment. Basis for further calculations. */
    public double getMonthlyPayment() {
        double runningTotal = 0;
        
        for (Product productObject : productSelection) {
            runningTotal += productObject.getProductBasePrice();
    public double getRemainingAmount(int monthsPaid) {
        if (monthsPaid < 0 || monthsPaid > contractLengthMonths) {
            //add some comment or smthn
        }
        
        monthlyPayment = runningTotal / contractLengthMonths;
        return monthlyPayment;
    }
    
    // This method calculates the end of the lease/rent to own contract using calendar and date classes. It returns the output.
    public Date getContractEndDate() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(contractDate);
        calendar.add(Calendar.MONTH, contractLengthMonths);
        return calendar.getTime();
    }

    public int getContractLengthMonths() {
        return contractLengthMonths;
    }
    
    public Date getContractStartDate() {
        return contractStartDate;
    /*public double getDepositAmount() {
        return depositAmount;
    }*/

    public void advanceContractByMonth() {
        if (remainingMonths > 0) {
            remainingMonths--;

            remainingTotalCost -= monthlyPayment;

            if (remainingMonths == 0) {
                //isActive = false;
            }
        }
    }

    public int getRemainingMonths() {
        return remainingMonths;
    }

    // Getter for remaining total cost
    public double getRemainingTotalCost() {
        return remainingTotalCost;
    }
}
