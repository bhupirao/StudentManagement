package com.sms.repo;

import com.sms.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StudentRepo extends JpaRepository<Student,Long> {

    List<Student> findByName(String name);

    Optional<Student> findByUniqueStudentCode(String studentCode);


}
