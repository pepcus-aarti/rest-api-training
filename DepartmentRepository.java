package com.employee.example.repository;

/**
 * @author Aarti
 *
 */

import org.springframework.data.jpa.repository.JpaRepository;

import com.employee.example.entity.Department;

public interface DepartmentRepository extends JpaRepository<Department,Integer> {
	
	Department findByName(String deptName);
	
	
}
