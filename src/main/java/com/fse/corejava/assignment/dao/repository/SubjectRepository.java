package com.fse.corejava.assignment.dao.repository;
import com.fse.corejava.assignment.dao.model.Subject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.Set;

@Repository
public interface SubjectRepository extends JpaRepository<Subject, Long> {
    Set<Subject> findBySubtitleIgnoreCaseContaining(String subtitle);
    @Query(value = "SELECT s FROM Subject s WHERE s.durationInHours = ?1")
    Set<Subject> findByDuration(int durationInHours);
}
