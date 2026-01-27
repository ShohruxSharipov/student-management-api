package org.example.demoapplication.student;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public interface StudentRepository extends JpaRepository<StudentEntity,Long> {

    StudentEntity getStudentById(Long id);

        @Query("""
        SELECT s FROM StudentEntity s
        WHERE (:name IS NULL OR LOWER(s.name) LIKE LOWER(CONCAT('%', :name, '%')))
          AND (:minAge IS NULL OR s.age >= :minAge)
          AND (:maxAge IS NULL OR s.age <= :maxAge)
    """)
        List<StudentEntity> search(String name, Integer minAge, Integer maxAge);


    boolean existsById(Long id);

    void deleteById(Long id);
}
