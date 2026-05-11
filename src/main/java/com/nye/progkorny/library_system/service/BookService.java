package com.nye.progkorny.library_system.service;

import com.nye.progkorny.library_system.model.Book;
import com.nye.progkorny.library_system.repository.BookRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    private final BookRepository repository;

    public BookService(BookRepository repository) {
        this.repository = repository;
    }

    public List<Book> getAll() {
        return repository.findAll();
    }

    public Book save(Book book) {
        return repository.save(book);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

    public Book createBook(Book book) {
        book.setAvailableCopies(book.getTotalCopies()); return repository.save(book);}

    public Book updateBook(Long id, Book updatedBook) {

        Book book = repository.findById(id).orElseThrow();

        book.setTitle(updatedBook.getTitle());
        book.setAuthor(updatedBook.getAuthor());
        book.setTotalCopies(updatedBook.getTotalCopies());

        return repository.save(book);
    }
}
