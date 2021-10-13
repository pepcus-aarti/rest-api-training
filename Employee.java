package com.employee.example.entity;



import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;



/**
 * @author Aarti
 *
 */

@Entity
@Table(name="employee")
public class Employee {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)	
	@Column(name="id")
	@NotNull
	private int id;
	
	@Column(name="firstName")
	@NotNull
	private String firstName;
	
	@Column(name="lastName")
	@NotNull
	private String lastName;
	
	@ManyToOne
	@NotNull
	private Department department;
	
	
	@Column(name="city")
	@NotNull
	private String city;
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id=id;
	}
	
	
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName=firstName;
	}
	
	
	public String getLastName() {
		return lastName;
	}
	
	public void setlastName(String lastName) {
		this.lastName=lastName;
	}
		
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city=city;
	}
	
	
	public Department getDepartment() {
		return department;
	}
	
	public void setDepartment(Department department) {
		this.department=department;
	}
	
}
