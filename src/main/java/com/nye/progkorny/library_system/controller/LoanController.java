package com.nye.progkorny.library_system.controller;

import com.nye.progkorny.library_system.model.Loan;
import com.nye.progkorny.library_system.service.LoanService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/loans")
public class LoanController {

    private final LoanService loanService;

    public LoanController(LoanService loanService) {
        this.loanService = loanService;
    }

    @GetMapping
    public List<Loan> getAllLoans() {
        return loanService.getAllLoans();
    }

//    @PostMapping
//    public Loan createLoan(@RequestBody Map<String, Long> body) {
//        return loanService.createLoan(body.get("userId"), body.get("bookId"));
//    }
@PostMapping
public ResponseEntity<?> createLoan(@RequestBody Map<String, Long> body) {

    try {

        Loan loan = loanService.createLoan(
                body.get("userId"),
                body.get("bookId")
        );

        return ResponseEntity.ok("Sikeres kölcsönzés!");

    } catch (ResponseStatusException e) {

        return ResponseEntity
                .badRequest()
                .body(e.getReason());
    }
}

    @DeleteMapping("/{id}")
    public void deleteLoan(@PathVariable Long id) {
        loanService.deleteLoan(id);
    }



    @PostMapping("/return/{loanId}")
    public void returnBook(@PathVariable Long loanId) {
        loanService.returnBook(loanId);
    }
    @GetMapping("/user/{userId}")
    public List<Loan> getUserLoans(@PathVariable Long userId) {
        return loanService.getLoansByUser(userId);
    }
}