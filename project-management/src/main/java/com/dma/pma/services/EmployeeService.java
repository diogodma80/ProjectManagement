package com.dma.pma.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.dma.pma.dao.EmployeeRepository;
import com.dma.pma.dto.EmployeeProject;
import com.dma.pma.entities.Employee;

@Service
public class EmployeeService {

	@Autowired
	EmployeeRepository employeeRepository;

	public Employee save(Employee employee) {
		return employeeRepository.save(employee);
	}

	public Iterable<Employee> findAll() {
		return employeeRepository.findAll();
	}

	public List<EmployeeProject> employeeProjects() {
		return employeeRepository.employeeProjects();
	}

	public Employee findById(Long id) {
		return employeeRepository.findById(id).get();
	}

	public void deleteById(Long id) {
		employeeRepository.deleteById(id);
		
	}

	public Employee findByEmail(String value) {
		return employeeRepository.findByEmail(value);
	}

	public Iterable<Employee> findAll(Pageable pageAndSize) {
		return employeeRepository.findAll(pageAndSize);
	}

	public Employee findByEmployeeId(long id) {
		return employeeRepository.findByEmployeeId(id);
	}

	public void delete(Employee employee) {
		employeeRepository.delete(employee);
	}

}
