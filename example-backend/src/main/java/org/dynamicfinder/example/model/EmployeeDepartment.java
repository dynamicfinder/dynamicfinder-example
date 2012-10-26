package org.dynamicfinder.example.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

// http://www.java.net/node/707224
@Entity
@Table(name="employee_department")
public class EmployeeDepartment implements Serializable {

	private static final long serialVersionUID = -4404340141071156987L;

	@EmbeddedId
	private EmployeeDepartmentID id;

	@Temporal(TemporalType.DATE)
	@Column(name="from_date", nullable=false)
	private Date from;

	@Temporal(TemporalType.DATE)
	@Column(name="to_date", nullable=false)
	private Date to;

	public EmployeeDepartment() {}

	public EmployeeDepartmentID getId() {
		return id;
	}

	public void setId(EmployeeDepartmentID id) {
		this.id = id;
	}

	public Date getFrom() {
		return from;
	}

	public void setFrom(Date from) {
		this.from = from;
	}

	public Date getTo() {
		return to;
	}

	public void setTo(Date to) {
		this.to = to;
	}

}
