package com.example.lab05.repository;

import com.example.lab05.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {
    List<Book> findByTitleContainingIgnoreCase(String title);

    List<Book> findByPriceGreaterThan(double price);

    List<Book> findByTitleStartingWith(String prefix);

    List<Book> findByIsbn(String isbn);
}
