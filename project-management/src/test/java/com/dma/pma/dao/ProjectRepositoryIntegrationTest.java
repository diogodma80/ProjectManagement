package com.dma.pma.dao;

import static org.junit.Assert.assertEquals;

import java.util.Collection;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;
import org.springframework.test.context.jdbc.SqlGroup;
import org.springframework.test.context.junit4.SpringRunner;

import com.dma.pma.entities.Project;
import com.dma.pma.services.ProjectService;

// using SpringBootTest, so the package structure needs to be changed to align with the tested classes' packages
@SpringBootTest
@RunWith(SpringRunner.class)
@SqlGroup({@Sql(executionPhase = ExecutionPhase.BEFORE_TEST_METHOD, scripts = {"classpath:schema.sql", "classpath:data.sql"})}) 
	// ensures the sql files are executed before/after the test
	// drop.sql is an overkill since the schema is wiped out before the test is executed
public class ProjectRepositoryIntegrationTest {

	@Autowired
	ProjectService projectService;
	
	@Test
	public void ifNewProjectSaved_thenSuccess() {
		Project project = new Project("Newest Test Project", "COMPLETE", "Test Description");
		projectService.save(project);
		
		// four projects are loaded from the data.sql, so pass the new project id should be 5
		assertEquals(5, ((Collection<?>)projectService.findAll()).size());
	}
}
