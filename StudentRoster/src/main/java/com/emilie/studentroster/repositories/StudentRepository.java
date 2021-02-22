package com.emilie.studentroster.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.emilie.studentroster.models.Student;

@Repository
public interface StudentRepository extends CrudRepository<Student, Long>{
	List<Student> findAll();
	Optional<Student> findById(Long id);
}
