package com.fse.corejava.assignment.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fse.corejava.assignment.dao.model.Book;
import com.fse.corejava.assignment.dao.model.Subject;
import com.fse.corejava.assignment.service.LibraryService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.hamcrest.core.Is.is;
import static org.mockito.BDDMockito.given;
@RunWith(SpringRunner.class)
@WebMvcTest(value = LibraryRestController.class, secure = false)
public class LibraryRestControllerTest {
    @Autowired
    private MockMvc mvc;
    @MockBean
    private LibraryService libraryService;
    @Autowired
    private ObjectMapper mapper;
    @Test
    public void getAllBooks() throws Exception {
        Book mockBook = mockBook();
        Set<Book> mockBooks = new HashSet<>();
        mockBooks.add(mockBook);
        given(libraryService.getAllBooks()).willReturn(mockBooks);
        mvc.perform(get("/book")
                .contentType(APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].bookId", is(10)))
                .andExpect(jsonPath("$[0].subjectId", is(1)))
                .andExpect(jsonPath("$[0].title", is("book title")))
                .andExpect(jsonPath("$[0].price", is(100)))
                .andExpect(jsonPath("$[0].volume", is(1)))
                .andExpect(jsonPath("$[0].publishDate", is("2019-01-12")));
    }

    @Test
    public void getBook() throws Exception {
        Book mockBook = mockBook();
        given(libraryService.getBook(anyLong())).willReturn(mockBook);
        mvc.perform(get("/book/10")
                .contentType(APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("bookId", is(10)))
                .andExpect(jsonPath("subjectId", is(1)))
                .andExpect(jsonPath("title", is("book title")))
                .andExpect(jsonPath("price", is(100)))
                .andExpect(jsonPath("volume", is(1)))
                .andExpect(jsonPath("publishDate", is("2019-01-12")));
    }

    @Test
    public void addBook() throws Exception {
        Book mockBook = mockBook();
        doNothing().when(libraryService).addBook(anyLong(), any());
        mvc.perform(post("/book")
                .content(mapper.writeValueAsString(mockBook))
                .contentType(APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void updateBook() throws Exception {
        Book mockBook = mockBook();
        doNothing().when(libraryService).addBook(anyLong(), any());
        mvc.perform(put("/book")
                .content(mapper.writeValueAsString(mockBook))
                .contentType(APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void getAllBooksException() throws Exception {
        Book mockBook = mockBook();
        Set<Book> mockBooks = new HashSet<>();
        mockBooks.add(mockBook);
        given(libraryService.getAllBooks()).willThrow(new RuntimeException());
        mvc.perform(get("/book")
                .contentType(APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    public void getBookException() throws Exception {
        Book mockBook = mockBook();
        given(libraryService.getBook(anyLong())).willThrow(new RuntimeException());
        mvc.perform(get("/book/10")
                .contentType(APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    public void addBookException() throws Exception {
        Book mockBook = mockBook();
        doThrow(new RuntimeException()).when(libraryService).addBook(anyLong(), any());
        mvc.perform(post("/book")
                .content(mapper.writeValueAsString(mockBook))
                .contentType(APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    public void updateBookException() throws Exception {
        Book mockBook = mockBook();
        doThrow(new RuntimeException()).when(libraryService).addBook(anyLong(), any());
        mvc.perform(put("/book")
                .content(mapper.writeValueAsString(mockBook))
                .contentType(APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    private Book mockBook() {
        Book book = new Book(10L,"book title",new BigDecimal(100), 1, LocalDate.parse("2019-01-12"));
        Subject subject = new Subject();
        subject.setSubjectId(1L);
        book.setSubject(subject);
        return book;
    }
}
