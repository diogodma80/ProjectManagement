package com.dma.pma.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.dma.pma.dto.ChartData;
import com.dma.pma.dto.TimeChartData;
import com.dma.pma.entities.Employee;
import com.dma.pma.entities.Project;

//Rest controller will serve from the 'apiemployees' endpoint while 'employees' will continue to serve the page
@RepositoryRestResource(path = "apiprojects", collectionResourceRel = "apiprojects")
public interface ProjectRepository extends PagingAndSortingRepository<Project, Long>{

//	@Override
//	Iterable<Project> findAll();
	
	@Query(nativeQuery = true, value = "SELECT stage AS label, COUNT(*) AS value "
			+ "FROM project "
			+ "GROUP BY stage")
	public List<ChartData> getProjectStatus();
	
	@Query(nativeQuery = true, value = "SELECT name as projectName, start_date as startDate, end_date as endDate FROM project WHERE start_date IS NOT NULL")
	public List<TimeChartData> getTimeData(); 

}
