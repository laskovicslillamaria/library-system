package com.nye.progkorny.library_system.service.service;

import com.nye.progkorny.library_system.model.Book;
import com.nye.progkorny.library_system.repository.BookRepository;
import com.nye.progkorny.library_system.service.BookService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class BookServiceTest {

    @Mock
    private BookRepository repository;

    @InjectMocks
    private BookService service;

    @Test
    void shouldReturnAllBooks() {
        Book book = new Book();
        book.setTitle("Harry Potter");

        when(repository.findAll()).thenReturn(List.of(book));

        List<Book> result = service.getAll();

        assertEquals(1, result.size());
        assertEquals("Harry Potter", result.get(0).getTitle());
    }
}
