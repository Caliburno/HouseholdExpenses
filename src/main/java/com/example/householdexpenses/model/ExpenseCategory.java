package com.example.householdexpenses.model;

public enum ExpenseCategory {

    INCOME("Income", true),
    FIXED_EXPENSES("Fixed Expenses", false),
    DEBT("Debt", false),
    VARIABLE_EXPENSES("Variable Expenses", false),
    SAVINGS("Savings", false);

    private final String displayName;
    private final Boolean isIncome;

    ExpenseCategory(String displayName, boolean isIncome) {
        this.displayName = displayName;
        this.isIncome = isIncome;
    }

    public boolean isIncome() {
        return isIncome;
    }

    public String getDisplayName() {
        return displayName;
    }
}
