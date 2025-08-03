package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.time.LocalDate;

@RestController
@RequestMapping("/api/expenses")
public class ExpenseController {

    @Autowired
    private ExpenseRepository expenseRepository;

    // --- Core CRUD API Endpoints ---

    // GET /api/expenses - Get all expenses
    @GetMapping
    public List<Expense> getAllExpenses() {
        return expenseRepository.findAll();
    }

    // POST /api/expenses - Add a new expense
    @PostMapping
    public Expense createExpense(@RequestBody Expense expense) {
        if (expense.getDate() == null) {
            expense.setDate(LocalDate.now());
        }
        return expenseRepository.save(expense);
    }

    // GET /api/expenses/{id} - Get a single expense by ID
    @GetMapping("/{id}")
    public ResponseEntity<Expense> getExpenseById(@PathVariable Long id) {
        return expenseRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // PUT /api/expenses/{id} - Update an existing expense
    @PutMapping("/{id}")
    public ResponseEntity<Expense> updateExpense(@PathVariable Long id, @RequestBody Expense expenseDetails) {
        return expenseRepository.findById(id)
                .map(expense -> {
                    expense.setDescription(expenseDetails.getDescription());
                    expense.setAmount(expenseDetails.getAmount());
                    expense.setDate(expenseDetails.getDate());
                    expense.setCategory(expenseDetails.getCategory());
                    Expense updatedExpense = expenseRepository.save(expense);
                    return ResponseEntity.ok(updatedExpense);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    // DELETE /api/expenses/{id} - Delete an expense
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteExpense(@PathVariable Long id) {
        return expenseRepository.findById(id)
                .map(expense -> {
                    expenseRepository.delete(expense);
                    return ResponseEntity.ok().build();
                })
                .orElse(ResponseEntity.notFound().build());
    }

    // --- Reporting API Endpoints ---

    // GET /api/expenses/report/category - Get total expenses by category
    @GetMapping("/report/category")
    public List<CategoryTotal> getCategoryReport() {
        return expenseRepository.findCategoryTotals();
    }

    // GET /api/expenses/report/total - Get total expenses for all categories
    @GetMapping("/report/total")
    public Double getTotalReport() {
        return expenseRepository.findTotalAmount();
    }
}