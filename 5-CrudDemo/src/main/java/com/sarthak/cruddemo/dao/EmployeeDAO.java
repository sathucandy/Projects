package com.sarthak.cruddemo.dao;

import java.util.List;

import com.sarthak.cruddemo.entity.Employee;

public interface EmployeeDAO {

	public List<Employee> findAll();

}
