package org.dynamicfinder.example.model;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="department")
public class Department implements Serializable {

	private static final long serialVersionUID = -4706946845192055747L;

	@Id @Column(name="dept_no")
	private String id;

	@Column(name="dept_name", nullable=false, length=40)
	private String name;

	@OneToMany(fetch=FetchType.LAZY, mappedBy="id.department")
	private Set<EmployeeDepartment> employees;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<EmployeeDepartment> getEmployees() {
		return employees;
	}

	public void setEmployees(Set<EmployeeDepartment> employees) {
		this.employees = employees;
	}

}
