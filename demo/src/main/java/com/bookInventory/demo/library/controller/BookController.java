package com.bookInventory.demo.library.controller;


import com.bookInventory.demo.library.model.Book;
import com.bookInventory.demo.library.service.BookService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    // POST /books
    @PostMapping
    public Book addBook(@RequestBody Book book) {
        return bookService.addBook(book);
    }

    // GET /books/{id}
    @GetMapping("/{id}")
    public Book getBook(@PathVariable int id) {
        return bookService.getBook(id);
    }

    // GET /books/search?year=2024
    @GetMapping("/search")
    public List<Book> searchByYear(@RequestParam int year) {
        return bookService.getBooksByYear(year);
    }

    // DELETE /books/{id}
    @DeleteMapping("/{id}")
    public String deleteBook(@PathVariable int id) {
        bookService.deleteBook(id);
        return "Book deleted successfully";
    }
}

