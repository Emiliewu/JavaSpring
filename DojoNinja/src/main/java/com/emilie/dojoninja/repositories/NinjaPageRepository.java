package com.emilie.dojoninja.repositories;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.emilie.dojoninja.models.Ninja;

@Repository
public interface NinjaPageRepository extends PagingAndSortingRepository<Ninja, Long> {
	
	Page<Ninja> findAll(PageRequest pageRequest);
	
	
	@Query("SELECT n, d FROM Ninja n JOIN n.dojo d ORDER BY d.name ASC")
	Page<Object[]> joinNinjasAndDojos(PageRequest pageRequest);
}
