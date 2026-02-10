package com.auca.restfulapi.library.controller;

import com.auca.restfulapi.library.model.Book;
import com.auca.restfulapi.library.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/books")
public class BookController {
    
    @Autowired
    private BookRepository bookRepository;

    @GetMapping
    public ResponseEntity<List<Book>> getAllBooks() {
        return ResponseEntity.ok(bookRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable Long id) {
        return bookRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/search")
    public ResponseEntity<List<Book>> searchBooksByTitle(@RequestParam String title) {
        return ResponseEntity.ok(bookRepository.findByTitleContainingIgnoreCase(title));
    }

    @PostMapping
    public ResponseEntity<?> addBook(@RequestBody Book book) {
        if (bookRepository.findByTitleContainingIgnoreCase(book.getTitle()).stream()
                .anyMatch(b -> b.getTitle().equalsIgnoreCase(book.getTitle()))) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body(Map.of("error", "Book already exists", "title", book.getTitle()));
        }
        Book saved = bookRepository.save(book);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable Long id) {
        if (bookRepository.existsById(id)) {
            bookRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
