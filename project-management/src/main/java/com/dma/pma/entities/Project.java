package com.dma.pma.entities;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Project {

	// SEQUENCE makes the database assign primary keys for the entity using a database sequence.
	@Id
	@Column(name = "project_id")
	@SequenceGenerator(name = "project_seq", sequenceName = "project_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "project_seq")
	private long projectId;
	
	@NotBlank(message = " * Must provide a project name")
	@Size(min = 4, max = 50)
	private String name;
	
	@NotBlank(message = " * Must provide a project status")
	private String stage; // categorize the project as NOTSTARTED, COMPLETED, INPROGRESS
	
	@NotBlank(message = " * Must provide a project description")
	@Size(min = 4, max = 50)
	private String description;

	@JsonIgnore
	@ManyToMany(cascade = { CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH,
			CascadeType.PERSIST }, fetch = FetchType.LAZY)
	@JoinTable(name = "project_employee", joinColumns = @JoinColumn(name = "project_id"), inverseJoinColumns = @JoinColumn(name = "employee_id"))
	private List<Employee> employees;
	
	@NotNull(message = "Date cannot be empty")
	private Date startDate;
	
	@NotNull(message = "Date cannot be empty")
	private Date endDate;

	public Project() {
		super();
	}

	public Project(String name, String stage, String description) {
		super();
		this.name = name;
		this.stage = stage;
		this.description = description;
	}

	public long getProjectId() {
		return projectId;
	}

	public void setProjectId(long projectId) {
		this.projectId = projectId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getStage() {
		return stage;
	}

	public void setStage(String stage) {
		this.stage = stage;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<Employee> getEmployees() {
		return employees;
	}

	public void setEmployees(List<Employee> employees) {
		this.employees = employees;
	}
	
	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	// convenience method:
	public void addEmployee(Employee emp) {
		if (employees == null) {
			employees = new ArrayList<>();
		}
		employees.add(emp);
	}

}
