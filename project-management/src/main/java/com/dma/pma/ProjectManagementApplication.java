package com.dma.pma;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.dma.pma.dao.EmployeeRepository;
import com.dma.pma.dao.ProjectRepository;

@SpringBootApplication
public class ProjectManagementApplication {
	
	@Autowired
	private EmployeeRepository employeeRepository;
	
	@Autowired
	private ProjectRepository projectRepository;

	public static void main(String[] args) {
		SpringApplication.run(ProjectManagementApplication.class, args);
	}
	
	// data.sql used to load the data to the database

}
