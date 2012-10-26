package org.dynamicfinder.example.model;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Embeddable
public class EmployeeDepartmentID implements Serializable {

	private static final long serialVersionUID = -1503652122025196691L;

	@ManyToOne(fetch=FetchType.LAZY, targetEntity=Employee.class)
	@JoinColumn(name="emp_no", nullable=false)
	private Employee employee;

	@ManyToOne(fetch=FetchType.LAZY, targetEntity=Department.class)
	@JoinColumn(name="dept_no", nullable=false)
	private Department department;

	public EmployeeDepartmentID() {}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

}
