package com.employee.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.employee.example.entity.Department;
import com.employee.example.exception.EmptyInputException;
import com.employee.example.exception.IdDoesNotExist;
import com.employee.example.repository.DepartmentRepository;

/**
 * @author Aarti
 *
 */

@Service
public class DepartmentService {
	@Autowired
	private DepartmentRepository deptRepository;
		
	/**
	 * It is used for saving the given department.
	 * @param department
	 * @return
	 */
	public Department saveDepartment(Department department) {
		return deptRepository.save(department);
	}
	
		
	/**
	 * By this method we get all departments present in database.
	 * @return
	 */
	public List<Department> getDepartments(){
		 return deptRepository.findAll();
	}
	
	/**
	 * By this method we get department by given department id.
	 * @param id
	 * @return
	 */
	public Department getDepartmentById(int id) {
		if(deptRepository.existsById(id))
		return deptRepository.findById(id).orElse(null);
		else throw new IdDoesNotExist("Id "+id+" is invalid");
	}
	
		
	/**
	 * This method is used for deleting the department by given department id and if department is deleted then all
	 * employees who belong to given department are deleted.
	 * @param id
	 * @return
	 */
	public String deleteDepartment(int id) {
		if(deptRepository.existsById(id)) {
		deptRepository.deleteById(id);			
		return "Department Removed";
		}else throw new IdDoesNotExist("Id "+id+" is invalid");
	}
	
	/**
	 * This method is used for updating the department.
	 * @param department
	 * @return
	 */
	public Department updateDepartment(Department department) {
		if(deptRepository.existsById(department.getId()))
		{
		Department existingDepartment=deptRepository.findById(department.getId()).orElse(null);
		
		if(department.getName().isBlank()) {
			throw new EmptyInputException("Department Name field should not be empty.");
		}			
		existingDepartment.setName(department.getName());                          
		return deptRepository.save(existingDepartment);
		}
		else
			throw new IdDoesNotExist("Id is invalid");
	}	

}
