package com.nye.progkorny.library_system.repository;

import com.nye.progkorny.library_system.model.Loan;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LoanRepository extends JpaRepository<Loan, Long> {

    boolean existsByUserIdAndBookIdAndReturnedFalse(Long userId, Long bookId);
    List<Loan> findByUserIdAndReturnedFalse(Long userId);

}
