package com.dma.pma.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dma.pma.dao.ProjectRepository;
import com.dma.pma.dto.ChartData;
import com.dma.pma.entities.Project;

@Service
public class ProjectService {

	@Autowired
	ProjectRepository projectRepository;

	public List<Project> findAll() {
		return projectRepository.findAll();
	}

	public List<ChartData> getProjectStatus() {
		return projectRepository.getProjectStatus();
	}

	public void save(Project project) {
		projectRepository.save(project);
	}
}
