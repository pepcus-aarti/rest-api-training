package com.employee.example.entity;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 * @author Aarti
 *
 */

@Entity
@Table(name="department")
public class Department {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)	
	@NotNull
	private int id;
    
	@NotNull
	@Column(name="name")
	private String name;
	
	public int getId() {
		return id;
	}	
	public void setId(int id) {
		this.id=id;
	}

	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name=name;
	}
}
