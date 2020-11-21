package com.dma.pma.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.dma.pma.entities.Employee;

public interface EmployeeRepository extends CrudRepository<Employee, Long> {

	@Override
	List<Employee> findAll();
}
