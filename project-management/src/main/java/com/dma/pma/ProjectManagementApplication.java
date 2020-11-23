package com.dma.pma;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.dma.pma.dao.EmployeeRepository;
import com.dma.pma.dao.ProjectRepository;
import com.dma.pma.entities.Employee;
import com.dma.pma.entities.Project;

@SpringBootApplication
public class ProjectManagementApplication {
	
	@Autowired
	private EmployeeRepository employeeRepository;
	
	@Autowired
	private ProjectRepository projectRepository;

	public static void main(String[] args) {
		SpringApplication.run(ProjectManagementApplication.class, args);
	}
	
	@Bean
	CommandLineRunner runner() {
		return args -> {
			Employee employee1 = new Employee("John", "Warton", "warton@gmail.com");
			Employee employee2 = new Employee("Mike", "Lanister", "lanister@gmail.com");
			Employee employee3 = new Employee("Steve", "Reeves", "Reeves@gmail.com");
			Employee employee4 = new Employee("Ronald", "Connor", "connor@gmail.com");
			Employee employee5 = new Employee("Jim", "Salvator", "Sal@gmail.com");
			Employee employee6 = new Employee("Peter", "Henley", "henley@gmail.com");
			Employee employee7 = new Employee("Richard", "Carson", "carson@gmail.com");
			Employee employee8 = new Employee("Honor", "Miles", "miles@gmail.com");
			Employee employee9 = new Employee("Tony", "Roggers", "roggers@gmail.com");

			Project project1 = new Project("Large Production Deploy", "NOTSTARTED", "This requires all hands on deck for"
					+ "the final deployment of the software into production");
			Project project2 = new Project("New Employee Budget",  "COMPLETED", "Decide on a new employee bonus budget"
					+ "for the year and figureout who will be promoted");
			Project project3 = new Project("Office Reconstruction", "INPROGRESS", "The office building in Monroe has "
					+ "been damaged due to hurricane in the region. This needs to be reconstructed");
			Project project4 = new Project("Improve Intranet Security", "INPROGRESS", "With the recent data hack, the office"
					+ "security needs to be improved and proper security team needs to be hired for "
					+ "implementation");
			
			// need to set both sides of the relationship manually
			project1.addEmployee(employee1);
			project1.addEmployee(employee2);
			project2.addEmployee(employee3);
			project3.addEmployee(employee1);
			project4.addEmployee(employee1);
			project4.addEmployee(employee3);
			
			// need to set both sides of the relationship manually
			employee1.setProjects(Arrays.asList(project1, project3, project4));
			employee2.setProjects(Arrays.asList(project1));
			employee3.setProjects(Arrays.asList(project2, project4));
			
			// save employees in database
			employeeRepository.save(employee1);
			employeeRepository.save(employee2); 
			employeeRepository.save(employee3); 
			employeeRepository.save(employee4);
			employeeRepository.save(employee5); 
			employeeRepository.save(employee6); 
			employeeRepository.save(employee7); 
			employeeRepository.save(employee8); 
			employeeRepository.save(employee9);

			// save projects in database
			projectRepository.save(project1);
			projectRepository.save(project2); 
			projectRepository.save(project3); 
			projectRepository.save(project4);
		};
	}

}
