package com.dma.pma.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.dma.pma.dao.EmployeeRepository;
import com.dma.pma.dao.ProjectRepository;
import com.dma.pma.dto.ChartData;
import com.dma.pma.dto.EmployeeProject;
import com.dma.pma.entities.Project;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
public class HomeController {
	
	@Autowired
	ProjectRepository projectRepository;
	
	@Autowired
	EmployeeRepository employeeRepository;

	@GetMapping("/")
	public String displayHome(Model model) throws JsonProcessingException {
		
		//Map<String, Object> map = new HashMap<>();
		
		// findAll is overriden in the ProjectRepository so that a list 
		// of Projects is returned instead of Iterable
		List<Project> projects =  projectRepository.findAll();
		List<EmployeeProject> employeesProjectCount = employeeRepository.employeeProjects();
		
		List<ChartData> projectData = projectRepository.getProjectStatus();
		
		// convert projectData object into a json structure for use in javascript
		ObjectMapper objectMapper = new ObjectMapper();
		String jsonString = objectMapper.writeValueAsString(projectData);
		// string return format: [["NOTSTARTED", 1], ["INPROGRESS", 2], ["COMPLETED", 1]]
		
		model.addAttribute("projectStatusCount", jsonString);
		
		model.addAttribute("projects", projects);
		model.addAttribute("employeesProjectCount", employeesProjectCount);
		
		return "main/home";
	}
}
