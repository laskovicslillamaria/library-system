package com.nye.progkorny.library_system;

import com.nye.progkorny.library_system.model.Book;
import com.nye.progkorny.library_system.repository.BookRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {

    private final BookRepository repository;

    public DataLoader(BookRepository repository) {
        this.repository = repository;
    }

    @Override
    public void run(String... args) {
        if(repository.count() == 0) {
            repository.save(new Book(null,"Harry Potter","J.K. Rowling",true));
            repository.save(new Book(null,"A Gyűrűk Ura","J.R.R. Tolkien",true));
            repository.save(new Book(null,"Dűne","Frank Herbert",true));
            repository.save(new Book(null,"1984","George Orwell",true));
        }
    }
}