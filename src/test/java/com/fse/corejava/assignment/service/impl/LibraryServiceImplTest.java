package com.fse.corejava.assignment.service.impl;

import com.fse.corejava.assignment.dao.model.Book;
import com.fse.corejava.assignment.dao.model.Subject;
import com.fse.corejava.assignment.dao.repository.BookRepository;
import com.fse.corejava.assignment.dao.repository.SubjectRepository;
import com.fse.corejava.assignment.exception.EntityNotFoundException;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Mockito.*;
@RunWith(MockitoJUnitRunner.class)
public class LibraryServiceImplTest {
    @InjectMocks
    private LibraryServiceImpl libraryService;
    @Mock
    private BookRepository bookRepository;
    @Mock
    private SubjectRepository subjectRepository;
    @Test
    public void addBook() {
        Book mockBook = mockBook();
        when(subjectRepository.getOne(anyLong())).thenReturn(mockBook.getSubject());
        when(bookRepository.save(any(Book.class))).thenReturn(mockBook);
        libraryService.addBook(1L, mockBook);
        verify(bookRepository, times(1)).save(any(Book.class));
        verify(subjectRepository, times(1)).getOne(anyLong());
    }

    @Test
    public void deleteBook() {
        when(bookRepository.exists(anyLong())).thenReturn(Boolean.TRUE);
        doNothing().when(bookRepository).delete(anyLong());
        libraryService.deleteBook(1L);
        verify(bookRepository, times(1)).exists(anyLong());
        verify(bookRepository, times(1)).delete(anyLong());
    }

    @Test(expected = EntityNotFoundException.class)
    public void deleteBookException() {
        when(bookRepository.exists(anyLong())).thenReturn(Boolean.FALSE);
        libraryService.deleteBook(1L);
        verify(bookRepository, times(1)).exists(anyLong());
    }

    @Test
    public void getAllBooks() {
        List<Book> books = new ArrayList<>();
        books.add(mockBook());
        when(bookRepository.findAll()).thenReturn(books);
        Set<Book> actualResult = libraryService.getAllBooks();
        Assert.assertEquals(books.size(), actualResult.size());
        verify(bookRepository, times(1)).findAll();
    }

    @Test
    public void getBook() {
        Book expectedResult = mockBook();
        when(bookRepository.exists(anyLong())).thenReturn(Boolean.TRUE);
        when(bookRepository.findOne(anyLong())).thenReturn(mockBook());
        Book actualResult = libraryService.getBook(1L);
        verify(bookRepository, times(1)).exists(anyLong());
        verify(bookRepository, times(1)).findOne(anyLong());
        Assert.assertEquals(expectedResult.getBookId(), actualResult.getBookId());
        Assert.assertEquals(expectedResult.getSubjectId(), actualResult.getSubjectId());
        Assert.assertEquals(expectedResult.getTitle(), actualResult.getTitle());
        Assert.assertEquals(expectedResult.getVolume(), actualResult.getVolume());
        Assert.assertEquals(expectedResult.getPrice(), actualResult.getPrice());
        Assert.assertEquals(expectedResult.getPublishDate(), actualResult.getPublishDate());
    }
    @Test(expected = EntityNotFoundException.class)
    public void getBookException() {
        when(bookRepository.exists(anyLong())).thenReturn(Boolean.FALSE);
        libraryService.getBook(1L);
        verify(bookRepository, times(1)).exists(anyLong());
    }

    private Book mockBook() {
        Book book = new Book(10L,"book title",new BigDecimal(100), 1, LocalDate.parse("2019-01-12"));
        Subject subject = new Subject();
        subject.setSubjectId(1L);
        book.setSubject(subject);
        return book;
    }
}