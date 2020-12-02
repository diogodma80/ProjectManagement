package com.dma.pma.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.dma.pma.entities.Employee;
import com.dma.pma.services.EmployeeService;

@Controller
@RequestMapping("/employees")
public class EmployeeController {
	
	@Autowired
	EmployeeService employeeService;
	
	@GetMapping("/new")
	public String displayEmployeeForm(Model model) {
		
		// bind the employee object to the model and send it over to the new-employee.html
		model.addAttribute("employee", new Employee());
		
		return "employees/new-employee";
		
	}
	
	@PostMapping("/save")
	public String createEmployee(Employee employee, Model model) {
		
		// save to the database using the crud repository
		employeeService.save(employee);
		
		return "redirect:/employees";
		
	}
	
	@GetMapping
	public String displayEmployees(Model model) {
		Iterable<Employee> employees = employeeService.findAll();
		
		model.addAttribute("employees", employees);
		
		return "employees/list-employees";
	}

}
