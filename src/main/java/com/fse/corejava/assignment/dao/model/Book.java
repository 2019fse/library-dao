package com.fse.corejava.assignment.dao.model;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;
import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
@Entity
@Table(name = "book")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "book_id")
    private Long bookId;
    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "subject_id")
    private Subject subject = new Subject();
    @Column(name = "title")
    private String title;
    @Column(name = "price")
    private BigDecimal price;
    @Column(name = "volume")
    private Integer volume;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Column(name = "publish_date")
    private LocalDate publishDate;

    public Book() {
    }

    public Book(Long bookId, String title, BigDecimal price, Integer volume, LocalDate publishDate) {
        this.bookId = bookId;
        this.title = title;
        this.price = price;
        this.volume = volume;
        this.publishDate = publishDate;
    }

    public Long getBookId() {
        return bookId;
    }

    public void setBookId(Long bookId) {
        this.bookId = bookId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getVolume() {
        return volume;
    }

    public void setVolume(Integer volume) {
        this.volume = volume;
    }

    public LocalDate getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(LocalDate publishDate) {
        this.publishDate = publishDate;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public Long getSubjectId() {
        return subject.getSubjectId();
    }

    public void setSubjectId(Long subjectId) {
        subject.setSubjectId(subjectId);
    }


}
