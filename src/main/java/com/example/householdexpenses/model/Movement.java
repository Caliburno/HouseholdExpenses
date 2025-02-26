package com.example.householdexpenses.model;

import java.time.LocalDateTime;

public class Movement {
    private MovementType type; // This connects to your predefined types
    private double amount;
    private LocalDateTime date;
    private String description;



    public Movement(MovementType type, double amount, LocalDateTime date, String description) {
        this.type = type;
        this.amount = amount;
        this.date = date;
        this.description = description;

    }

    // Getters and setters
    public MovementType getType() { return type; }
    public void setType(MovementType type) { this.type = type; }

    public double getAmount() { return amount; }
    public void setAmount(double amount) { this.amount = amount; }

    public LocalDateTime getDate() { return date; }
    public void setDate(LocalDateTime date) { this.date = date; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public ExpenseCategory getCategory() {
        return type.getCategory();
    }
}