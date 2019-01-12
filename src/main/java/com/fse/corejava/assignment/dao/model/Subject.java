package com.fse.corejava.assignment.dao.model;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "subject")
public class Subject {
    public Subject() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "subject_id")
    private Long subjectId;
    @Column(name = "sub_title")
    private String subtitle;
    @Column(name = "duration_in_hours")
    private Integer durationInHours;
    @JsonManagedReference
    @OneToMany(mappedBy = "subject", cascade = CascadeType.ALL)
    private Set<Book> references = new LinkedHashSet<>();

    public Long getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(Long subjectId) {
        this.subjectId = subjectId;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public Integer getDurationInHours() {
        return durationInHours;
    }

    public void setDurationInHours(Integer durationInHours) {
        this.durationInHours = durationInHours;
    }

    public Set<Book> getReferences() {
        return references;
    }

    public void setReferences(Set<Book> references) {
        this.references = references;
    }
}
