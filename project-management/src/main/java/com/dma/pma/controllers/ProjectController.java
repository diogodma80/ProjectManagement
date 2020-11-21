package com.dma.pma.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.dma.pma.entities.Project;

@Controller
@RequestMapping("/projects")
public class ProjectController {

	@GetMapping("/new")
	public String displayProjectForm(Model model) {
		//bind an entity project to the form using the model.addAttribute method
		Project project = new Project();
		model.addAttribute("project", project);
		return "new-project";
	}
	
	@PostMapping("/save")
	public String createProject(Project project, Model model) { 
		//TODO - this method should handle saving to the database
		return ""; 
	}
}
