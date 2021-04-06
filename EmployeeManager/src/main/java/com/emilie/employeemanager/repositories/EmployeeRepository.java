package com.emilie.employeemanager.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.emilie.employeemanager.models.Employee;

public interface EmployeeRepository extends CrudRepository<Employee, Long>{
	List<Employee> findAll();
	
	
}
