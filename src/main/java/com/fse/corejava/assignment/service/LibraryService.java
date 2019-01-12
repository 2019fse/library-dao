package com.fse.corejava.assignment.service;

import com.fse.corejava.assignment.dao.model.Book;
import com.fse.corejava.assignment.dao.model.Subject;

import java.util.Set;

public interface LibraryService {
    void addBook(Long subjectId, Book book);
    void deleteBook(Long bookId);
    Set<Book> getAllBooks();
    Book getBook(Long bookId);
}
