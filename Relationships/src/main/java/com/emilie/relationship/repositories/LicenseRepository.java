package com.emilie.relationship.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.emilie.relationship.models.License;

@Repository
public interface LicenseRepository extends CrudRepository<License, Long> {
	List<License> findAll();
	Optional<License> findById(Long id);
	Optional<License> findByPerson_id(Long id);
}
