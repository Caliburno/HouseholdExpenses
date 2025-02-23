package com.example.householdexpenses.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Movement {

    private final MovementType type;
    private final BigDecimal amount;
    private final LocalDateTime date;
    private String description;

    public Movement(MovementType type, BigDecimal amount, LocalDateTime date, String description) {
        this.type = type;
        this.amount = amount;
        this.date = date;
        this.description = description;
    }

    public ExpenseCategory get.Category() {
        return type.getCategory();
    }

    public boolean isIncome() {
        return type.getCategory().isIncome();
    }

}
