package com.example.householdexpenses.model;

import java.time.LocalDate;

public class Expenses {
    private LocalDate date;
    private String description;
    private double amount;
    private ExpenseCategory category;

    public Expenses() {
    }

    public Expenses(LocalDate date, String description, double amount, ExpenseCategory category) {
        this.date = date;
        this.description = description;
        this.amount = amount;
        this.category = category;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public ExpenseCategory getCategory() {
        return category;
    }

    public void setCategory(ExpenseCategory category) {
        this.category = category;
    }
}
