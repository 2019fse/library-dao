package com.fse.corejava.assignment.dao.repository;

import com.fse.corejava.assignment.dao.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    Set<Book> findByTitleIgnoreCaseContaining(String title);
    @Query(value = "SELECT b FROM Book b WHERE b.title like '%' || ?1 || '%'")
    Set<Book> findByTitle(String title);
}
