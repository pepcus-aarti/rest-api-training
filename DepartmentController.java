package com.employee.example.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.employee.example.entity.Department;

import com.employee.example.service.DepartmentService;

/**
 * @author Aarti
 *
 */
@RestController

public class DepartmentController {
	
	@Autowired
	private DepartmentService deptService;
	
	/**
	 * Method is used for adding department
	 * @param department
	 * @return
	 */
	@PostMapping("/department")
	public ResponseEntity<Department> addDepartment(@RequestBody Department department) {
		Department departmentObj=deptService.saveDepartment(department);
		return  ResponseEntity.status(HttpStatus.CREATED).body(departmentObj);
	}
	
		
	/**
	 * Method is used for finding Department by id.
	 * @param id
	 * @return
	 */
	@GetMapping("/departmentById/{id}")
	public ResponseEntity<Department> findDepartmentById(@PathVariable("id")int id) {		
		Department departmentObj=deptService.getDepartmentById(id);
		return ResponseEntity.status(HttpStatus.FOUND).body(departmentObj);
		}
	
	
	/**
	 * Method is used for finding all Departments.
	 * @return
	 */
	@GetMapping("/department")
	public List<Department> findAllDepartments(){
		 return deptService.getDepartments();
	}
	
	/**
	 * Method is used for updating the Department object.
	 * @param department
	 * @return
	 */
	@PutMapping("/department")
	public Department updateDepartment(@RequestBody Department department) {
		return deptService.updateDepartment(department);
	}
	
	/**
	 * Method is used for deletion of Department by given Department Id.
	 * @param id
	 * @return
	 */
	@DeleteMapping("/department")
	public String deleteDepartment(@RequestParam int id) {
		return deptService.deleteDepartment(id);
	}

}
