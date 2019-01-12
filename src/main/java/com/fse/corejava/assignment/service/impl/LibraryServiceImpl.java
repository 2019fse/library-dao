package com.fse.corejava.assignment.service.impl;
import com.fse.corejava.assignment.dao.model.Book;
import com.fse.corejava.assignment.dao.model.Subject;
import com.fse.corejava.assignment.dao.repository.BookRepository;
import com.fse.corejava.assignment.dao.repository.SubjectRepository;
import com.fse.corejava.assignment.exception.EntityNotFoundException;
import com.fse.corejava.assignment.service.LibraryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class LibraryServiceImpl implements LibraryService {
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private SubjectRepository subjectRepository;

    @Override
    public void addBook(Long subjectId, Book book) {
        Subject subject = subjectRepository.getOne(subjectId);
        book.setSubject(subject);
        bookRepository.save(book);
    }

    @Override
    public void deleteBook(Long bookId) {
        if(bookRepository.exists(bookId)) {
            bookRepository.delete(bookId);
        } else {
            throw new EntityNotFoundException();
        }

    }

    @Override
    public Set<Book> getAllBooks() {
        return new HashSet<>(bookRepository.findAll());
    }

    @Override
    public Book getBook(Long bookId) {
        if(bookRepository.exists(bookId)) {
            return bookRepository.findOne(bookId);
        } else {
            throw new EntityNotFoundException();
        }
    }

}
