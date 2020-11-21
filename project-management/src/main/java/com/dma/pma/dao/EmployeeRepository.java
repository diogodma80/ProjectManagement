package com.dma.pma.dao;

import org.springframework.data.repository.CrudRepository;

import com.dma.pma.entities.Employee;

public interface EmployeeRepository extends CrudRepository<Employee, Long> {

}
