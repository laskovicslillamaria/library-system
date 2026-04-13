package com.nye.progkorny.library_system.controller;

import com.nye.progkorny.library_system.model.Loan;
import com.nye.progkorny.library_system.service.LoanService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/loans")
public class LoanController {

    private final LoanService service;

    public LoanController(LoanService service) {
        this.service = service;
    }

    @GetMapping
    public List<Loan> getAllLoans() {
        return service.getAllLoans();
    }

    @PostMapping
    public Loan createLoan(@RequestBody Map<String, Long> body) {
        return service.createLoan(body.get("userId"), body.get("bookId"));
    }

    @DeleteMapping("/{id}")
    public void deleteLoan(@PathVariable Long id) {
        service.deleteLoan(id);
    }
}