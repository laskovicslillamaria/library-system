package com.nye.progkorny.library_system.service;

import com.nye.progkorny.library_system.model.Book;
import com.nye.progkorny.library_system.model.Loan;
import com.nye.progkorny.library_system.model.User;
import com.nye.progkorny.library_system.repository.BookRepository;
import com.nye.progkorny.library_system.repository.LoanRepository;
import com.nye.progkorny.library_system.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
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

    @Test
    void shouldCreateLoan() {
        User user = new User();
        Book book = new Book();
        book.setAvailable(true);

        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        when(bookRepository.findById(1L)).thenReturn(Optional.of(book));
        when(loanRepository.save(org.mockito.ArgumentMatchers.any(Loan.class)))
                .thenAnswer(i -> i.getArgument(0));

        Loan result = service.createLoan(1L, 1L);

        assertNotNull(result);
        assertFalse(book.getAvailable());
    }
}
