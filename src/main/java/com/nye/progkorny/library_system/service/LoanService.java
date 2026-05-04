package com.nye.progkorny.library_system.service;

import com.nye.progkorny.library_system.model.Book;
import com.nye.progkorny.library_system.model.Loan;
import com.nye.progkorny.library_system.model.User;
import com.nye.progkorny.library_system.repository.BookRepository;
import com.nye.progkorny.library_system.repository.LoanRepository;
import com.nye.progkorny.library_system.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class LoanService {

    private final LoanRepository loanRepository;
    private final UserRepository userRepository;
    private final BookRepository bookRepository;

    public LoanService(LoanRepository loanRepository,
                       UserRepository userRepository,
                       BookRepository bookRepository) {
        this.loanRepository = loanRepository;
        this.userRepository = userRepository;
        this.bookRepository = bookRepository;
    }

    public List<Loan> getAllLoans() {
        return loanRepository.findAll();
    }

    public Loan createLoan(Long userId, Long bookId) {

        User user = userRepository.findById(userId).orElseThrow();
        Book book = bookRepository.findById(bookId).orElseThrow();

        // 1 user ne vehesse ki ugyanazt a könyvet kétszer
        boolean alreadyBorrowed = loanRepository
                .existsByUserIdAndBookIdAndReturnedFalse(userId, bookId);

        if (alreadyBorrowed) {
            throw new RuntimeException("Már nálad van ez a könyv!");
        }

        //  nincs készleten
        if (book.getAvailableCopies() <= 0) {
            throw new RuntimeException("Nincs elérhető példány!");
        }

        // készletet csökkenés
        book.setAvailableCopies(book.getAvailableCopies() - 1);
        bookRepository.save(book);

        // kölcsönzés létrehozása
        Loan loan = new Loan();
        loan.setUser(user);
        loan.setBook(book);
        loan.setLoanDate(LocalDate.now());
        loan.setReturned(false);

        return loanRepository.save(loan);
    }

    public void deleteLoan(Long id) {
        loanRepository.deleteById(id);
    }

    public void returnBook(Long loanId) {

        Loan loan = loanRepository.findById(loanId).orElseThrow();

        if (loan.isReturned()) {
            return;
        }

        loan.setReturned(true);

        // készletet növelés
        Book book = loan.getBook();
        book.setAvailableCopies(book.getAvailableCopies() + 1);

        bookRepository.save(book);
        loanRepository.save(loan);
    }

    public List<Loan> getLoansByUser(Long userId) {
        return loanRepository.findByUserIdAndReturnedFalse(userId);
    }
}