package com.dma.dao;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;
import org.springframework.test.context.jdbc.SqlGroup;
import org.springframework.test.context.junit4.SpringRunner;

import com.dma.pma.ProjectManagementApplication;
import com.dma.pma.dao.ProjectRepository;
import com.dma.pma.entities.Project;

// all the annotations need to be set since the package is not in line with the application
@ContextConfiguration(classes = ProjectManagementApplication.class)
@RunWith(SpringRunner.class)
@DataJpaTest
@SqlGroup({@Sql(executionPhase = ExecutionPhase.BEFORE_TEST_METHOD, scripts = {"classpath:schema.sql", "classpath:data.sql"}),
	@Sql(executionPhase = ExecutionPhase.AFTER_TEST_METHOD, scripts = "classpath:drop.sql")}) 
	// ensures the sql files are executed before/after the test
	// drop.sql is an overkill since the schema is wiped out before the test is executed
public class ProjectRepositoryIntegrationTest {

	@Autowired
	ProjectRepository projectRepository;
	
	@Test
	public void ifNewProjectSaved_thenSuccess() {
		Project project = new Project("New Test Project", "COMPLETE", "Test Description");
		projectRepository.save(project);
		
		assertEquals(5, projectRepository.findAll().size());
	}
}
