package com.example.lab05.controller;

import com.example.lab05.model.Book;
import com.example.lab05.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/books")
public class BookController {
    @Autowired
    private BookService bookService;

    @GetMapping
    public List<Book> getAllBooks() {
        return bookService.getAllBooks();
    }

    @GetMapping("/v1")
    public List<Book> getBooksV1() {
        return bookService.getAllBooks();
    }

    @GetMapping(params = "version=1")
    public List<Book> getBooksByVersionParam() {
        return bookService.getAllBooks();
    }

    @GetMapping(headers = "X-API-VERSION=2")
    public List<Book> getBooksByHeaderVersion() {
        return bookService.getAllBooks();
    }

    @GetMapping(params = {"id", "version=1"})
    public ResponseEntity<Book> getBookByIdV1(@RequestParam("id") int id) {
        Optional<Book> book = bookService.getBookById(id);
        if (book.isPresent()) {
            return ResponseEntity.ok(book.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping(value = "/{id}", produces = "application/cs.miu.edu-v2+json")
    public ResponseEntity<Book> getBookByIdV2(@PathVariable int id) {
        Optional<Book> book = bookService.getBookById(id);

        if (book.isPresent()) {
            return ResponseEntity.ok(book.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/search")
    public List<Book> getBooksByTitle(@RequestParam("title") String title) {
        return bookService.findBooksByTitle(title);
    }

    @GetMapping("/price")
    public List<Book> getBooksByPrice(@RequestParam("price") double price) {
        return bookService.findBooksByPriceGreaterThan(price);
    }

    @PostMapping
    public ResponseEntity<Book> createBook(@RequestBody Book book) {
        Book newBook = bookService.addBook(book);
        return ResponseEntity.ok(newBook);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Book> updateBook(@PathVariable("id") int id, @RequestBody Book book) {
        Book updatedBook = bookService.updateBook(id, book);
        if (updatedBook != null) {
            return ResponseEntity.ok(updatedBook);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable("id") int id) {
        bookService.deleteBook(id);
        return ResponseEntity.ok().build();
    }
}
