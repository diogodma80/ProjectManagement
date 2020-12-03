package com.dma.pma.api.controllers;

import javax.validation.Valid;

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

import com.dma.pma.entities.Project;
import com.dma.pma.services.ProjectService;

@RestController
@RequestMapping("/app-api/projects")
public class ProjectApiController {
	
	@Autowired
	ProjectService projectService;
	
	@GetMapping
	public Iterable<Project> getProjects() {
		return projectService.findAll();
	}
	
	@GetMapping("/{id}")
	public Project getProjectById(@PathVariable("id") Long id) {
		return projectService.findById(id);
	}
	
	@PostMapping(consumes = "application/json")
	@ResponseStatus(HttpStatus.CREATED)
	public Project create(@RequestBody @Valid Project project) {
		return projectService.save(project);
	}
	
	@PutMapping(consumes = "application/json")
	@ResponseStatus(HttpStatus.OK)
	public Project update(@RequestBody @Valid Project project) {
		return projectService.save(project);
	}
	
	@PatchMapping(path = "/{id}", consumes = "application/json")
	public Project partialUpdate(@PathVariable("id") long id, @RequestBody @Valid Project patchProject) {
		Project project = projectService.findById(id);
		
		if(patchProject.getName() != null) {
			project.setName(patchProject.getName());
		}
		if(patchProject.getStage() != null) {
			project.setStage(patchProject.getStage());
		}
		if(patchProject.getDescription() != null) {
			project.setDescription(patchProject.getDescription());
		}

		return projectService.save(project);
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable("id") Long id) {
		try {
			projectService.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			//e.printStackTrace();
		}
		
	}

}
