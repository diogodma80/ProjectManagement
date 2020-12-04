package com.dma.pma.validators;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.dma.pma.entities.Employee;
import com.dma.pma.services.EmployeeService;

public class UniqueValidator implements ConstraintValidator<UniqueValue, String>{

	@Autowired
	EmployeeService employeeService;
	
	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		
		System.out.println("Entered validation method");
		
		Employee employee = employeeService.findByEmail(value);
		
		if(employee != null) {
			//System.out.println(employee.getEmail());
			return false;
		} else {
			return true;
		}
	}

}
