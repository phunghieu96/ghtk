package com.ghtk.quanlysv.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ghtk.quanlysv.model.Student;

import java.util.List;
import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student, String>{
    Optional<Student> findByMaHS(String s);

    @Query("select student from Student student where student.hoTen like %:hoTen%")
    List<Student> findByName(@Param("hoTen") String name);

    @Query("select student from Student student where student.maHS like %:maHS%")
    List<Student> findByIds(@Param("maHS") String maHS);
}
