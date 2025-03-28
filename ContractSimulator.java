import java.util.ArrayList;
import java.util.*;
import java.time.*;

/**
 * Write a description of class Simulation here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class ContractSimulator {
    
    private RecurringContract contract;
    private Clock simulationClock;

    public ContractSimulator(RecurringContract contract) {
        this.contract = contract;
        this.simulationClock = new Clock("Mon", 1, "Jan", 2025, 0, 0, 0);
    }

       public void simulateContractProgression() {
        try {
            while (contract.isActive()) {
                contract.advanceContractByMonth();

                displayContractStatus();

                Thread.sleep(1000);
            }
        } catch (InterruptedException e) {
        }
    }

    private void displayContractStatus() {
        System.out.println("\n===== Contract Simulation =====");
        System.out.println("Simulation Time: " + new Date());
        System.out.println("Contract ID: " + contract.getContractID());
        System.out.println("Contract Type: " + contract.getContractType());
        System.out.println("Remaining Months: " + contract.getRemainingMonths());
        System.out.println("Remaining Total Cost: " + String.format("%.2f€", contract.getRemainingTotalCost()));
        System.out.println("Contract Active: " + contract.isActive());
        System.out.println("===============================\n");
    }

    public static void main(String[] args) {
        ArrayList<String> products = new ArrayList<>();
        products.add("Laptop");

        Lease leaseContract = new Lease("LEASE001",products,"ACCOUNT123", 2000.0, "Gaming Laptop", 200.0, 10, 400.0);

        ContractSimulator simulator = new ContractSimulator(leaseContract);
        simulator.simulateContractProgression();
    }
}