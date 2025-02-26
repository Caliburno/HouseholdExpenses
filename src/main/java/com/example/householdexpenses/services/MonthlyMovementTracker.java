package com.example.householdexpenses.services;
import com.example.householdexpenses.model.ExpenseCategory;
import com.example.householdexpenses.model.Movement;
import com.example.householdexpenses.model.MovementType;
import java.time.LocalDateTime;

public class MonthlyMovementTracker {
    // Array of arrays to store movements by month
    // Index is calculated as: (year-startYear)*12 + (month-1)
    private Movement[][] movementsByMonth;
    private int[] movementCountByMonth; // Tracks the number of movements in each month
    private final int MAX_MOVEMENTS_PER_MONTH = 200; // Maximum movements per month
    private final int YEAR_RANGE = 5; // Store data for 5 years
    private final int startYear; // First year we're tracking

    // Constructor
    public MonthlyMovementTracker(int startYear) {
        this.startYear = startYear;
        // Create arrays to hold movements for each month across multiple years
        int totalMonths = YEAR_RANGE * 12; // Total months we'll track
        this.movementsByMonth = new Movement[totalMonths][MAX_MOVEMENTS_PER_MONTH];
        this.movementCountByMonth = new int[totalMonths]; // Initialize counts to 0
    }

    // Helper method to calculate month index
    private int getMonthIndex(int year, int month) {
        return (year - startYear) * 12 + (month - 1);
    }

    // Add a new movement
    public void addMovement(MovementType type, double amount, LocalDateTime date, String description) {
        int year = date.getYear();
        int month = date.getMonthValue();

        // Calculate array index for this month
        int monthIndex = getMonthIndex(year, month);

        // Check if month is within our tracking range
        if (monthIndex < 0 || monthIndex >= movementsByMonth.length) {
            System.out.println("Error: Date out of tracking range.");
            return;
        }

        // Check if we have space for this month
        if (movementCountByMonth[monthIndex] < MAX_MOVEMENTS_PER_MONTH) {
            // Create the movement and add it to the array
            Movement movement = new Movement(type, amount, date, description);
            movementsByMonth[monthIndex][movementCountByMonth[monthIndex]] = movement;
            movementCountByMonth[monthIndex]++;
        } else {
            System.out.println("Error: Maximum number of movements for this month reached.");
        }
    }

    // Get all movements for a specific month
    public Movement[] getMovementsByMonth(int year, int month) {
        int monthIndex = getMonthIndex(year, month);

        // Check if month is within our tracking range
        if (monthIndex < 0 || monthIndex >= movementsByMonth.length) {
            return new Movement[0]; // Return empty array if out of range
        }

        // Create a new array with only the actual movements (no null entries)
        int count = movementCountByMonth[monthIndex];
        Movement[] result = new Movement[count];

        // Copy existing movements to the new array
        for (int i = 0; i < count; i++) {
            result[i] = movementsByMonth[monthIndex][i];
        }

        return result;
    }

    // Calculate total income for a specific month
    public double getMonthlyTotalIncome(int year, int month) {
        int monthIndex = getMonthIndex(year, month);

        // Check if month is within our tracking range
        if (monthIndex < 0 || monthIndex >= movementsByMonth.length) {
            return 0.0; // Return 0 if out of range
        }

        double total = 0.0;
        int count = movementCountByMonth[monthIndex];

        for (int i = 0; i < count; i++) {
            // Check if this movement is an income
            if (movementsByMonth[monthIndex][i].getCategory().isIncome()) {
                total += movementsByMonth[monthIndex][i].getAmount();
            }
        }

        return total;
    }

    // Calculate total expenses for a specific month
    public double getMonthlyTotalExpenses(int year, int month) {
        int monthIndex = getMonthIndex(year, month);

        // Check if month is within our tracking range
        if (monthIndex < 0 || monthIndex >= movementsByMonth.length) {
            return 0.0; // Return 0 if out of range
        }

        double total = 0.0;
        int count = movementCountByMonth[monthIndex];

        for (int i = 0; i < count; i++) {
            // Check if this movement is NOT an income
            if (!movementsByMonth[monthIndex][i].getCategory().isIncome()) {
                total += movementsByMonth[monthIndex][i].getAmount();
            }
        }

        return total;
    }

    // Calculate balance for a specific month
    public double getMonthlyBalance(int year, int month) {
        return getMonthlyTotalIncome(year, month) - getMonthlyTotalExpenses(year, month);
    }

    // Get monthly total by category
    public double getMonthlyCategoryTotal(int year, int month, ExpenseCategory category) {
        int monthIndex = getMonthIndex(year, month);

        // Check if month is within our tracking range
        if (monthIndex < 0 || monthIndex >= movementsByMonth.length) {
            return 0.0; // Return 0 if out of range
        }

        double total = 0.0;
        int count = movementCountByMonth[monthIndex];

        for (int i = 0; i < count; i++) {
            if (movementsByMonth[monthIndex][i].getCategory() == category) {
                total += movementsByMonth[monthIndex][i].getAmount();
            }
        }

        return total;
    }

    // Print a monthly summary report
    public void printMonthlySummaryReport(int year, int month) {
        System.out.println("=== FINANCIAL SUMMARY: " + year + "-" + month + " ===");
        System.out.println("Total Income: " + getMonthlyTotalIncome(year, month));
        System.out.println("Total Expenses: " + getMonthlyTotalExpenses(year, month));
        System.out.println("Balance: " + getMonthlyBalance(year, month));

        System.out.println("\n=== CATEGORY BREAKDOWN ===");
        // Print each expense category
        for (ExpenseCategory category : ExpenseCategory.values()) {
            double amount = getMonthlyCategoryTotal(year, month, category);
            // Only print if there's data
            if (amount > 0) {
                // Calculate percentage
                double percentage = 0.0;
                if (category.isIncome()) {
                    double totalIncome = getMonthlyTotalIncome(year, month);
                    if (totalIncome > 0) {
                        percentage = (amount / totalIncome) * 100;
                    }
                } else {
                    double totalExpenses = getMonthlyTotalExpenses(year, month);
                    if (totalExpenses > 0) {
                        percentage = (amount / totalExpenses) * 100;
                    }
                }

                System.out.println(category.getDisplayName() + ": " + amount +
                        " (" + String.format("%.1f", percentage) + "%)");

                // For each category, print its movement types
                for (MovementType type : MovementType.values()) {
                    // Only if this type belongs to the current category
                    if (type.getCategory() == category) {
                        double typeAmount = getMonthlyTypeTotal(year, month, type);
                        // Only print if there's a value for this type
                        if (typeAmount > 0) {
                            double typePercentage = 0.0;
                            if (amount > 0) {
                                typePercentage = (typeAmount / amount) * 100;
                            }
                            System.out.println("  - " + type.getDisplayName() + ": " + typeAmount +
                                    " (" + String.format("%.1f", typePercentage) + "%)");
                        }
                    }
                }
            }
        }
    }

    // Get monthly total by movement type
    public double getMonthlyTypeTotal(int year, int month, MovementType type) {
        int monthIndex = getMonthIndex(year, month);

        // Check if month is within our tracking range
        if (monthIndex < 0 || monthIndex >= movementsByMonth.length) {
            return 0.0; // Return 0 if out of range
        }

        double total = 0.0;
        int count = movementCountByMonth[monthIndex];

        for (int i = 0; i < count; i++) {
            if (movementsByMonth[monthIndex][i].getType() == type) {
                total += movementsByMonth[monthIndex][i].getAmount();
            }
        }

        return total;
    }
}