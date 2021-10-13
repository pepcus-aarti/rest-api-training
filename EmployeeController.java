package com.employee.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.employee.example.data.DeptEmpDto;
import com.employee.example.entity.Employee;
import com.employee.example.service.EmployeeService;


/**
 * @author Aarti
 *
 */
@RestController
@Validated

public class EmployeeController {
	
	@Autowired
	private EmployeeService employeeService;
	
	/**
	 * Method to POST Employee Object
	 * @param employee
	 * @return
	 * @throws Exception 
	 */
	@PostMapping("/employee")
	public ResponseEntity<?> addEmployee(@RequestBody Employee employee) throws Exception {
		
	    Employee employeeSaved=employeeService.saveEmployee(employee);
	    return new ResponseEntity<Employee>(employeeSaved, HttpStatus.CREATED);
	}

		
	 /**This method is used for finding all employees and also for filtering by giving request param as
	 * by firstName   or
	 * by city   or
	 * by firstName and city.
	 * @param firstName
	 * @param lastName
	 * @param city
	 * @return
	 */
	
	@GetMapping("/employee")
	public ResponseEntity<List<Employee>> findAllEmployees(@RequestParam(required=false) String firstName,
			@RequestParam(required=false) String lastName,
			@RequestParam(required=false) String city){
		List<Employee> reqEmployees=employeeService.findEmployees(firstName,lastName,city);
	     return new ResponseEntity<List<Employee>> (reqEmployees,HttpStatus.OK);
	}
	
	/**
	 * Method to find Employee by Id.
	 * @param employeeId
	 * @return
	 */
	
	@GetMapping("/employeeById/{id}")
	public ResponseEntity<Employee> findEmployeeById(@PathVariable int id){	
		Employee employee=employeeService.findEmployeeById(id);
		return new ResponseEntity<Employee>(employee,HttpStatus.OK);
		//return ResponseEntity.status(status).body(exception);
		
	}
	
	/**
	 * Method to used for updating the employee information.
	 * @param employee
	 * @return
	 */
	@PutMapping("/employee")
	public ResponseEntity<Employee> updateEmployee(@RequestBody Employee employee) {
		Employee updatedEmployee=employeeService.updateEmployee(employee);
		return new ResponseEntity<Employee>(updatedEmployee, HttpStatus.OK);
	}
	
	/**
	 * Method used for deleting the Employee object by given employee id.
	 * @param id
	 * @return
	 */
	@DeleteMapping("/employee")
	public ResponseEntity<String> deleteEmployee(@RequestParam int id) {
		employeeService.deleteEmployee(id);
		return new ResponseEntity<String> ("Deleted",HttpStatus.OK);
	}
	
	/**
	 * This is used for retrieving all department name with corresponding employee name.
	 * @return
	 */
	@GetMapping("/deptEmployeeData")
	public List<DeptEmpDto> findAllDeparmentAndEmployees(){		
		return employeeService.getDeparmentEmployeeData();
   }	
		
	/**
	 * This method is used for finding all the employees who are present in given department.
	 * @param id
	 * @return
	 */
	@GetMapping("/employeeByDepartmentId")
	public List<Employee> findRequiredEmployeeByDeptId(@RequestParam int id){		
		return employeeService.findEmployeeByDepartmentId(id);
   }
	
	/**
	 * This method is used for finding employee by given firstName,lastName and city.
	 * @param firstName
	 * @param lastName
	 * @param city
	 * @return
	 */
	@GetMapping("/employeeFirstNameAndLastNameAndCity")
	public List<Employee> FindEmployeeFirstNameAndLastNameAndCity(@RequestParam(required=false) String firstName,
			@RequestParam(required=false) String lastName,
			@RequestParam(required=false) String city){
		
		return employeeService.findEmployeeByFirstNameAndLastNameAndCity(firstName, lastName, city);
   }
	
}
