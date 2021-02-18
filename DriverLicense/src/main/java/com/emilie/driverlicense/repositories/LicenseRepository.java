package com.emilie.driverlicense.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.emilie.driverlicense.models.License;

@Repository
@EnableJpaRepositories
public interface LicenseRepository extends CrudRepository<License, Long> {
	List<License> findAll();
//	List<License> findById(Long id);
	List<License> findByPerson(Long id);
}

