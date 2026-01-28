package com.bookInventory.demo.library.service;


import com.bookInventory.demo.library.exception.BookNotFoundException;
import com.bookInventory.demo.library.model.Book;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;


@Service
public class BookService {

    private final Map<Integer, Book> books = new ConcurrentHashMap<>();
    private final AtomicInteger counter = new AtomicInteger(1);

    public Book addBook(Book book) {
        int id = counter.getAndIncrement();
        book.setId(id);
        books.put(id, book);
        return book;
    }



    public Book getBook(int id) {
        Book book = books.get(id);
        if (book == null) {
            throw new BookNotFoundException("Book not found with id " + id);
        }
        return book;
    }

    public List<Book> getBooksByYear(int year) {
        List<Book> result = new ArrayList<>();
        for (Book book : books.values()) {
            if (book.getYear() == year) {
                result.add(book);
            }
        }
        return result;
    }

    public void deleteBook(int id) {
        if (!books.containsKey(id)) {
            throw new BookNotFoundException("Book not found with id " + id);
        }
        books.remove(id);
    }


}

