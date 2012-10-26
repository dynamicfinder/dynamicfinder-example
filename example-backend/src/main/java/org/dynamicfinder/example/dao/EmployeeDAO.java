package org.dynamicfinder.example.dao;

import javax.persistence.EntityManagerFactory;

import org.dynamicfinder.example.model.Employee;

public class EmployeeDAO extends AbstractDAO<Employee> {

	public EmployeeDAO(EntityManagerFactory emf) {
		super(emf, Employee.class);
	}

}
