package com.dma.pma.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.dma.pma.dto.EmployeeProject;
import com.dma.pma.entities.Employee;

// Rest controller will serve from the 'apiemployees' endpoint while 'employees' will continue to serve the page
@RepositoryRestResource(path = "apiemployees", collectionResourceRel = "apiemployees")
public interface EmployeeRepository extends PagingAndSortingRepository<Employee, Long> {

//	@Override
//	List<Employee> findAll();
	
	// returns a table with first name, last name and project count for each employee
	@Query(nativeQuery = true, value = "SELECT e.first_name AS firstName, e.last_name AS lastName, COUNT(pe.employee_id) AS projectCount "
			+ "FROM employee e left join project_employee pe ON pe.employee_id = e.employee_id "
			+ "GROUP BY e.first_name, e.last_name ORDER BY 3 DESC")
	public List<EmployeeProject> employeeProjects();

	public Employee findByEmail(String value);

	public Employee findByEmployeeId(long id);
	
//	@Query(nativeQuery = true, value = "SELECT e.first_name AS firstName, e.employee_id as employeeId "
//			+ "FROM employee e left join project_employee pe ON pe.employee_id = e.employee_id")
//	public List<EmployeeProject> projectEmployees();
}
