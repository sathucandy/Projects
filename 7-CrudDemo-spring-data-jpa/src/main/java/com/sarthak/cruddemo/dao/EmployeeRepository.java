package com.sarthak.cruddemo.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sarthak.cruddemo.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
	
	// thats it no need to write code
	
}
