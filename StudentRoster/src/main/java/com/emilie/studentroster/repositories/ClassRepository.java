package com.emilie.studentroster.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.emilie.studentroster.models.Classmodel;

@Repository
public interface ClassRepository extends CrudRepository<Classmodel, Long>{
	List<Classmodel> findAll();
}
