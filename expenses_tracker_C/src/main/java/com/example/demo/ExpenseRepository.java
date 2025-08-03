package com.example.demo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.time.LocalDate;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import com.example.demo.CategoryTotal;

@Repository
public interface ExpenseRepository extends JpaRepository<Expense, Long> {
    
    // Find all expenses for a specific category
    List<Expense> findByCategory(String category);

    // Find all expenses within a specific date range
    List<Expense> findByDateBetween(LocalDate startDate, LocalDate endDate);

    @Query("SELECT new com.example.demo.CategoryTotal(e.category, SUM(e.amount)) FROM Expense e GROUP BY e.category")
    List<CategoryTotal> findCategoryTotals();

    @Query("SELECT SUM(e.amount) FROM Expense e")
    Double findTotalAmount();
}