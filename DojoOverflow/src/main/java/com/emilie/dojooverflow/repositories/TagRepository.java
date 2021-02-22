package com.emilie.dojooverflow.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.emilie.dojooverflow.models.Tag;

@Repository
public interface TagRepository extends CrudRepository<Tag, Long>{
	List<Tag> findAll();
	boolean existsByTag(String t);
//	List<Tag> findByTagContainingIgnoreCase(String searchString);
	Optional<Tag> findByTagContaining(String searchString);

}
