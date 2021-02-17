package com.emilie.lookify.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.emilie.lookify.models.Lookify;

@Repository
public interface LookifyRepository extends CrudRepository<Lookify, Long>{
	List<Lookify> findAll();
	List<Lookify> findTop10ByOrderByRatingDesc();
	List<Lookify> findByArtistContainingIgnoreCase(String searchString);
}
