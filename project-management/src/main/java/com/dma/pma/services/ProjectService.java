package com.dma.pma.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dma.pma.dao.ProjectRepository;
import com.dma.pma.dto.ChartData;
import com.dma.pma.dto.TimeChartData;
import com.dma.pma.entities.Project;

@Service
public class ProjectService {

	@Autowired
	ProjectRepository projectRepository;

	public Iterable<Project> findAll() {
		return projectRepository.findAll();
	}

	public List<ChartData> getProjectStatus() {
		return projectRepository.getProjectStatus();
	}

	public Project save(Project project) {
		return projectRepository.save(project);
	}

	public Project findById(Long id) {
		return projectRepository.findById(id).get();
	}

	public void deleteById(Long id) {
		projectRepository.deleteById(id);
	}
	
	public List<TimeChartData> getTimeData() {
		return projectRepository.getTimeData();
	}
}
