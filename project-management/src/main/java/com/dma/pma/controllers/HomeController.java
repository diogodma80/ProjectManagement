package com.dma.pma.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.dma.pma.dao.EmployeeRepository;
import com.dma.pma.dao.ProjectRepository;
import com.dma.pma.entities.Employee;
import com.dma.pma.entities.Project;

@Controller
public class HomeController {
	
	@Autowired
	ProjectRepository projectRepository;
	
	@Autowired
	EmployeeRepository employeeRepository;

	@GetMapping("/")
	public String displayHome(Model model) {
		
		// findAll is overriden in the ProjectRepository so that a list 
		// of Projects is returned instead of Iterable
		List<Project> projects =  projectRepository.findAll();
		List<Employee> employees = employeeRepository.findAll();
		
		model.addAttribute("projects", projects);
		model.addAttribute("employees", employees);
		
		return "main/home";
	}
}
