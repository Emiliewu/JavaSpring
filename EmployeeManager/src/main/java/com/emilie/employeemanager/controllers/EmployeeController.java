package com.emilie.employeemanager.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.emilie.employeemanager.models.Employee;
import com.emilie.employeemanager.services.EmployeeService;

@RestController
public class EmployeeController {
	private final EmployeeService eservice;
	
	public EmployeeController(EmployeeService eservice) {
		this.eservice = eservice;
	}
	
	@RequestMapping("/") 
	public String findEmployee(){
		String str = "";
		List<Employee> employees = eservice.findAllEmployeeByManager((long) 1);
		for (Employee e: employees) {
			str = str + " " + e.getFirst_name().toString() + " " + e.getLast_name().toString() + "\n";
		}
		
		return str;
	}
}
