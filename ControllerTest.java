
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.Date;

/**
 * The test class ControllerTest.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class ControllerTest
{
    private Controller controller;

    /**
     * Default constructor for test class ControllerTest
     */
    public ControllerTest()
    {
    }

    /**
     * Sets up the test fixture.
     *
     * Called before every test case method.
     */
    @BeforeEach
    public void setUp() {
        controller = new Controller();

        Account testAccount = new Account("0001", "The Chosen One", "why@gg.ua", new Date());
        controller.accountList.add(testAccount);

        ArrayList<Product> testProducts = new ArrayList<>();
        Product testProduct1 = new Product("1001", "Nuclear Reactor", "Nuclear Item", "Life's Paradigm", 12, 10000);
        testProducts.add(testProduct1);

        Lease testLeaseContract = new Lease("9111", testProducts, "0001", 12, new Date());
        RentToOwn testRentToOwnContract = new RentToOwn("9112", testProducts, "0001", 24, new Date());

        controller.contractList.add(testLeaseContract);
        controller.contractList.add(testRentToOwnContract);
    }

    @Test
    public void testContractTotalCostCalculation() {
        //lease test
        Lease leaseContract = (Lease) controller.contractList.get(0);

        double leaseMonthlyPayment = leaseContract.getAdjustedMonthlyCost();
        double leaseTotalCost = leaseContract.calculateTotalCost();

        //total cost is: monthly cost * months + deposit
        assertEquals(leaseMonthlyPayment * 12 + leaseContract.getDepositAmount(), leaseTotalCost, 0.01);

        //rent-to-own twest
        RentToOwn rentToOwnContract = (RentToOwn) controller.contractList.get(1);

        double rentToOwnMonthlyPayment = rentToOwnContract.getMonthlyCostWithInterest();
        double rentToOwnTotalCost = rentToOwnContract.calculateTotalCost();

        //total cost is: monthly cost with interest * months
        assertEquals(rentToOwnMonthlyPayment * 24, rentToOwnTotalCost, 0.01);
    }

    @Test
    public void testContractTotalCostBreakdown() {
        //lease test
        Lease leaseContract = (Lease) controller.contractList.get(0);
        int leaseTermMonths = leaseContract.getContractLengthMonths();
        double leaseMonthlyPayment = leaseContract.getAdjustedMonthlyCost();
        double leaseDeposit = leaseContract.getDepositAmount();
        double expectedLeaseTotalCost = (leaseMonthlyPayment * leaseTermMonths) + leaseDeposit;

        assertEquals(expectedLeaseTotalCost, leaseContract.calculateTotalCost(), 0.01,"Lease total cost calculation mismatch.");
        assertEquals(leaseMonthlyPayment, leaseContract.getAdjustedMonthlyCost(), 0.01, "Lease monthly payment is incorrect.");
        assertEquals(leaseDeposit, leaseContract.getDepositAmount(), 0.01, "Lease deposit amount is incorrect.");

        //rent-to-Own test
        RentToOwn rentToOwnContract = (RentToOwn) controller.contractList.get(1);
        int rtoTermMonths = rentToOwnContract.getContractLengthMonths();
        double rtoMonthlyPaymentWithInterest = rentToOwnContract.getMonthlyCostWithInterest();
        double expectedRtoTotalCost = rtoMonthlyPaymentWithInterest * rtoTermMonths;

        assertEquals(expectedRtoTotalCost, rentToOwnContract.calculateTotalCost(), 0.01, "Rent-to-Own total cost calculation mismatch.");
        assertEquals(rtoMonthlyPaymentWithInterest, rentToOwnContract.getMonthlyCostWithInterest(), 0.01, "Rent-to-Own monthly payment with interest is incorrect.");
    }

    @Test
    public void testContractAccountAssociation() {
        Account testAccount = controller.accountList.get(0);
        String accountId = testAccount.getAccountID();

        ArrayList<Product> testProducts = new ArrayList<>();
        Product testProduct1 = new Product("2001", "Test Item", "A test product", "Test Supplier", 5, 50.0);
        testProducts.add(testProduct1);

        int initialContractCount = controller.contractList.size();

        Lease newLeaseContract = new Lease("L999", testProducts, accountId, 6, new Date());
        controller.contractList.add(newLeaseContract);

        RentToOwn newRentToOwnContract = new RentToOwn("R888", testProducts, accountId, 12, new Date());
        controller.contractList.add(newRentToOwnContract);

        int expectedContractCount = initialContractCount + 2;
        assertEquals(expectedContractCount, controller.contractList.size());

        assertEquals(accountId, newLeaseContract.getLinkedAccount());
        assertEquals(accountId, newRentToOwnContract.getLinkedAccount());

        int contractsForAccount = 0;
        for (Contract contract : controller.contractList) {
            if (contract.getLinkedAccount().equals(accountId)) {
                contractsForAccount++;
            }
        }
        assertEquals(4, contractsForAccount);
    }

}
