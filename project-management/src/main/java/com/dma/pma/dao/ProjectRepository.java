package com.dma.pma.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.dma.pma.dto.ChartData;
import com.dma.pma.entities.Project;

public interface ProjectRepository extends PagingAndSortingRepository<Project, Long>{

//	@Override
//	Iterable<Project> findAll();
	
	@Query(nativeQuery = true, value = "SELECT stage AS label, COUNT(*) AS value "
			+ "FROM project "
			+ "GROUP BY stage")
	public List<ChartData> getProjectStatus();

}
