package com.dma.pma.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.dma.pma.dao.EmployeeRepository;
import com.dma.pma.dao.ProjectRepository;
import com.dma.pma.entities.Employee;
import com.dma.pma.entities.Project;

@Controller
@RequestMapping("/projects")
public class ProjectController {
	
	@Autowired
	ProjectRepository projectRepository;
	
	@Autowired
	EmployeeRepository employeeRepository;

	@GetMapping("/new")
	public String displayProjectForm(Model model) {
		// fbind an entity project to the form using the model.addAttribute method
		Project project = new Project();
		
		// get the list of employees and send it over to the new-project page
		List<Employee> employees = employeeRepository.findAll();
		
		model.addAttribute("project", project);
		model.addAttribute("employees", employees);
		return "projects/new-project";
	}
	
	@PostMapping("/save")
	public String createProject(Project project, Model model, @RequestParam List<Long> employees) { 
		// this method should handle saving to the database
		projectRepository.save(project);
		
		// manually save the employee ids linked to the project
		Iterable<Employee> chosenEmployees = employeeRepository.findAllById(employees);
		for(Employee employee : chosenEmployees) {
			employee.setProject(project);
			employeeRepository.save(employee);
		}
		
		// use a redirect to prevent duplicate submissions
		return "redirect:/projects/new"; 
	}
	
	@GetMapping
	public String displayProjects(Model model) {
		List<Project> projects = projectRepository.findAll();
		
		model.addAttribute("projects", projects);
		
		return "projects/list-projects";
	}

}
