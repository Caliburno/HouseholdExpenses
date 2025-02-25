package com.example.householdexpenses.model;

public enum MovementType {

    SALARY("Salary", ExpenseCategory.INCOME),
    BALANCE("Previous Month Balance", ExpenseCategory.INCOME),
    PROFESSIONAL_FEES("Professional Fees", ExpenseCategory.INCOME),
    RENT("Rent", ExpenseCategory.INCOME),
    VARIABLE_INCOME("Variable Income", ExpenseCategory.INCOME),
    DWELLING("Dwellings", ExpenseCategory.FIXED_EXPENSES),
    PUBLIC_SERVICES_AND_EXPENSES("Public Services And Expenses", ExpenseCategory.FIXED_EXPENSES),
    TELECOMMUNICATIONS("Telecommunications", ExpenseCategory.FIXED_EXPENSES),
    INSURANCE_AND_PENSION("Insurance And Pension", ExpenseCategory.FIXED_EXPENSES),
    SCHOOL("School", ExpenseCategory.FIXED_EXPENSES),
    SUBSCRIPTIONS_AND_MEMBERSHIPS("Subscriptions And Memberships", ExpenseCategory.FIXED_EXPENSES),
    VEHICLES("Vehicles", ExpenseCategory.FIXED_EXPENSES),
    TAXES_AND_ADMINISTRATIVE_EXPENSES("Taxes And Administrative Expenses", ExpenseCategory.FIXED_EXPENSES),
    FOOD("Food", ExpenseCategory.VARIABLE_EXPENSES),
    CLEANING_PRODUCTS("Cleaning Products", ExpenseCategory.VARIABLE_EXPENSES),
    TRANSPORT("Transport", ExpenseCategory.VARIABLE_EXPENSES),
    HEALTH_AND_WELLBEING("Health And Well-Being", ExpenseCategory.VARIABLE_EXPENSES),
    PERSONAL_CARE("Personal Care", ExpenseCategory.VARIABLE_EXPENSES),
    PETS("Pets", ExpenseCategory.VARIABLE_EXPENSES),
    MAINTENANCE("Maintenance", ExpenseCategory.VARIABLE_EXPENSES),
    EDUCATION_AND_STATIONERY("Education And Stationery", ExpenseCategory.VARIABLE_EXPENSES),
    ENTERTAINMENT_AND_LEISURE("Entertainment And Leisure", ExpenseCategory.VARIABLE_EXPENSES),
    EVENTUAL_PURCHASES("Eventual Purchases", ExpenseCategory.VARIABLE_EXPENSES),
    MISCELLANEOUS("Miscellaneous", ExpenseCategory.VARIABLE_EXPENSES),
    CREDIT_CARD("Credit Card", ExpenseCategory.DEBT),
    LOAN_INSTALLMENTS("Loan Installments", ExpenseCategory.DEBT),
    OTHER_DEBTS("Other Debts", ExpenseCategory.DEBT),
    SAVINGS("Savings And Investment", ExpenseCategory.SAVINGS);

    private final String displayName;
    private final ExpenseCategory category;

    MovementType(String displayName, ExpenseCategory category) {
        this.category = category;
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }

    public ExpenseCategory getCategory() {
        return category;
    }
}
