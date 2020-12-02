package com.dma.pma.api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.dma.pma.entities.Employee;
import com.dma.pma.services.EmployeeService;

@RestController
@RequestMapping("/app-api/employees")
public class EmployeeApiController {

	@Autowired
	EmployeeService employeeService;

	@GetMapping
	public Iterable<Employee> getEmployees() {
		return employeeService.findAll();
	}
	
	@GetMapping("/{id}")
	public Employee getEmployeeById(@PathVariable("id") Long id) {
		return employeeService.findById(id);
	}
	
	@PostMapping(consumes = "application/json")
	@ResponseStatus(HttpStatus.CREATED)
	public Employee create(@RequestBody Employee employee) {
		return employeeService.save(employee);
	}
	
	@PutMapping(consumes = "application/json")
	@ResponseStatus(HttpStatus.OK)
	public Employee update(@RequestBody Employee employee) {
		return employeeService.save(employee);
	}
	
	@PatchMapping(path = "/{id}", consumes = "application/json")
	public Employee partialUpdate(@PathVariable("id") long id, @RequestBody Employee patchEmployee) {
		Employee employee = employeeService.findById(id);
		
		if(patchEmployee.getEmail() != null) {
			employee.setEmail(patchEmployee.getEmail());
		}
		if(patchEmployee.getFirstName() != null) {
			employee.setFirstName(patchEmployee.getFirstName());
		}
		if(patchEmployee.getLastName() != null) {
			employee.setLastName(patchEmployee.getLastName());
		}

		return employeeService.save(employee);
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable("id") Long id) {
		try {
			employeeService.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			//e.printStackTrace();
		}
		
	}
	
}
