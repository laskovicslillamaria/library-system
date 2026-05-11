package com.nye.progkorny.library_system.service.service;

import com.nye.progkorny.library_system.model.Book;
import com.nye.progkorny.library_system.model.Loan;
import com.nye.progkorny.library_system.model.User;
import com.nye.progkorny.library_system.repository.BookRepository;
import com.nye.progkorny.library_system.repository.LoanRepository;
import com.nye.progkorny.library_system.repository.UserRepository;
import com.nye.progkorny.library_system.service.LoanService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class LoanServiceTest {

    @Mock
    private LoanRepository loanRepository;

    @Mock
    private UserRepository userRepository;

    @Mock
    private BookRepository bookRepository;

    @InjectMocks
    private LoanService service;

 // Ha nincs készleten a könyv
    @Test
    void shouldThrowExceptionWhenNoCopiesAvailable() {

        User user = new User();

        Book book = new Book();
        book.setTotalCopies(3);
        book.setAvailableCopies(0);

        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        when(bookRepository.findById(1L)).thenReturn(Optional.of(book));

        when(loanRepository.existsByUserIdAndBookIdAndReturnedFalse(1L, 1L))
                .thenReturn(false);

        RuntimeException exception = assertThrows(
                RuntimeException.class,
                () -> service.createLoan(1L, 1L)
        );

        assertEquals("Nincs elérhető példány!", exception.getMessage());
    }

// Már ki van kölcsönözve
    @Test
    void shouldThrowExceptionWhenUserAlreadyBorrowedBook() {

        User user = new User();

        Book book = new Book();
        book.setTotalCopies(3);
        book.setAvailableCopies(3);

        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        when(bookRepository.findById(1L)).thenReturn(Optional.of(book));

        when(loanRepository.existsByUserIdAndBookIdAndReturnedFalse(1L, 1L))
                .thenReturn(true);

        RuntimeException exception = assertThrows(
                RuntimeException.class,
                () -> service.createLoan(1L, 1L)
        );

        assertEquals("Már nálad van ez a könyv!", exception.getMessage());
    }
//Könyv visszahozás
    @Test
    void shouldReturnBook() {

        Book book = new Book();
        book.setAvailableCopies(1);

        Loan loan = new Loan();
        loan.setBook(book);
        loan.setReturned(false);

        when(loanRepository.findById(1L))
                .thenReturn(Optional.of(loan));

        service.returnBook(1L);

        assertEquals(2, book.getAvailableCopies());
    }
// Sikeres kölcsönzés
    @Test
    void shouldCreateLoan() {

        User user = new User();

        Book book = new Book();
        book.setTotalCopies(3);
        book.setAvailableCopies(3);

        when(userRepository.findById(1L))
                .thenReturn(Optional.of(user));

        when(bookRepository.findById(1L))
                .thenReturn(Optional.of(book));

        when(loanRepository.existsByUserIdAndBookIdAndReturnedFalse(1L, 1L))
                .thenReturn(false);

        when(loanRepository.save(org.mockito.ArgumentMatchers.any(Loan.class)))
                .thenAnswer(i -> i.getArgument(0));

        Loan result = service.createLoan(1L, 1L);

        assertNotNull(result);

        assertEquals(2, book.getAvailableCopies());
    }

}