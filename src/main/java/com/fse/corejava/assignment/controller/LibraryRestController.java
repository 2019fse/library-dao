package com.fse.corejava.assignment.controller;

import com.fse.corejava.assignment.dao.model.Book;
import com.fse.corejava.assignment.exception.EntityNotFoundException;
import com.fse.corejava.assignment.exception.LibraryException;
import com.fse.corejava.assignment.service.LibraryService;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
public class LibraryRestController {
    private LibraryService libraryService;

    public LibraryRestController(LibraryService libraryService) {
        this.libraryService = libraryService;
    }

    @PostMapping("/book")
    public void addBook(@RequestBody Book book) {
        try {
            libraryService.addBook(book.getSubjectId(), book);
        } catch (Exception e) {
            throw new LibraryException("Error in adding book.");
        }
    }

    @PutMapping(value = "/book")
    public void updateBook(@RequestBody Book book) {
        try {
            libraryService.addBook(book.getSubjectId(), book);
        } catch (Exception e) {
            throw new LibraryException("Error in updating book.");
        }
    }

    @DeleteMapping("/book/{bookId}")
    public void deleteBook(@PathVariable Long bookId) {
        try {
            libraryService.deleteBook(bookId);
        } catch (EntityNotFoundException e) {
            throw new LibraryException("Book not found.");
        }
        catch (Exception e) {
            throw new LibraryException("Error in deleting book.");
        }
    }

    @GetMapping("/book")
    public Set<Book> getAllBooks() {
        try {
            return libraryService.getAllBooks();
        } catch (Exception e) {
            throw new LibraryException("Error in getting all books.");
        }
    }

    @GetMapping("/book/{bookId}")
    public Book getBook(@PathVariable Long bookId) {
        try {
            return libraryService.getBook(bookId);
        } catch (EntityNotFoundException e) {
            throw new LibraryException("Book not found.");
        }
        catch (Exception e) {
            throw new LibraryException("Error in getting book.");
        }
    }
}
