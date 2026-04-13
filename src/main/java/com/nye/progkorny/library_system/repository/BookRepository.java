package com.nye.progkorny.library_system.repository;

import com.nye.progkorny.library_system.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {
}
