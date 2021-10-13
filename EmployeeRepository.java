package com.employee.example.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.employee.example.data.DeptEmpDto;
import com.employee.example.entity.Employee;

/**
 * @author Aarti
 *
 */

public interface EmployeeRepository extends JpaRepository<Employee,Integer>,JpaSpecificationExecutor<Employee> {

	
	Employee findById(int id);
			
	List<Employee> findByCity(String city);
	
	List<Employee> findByFirstName(String firstName);
	
	List<Employee> findByLastName(String lastName);
	
	List<Employee> findByFirstNameAndCity(String firstName,String city);
	
		
	@Query("SELECT new com.employee.example.data.DeptEmpDto(d.name,e.firstName)"+"From Employee e INNER JOIN e.department d")
	List<DeptEmpDto> fetchDeptEmpDataInnerJoin();
	
		
	@Query("SELECT e from Employee e INNER JOIN e.department d where d.id=:id")
	List<Employee> findEmployeesByDepartmentId(@Param("id") int id);
	
}
	
	