package com.example.lab05.service;

import com.example.lab05.model.Book;
import com.example.lab05.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {
    @Autowired
    private BookRepository bookRepository;

    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    public Optional<Book> getBookById(int id) {
        return bookRepository.findById(id);
    }

    public Book addBook(Book book) {
        return bookRepository.save(book);
    }

    public Book updateBook(int id, Book book) {
        Optional<Book> currentBook = bookRepository.findById(id);

        if (currentBook.isPresent()) {
            Book bookToUpdate = currentBook.get();
            bookToUpdate.setTitle(book.getTitle());
            bookToUpdate.setIsbn(book.getIsbn());
            bookToUpdate.setPrice(book.getPrice());

            return bookRepository.save(bookToUpdate);
        }

        return null;
    }

    public void deleteBook(int id) {
        bookRepository.deleteById(id);
    }

    public List<Book> findBooksByTitle(String title) {
        return bookRepository.findByTitleContainingIgnoreCase(title);
    }

    public List<Book> findBooksByPriceGreaterThan(double price) {
        return bookRepository.findByPriceGreaterThan(price);
    }

    public List<Book> findBooksByIsbn(String isbn) {
        return bookRepository.findByIsbn(isbn);
    }

    public List<Book> findBooksByTitleStartingWith(String prefix) {
        return bookRepository.findByTitleStartingWith(prefix);
    }
}
