package com.example.householdexpenses.services;

import com.example.householdexpenses.model.Expenses;

import java.util.ArrayList;
import java.util.List;

public class ExpenseManager {

    private List<Expenses> expensesList;

    private void addExpense(Expenses expenses) {
        expensesList.add(expenses);
    }

}
