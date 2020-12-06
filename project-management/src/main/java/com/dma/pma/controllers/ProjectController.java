package com.dma.pma.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.dma.pma.dto.TimeChartData;
import com.dma.pma.entities.Employee;
import com.dma.pma.entities.Project;
import com.dma.pma.services.EmployeeService;
import com.dma.pma.services.ProjectService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
@RequestMapping("/projects")
public class ProjectController {
	
	@Autowired
	ProjectService projectService;
	
	@Autowired
	EmployeeService employeeService;

	@GetMapping("/new")
	public String displayProjectForm(Model model) {
		// fbind an entity project to the form using the model.addAttribute method
		Project project = new Project();
		
		// get the list of employees and send it over to the new-project page
		Iterable<Employee> employees = employeeService.findAll();
		
		model.addAttribute("project", project);
		model.addAttribute("employees", employees);
		return "projects/new-project";
	}
	
	@PostMapping("/save")
	public String createProject(Model model, @Valid Project project, Errors errors) {
		
		if(errors.hasErrors()) {
			return "projects/new-project";
		}
		
		// this method should handle saving to the database
		projectService.save(project);
		
		// use a redirect to prevent duplicate submissions
		return "redirect:/projects"; 
	}
	
	@GetMapping
	public String displayProjects(Model model) {
		Iterable<Project> projects = projectService.findAll();
		
		model.addAttribute("projects", projects);
		
		return "projects/list-projects";
	}
	
	@GetMapping("/timelines")
	public String displayProjectTimelines(Model model) throws JsonProcessingException {
		
		List<TimeChartData> timelineData =  projectService.getTimeData();
		
		// wrap the project data (name, start and end data) into the objectMaper object
		ObjectMapper objectMapper = new ObjectMapper();
		String jsonTimeLineData = objectMapper.writeValueAsString(timelineData);
		
		System.out.println("------- project timelines --------");
		System.out.println(jsonTimeLineData);
		
		model.addAttribute("projectTimeList", jsonTimeLineData);
		return "projects/project-timelines";
	}

}
