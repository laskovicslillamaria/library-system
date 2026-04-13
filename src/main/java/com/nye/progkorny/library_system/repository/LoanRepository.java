package com.nye.progkorny.library_system.repository;

import com.nye.progkorny.library_system.model.Loan;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LoanRepository extends JpaRepository<Loan, Long> {
}
