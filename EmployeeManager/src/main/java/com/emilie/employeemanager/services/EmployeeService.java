package com.emilie.employeemanager.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.emilie.employeemanager.models.Employee;
import com.emilie.employeemanager.repositories.EmployeeRepository;

@Service
public class EmployeeService {
	private final EmployeeRepository eRepo;
	
	public EmployeeService(EmployeeRepository eRepo) {
		this.eRepo = eRepo;
	}
//	● Implement a getter method that will allow a manager to get all their employees.
	public List<Employee> findAllEmployeeByManager(Long manager_id) {
		Optional<Employee> manager = eRepo.findById(manager_id);
		List<Employee> res;
		if (manager.isPresent()) {
			res = manager.get().getEmployees();
			for (Employee e: res) {
				System.out.println(e.toString());
			}
			return res;
		} else {
			return null;
		}
	}
	
//	● Implement a getter method that will allow an employee to get their manager.
	public Employee findManagerByEmployee(Long employee_id) {
		Optional<Employee> employee = eRepo.findById(employee_id);
		if (employee.isPresent()) {
			System.out.println(employee.get().getManager().toString());
			return employee.get().getManager();
		} else {
			return null;
		}
	}
	
}
