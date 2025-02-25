package com.example.householdexpenses.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Movement {
    private LocalDateTime date;
    private String description;
    private double amount;
    private MovementType type; // This connects to your predefined types

    public Movement(LocalDateTime date, String description, double amount, MovementType type) {
        this.date = date;
        this.description = description;
        this.amount = amount;
        this.type = type;
    }

    // Getters and setters
    public LocalDateTime getDate() { return date; }
    public void setDate(LocalDateTime date) { this.date = date; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public double getAmount() { return amount; }
    public void setAmount(double amount) { this.amount = amount; }

    public MovementType getType() { return type; }
    public void setType(MovementType type) { this.type = type; }

    public ExpenseCategory getCategory() {
        return type.getCategory();
    }

    @Override
    public String toString() {
        return date + " | " + type.getDisplayName() + " | " + description + " | " + amount;
    }
}