package expense2;

import java.util.ArrayList;
import java.util.Scanner;

// Abstract class defining a basic loan structure
abstract class Loan {
    protected double purchasePrice;  // The total purchase price of the item (home or vehicle)
    protected double deposit;        // Initial down payment
    protected double interestRate;   // Annual interest rate for the loan
    protected int months;            // Loan duration in months

    // Constructor to initialize loan details
    public Loan(double purchasePrice, double deposit, double interestRate, int months) {
        this.purchasePrice = purchasePrice;   // Assign purchase price
        this.deposit = deposit;               // Assign deposit
        this.interestRate = interestRate;     // Assign interest rate
        this.months = months;                 // Assign loan term in months
    }

    // Abstract method to calculate the loan repayment amount, to be implemented by subclasses
    public abstract double getAmount();
}

// HomeLoan class extending Loan class to represent home loans
class HomeLoan extends Loan {
    // Constructor for HomeLoan, passing loan details to the parent class constructor
    public HomeLoan(double purchasePrice, double deposit, double interestRate, int months) {
        super(purchasePrice, deposit, interestRate, months);  // Call the parent Loan constructor
    }

    // Calculate the monthly repayment for the home loan
    public double getAmount() {
        double loanAmount = purchasePrice - deposit;  // Loan amount is purchase price minus deposit
        double monthlyInterestRate = interestRate / 100 / 12;  // Convert annual rate to monthly
        // Formula for calculating monthly repayment
        return (loanAmount * monthlyInterestRate) / (1 - Math.pow(1 + monthlyInterestRate, -months));
    }
}

// VehicleLoan class extending Loan class to represent vehicle loans
class VehicleLoan extends Loan {
    // Constructor for VehicleLoan with a fixed loan term of 60 months (5 years)
    public VehicleLoan(double purchasePrice, double deposit, double interestRate) {
        super(purchasePrice, deposit, interestRate, 60);  // Call parent Loan constructor with fixed months
    }

    // Calculate the monthly repayment for the vehicle loan
    public double getAmount() {
        double loanAmount = purchasePrice - deposit;  // Loan amount is purchase price minus deposit
        double monthlyInterestRate = interestRate / 100 / 12;  // Convert annual rate to monthly
        // Formula for calculating monthly repayment
        return (loanAmount * monthlyInterestRate) / (1 - Math.pow(1 + monthlyInterestRate, -months));
    }
}

public class Expense2 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);  // Scanner for user input
        ArrayList<Double> expenses = new ArrayList<>();  // List to store user expenses

        String[] expenseCategories = {  // Categories of expenses
            "Gross monthly income",
            "Monthly tax deductions",
            "Groceries",
            "Water and lights",
            "Travel costs",
            "Phone expenses",
            "Other expenses"
        };

        // Loop to gather user input for each expense category
        for (String category : expenseCategories) {
            System.out.print("Enter your " + category + ": ");
            expenses.add(scanner.nextDouble());  // Add the input to expenses list
        }

        double grossIncome = expenses.get(0);  // Gross monthly income is the first input
        double tax = expenses.get(1);  // Tax deductions are the second input
        double totalExpenses = 0;  // Initialize total expenses

        // Calculate total expenses (excluding gross income and tax)
        for (int i = 2; i < expenses.size(); i++) {
            totalExpenses += expenses.get(i);
        }

        // Ask the user if they are buying a property
        System.out.print("Are you renting or buying a property? (rent/buy): ");
        String accommodation = scanner.next();

        if (accommodation.equalsIgnoreCase("buy")) {
            // If buying a property, gather home loan details
            System.out.print("Enter the purchase price of the property: ");
            double purchasePrice = scanner.nextDouble();

            System.out.print("Enter the total deposit: ");
            double deposit = scanner.nextDouble();

            System.out.print("Enter the interest rate (%): ");
            double interestRate = scanner.nextDouble();

            // Create a HomeLoan object with a fixed term of 360 months (30 years)
            HomeLoan homeLoan = new HomeLoan(purchasePrice, deposit, interestRate, 360);
            double monthlyRepayment = homeLoan.getAmount();  // Calculate monthly repayment
            System.out.println("Monthly home loan repayment: " + monthlyRepayment);

            // Warn if the home loan repayment exceeds a third of the user's gross income
            if (monthlyRepayment > (grossIncome / 3)) {
                System.out.println("Warning: Home loan repayment is more than a third of your gross income.");
            }

            totalExpenses += monthlyRepayment;  // Add the repayment to total expenses
            expenses.add(monthlyRepayment);  // Add repayment to expenses list
        } else {
            // If renting, gather rental details
            System.out.print("Enter your monthly rental amount: ");
            double rent = scanner.nextDouble();
            totalExpenses += rent;  // Add rent to total expenses
            expenses.add(rent);  // Add rent to expenses list
        }

        // Ask the user if they are buying a vehicle
        System.out.print("Are you buying a vehicle? (yes/no): ");
        String buyVehicle = scanner.next();

        if (buyVehicle.equalsIgnoreCase("yes")) {
            // If buying a vehicle, gather vehicle loan details
            System.out.print("Enter the purchase price of the vehicle: ");
            double vehiclePurchasePrice = scanner.nextDouble();

            System.out.print("Enter the total deposit for the vehicle: ");
            double vehicleDeposit = scanner.nextDouble();

            System.out.print("Enter the interest rate for the vehicle (%): ");
            double vehicleInterestRate = scanner.nextDouble();

            System.out.print("Enter the estimated insurance premium: ");
            double insurancePremium = scanner.nextDouble();

            // Create a VehicleLoan object
            VehicleLoan vehicleLoan = new VehicleLoan(vehiclePurchasePrice, vehicleDeposit, vehicleInterestRate);
            double vehicleMonthlyRepayment = vehicleLoan.getAmount();  // Calculate monthly repayment

            // Calculate total monthly cost of the vehicle (loan + insurance)
            double totalVehicleCost = vehicleMonthlyRepayment + insurancePremium;
            System.out.println("Total monthly cost of buying the vehicle (loan + insurance): " + totalVehicleCost);

            totalExpenses += totalVehicleCost;  // Add vehicle costs to total expenses
            expenses.add(totalVehicleCost);  // Add total vehicle cost to expenses list
        }

        // Directly notify if total expenses exceed 75% of gross income
        notifyIfExpensesExceedThreshold(totalExpenses, grossIncome);

        // Calculate and display available money after deductions
        double availableMoney = grossIncome - tax - totalExpenses;
        System.out.println("Available monthly money after deductions: " + availableMoney);

        // Sort expenses in descending order manually
        System.out.println("Expenses in descending order:");
        for (int i = 0; i < expenses.size(); i++) {
            for (int j = i + 1; j < expenses.size(); j++) {
                if (expenses.get(i) < expenses.get(j)) {  // If current expense is smaller, swap
                    double temp = expenses.get(i);
                    expenses.set(i, expenses.get(j));
                    expenses.set(j, temp);
                }
            }
        }

        // Display sorted expenses
        for (double expense : expenses) {
            System.out.println(expense);  // Print each expense
        }

        scanner.close();  // Close the scanner to prevent resource leak
    }

    // Method to notify if total expenses exceed 75% of gross income
    private static void notifyIfExpensesExceedThreshold(double totalExpenses, double grossIncome) {
        if (totalExpenses > (grossIncome * 0.75)) {
            System.out.println("Warning: Total expenses exceed 75% of your gross income.");
        }
    }
}
