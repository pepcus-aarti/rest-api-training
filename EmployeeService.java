package com.employee.example.service;



import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import com.employee.example.data.DeptEmpDto;
import com.employee.example.entity.Employee;
import com.employee.example.exception.EmptyInputException;
import com.employee.example.exception.IdDoesNotExist;
import com.employee.example.repository.DepartmentRepository;
import com.employee.example.repository.EmployeeRepository;

/**
 * @author Aarti
 *
 */

@Service
public class EmployeeService {
	
	
	@Autowired
	private EmployeeRepository employeeRepository;
	
	@Autowired
	private DepartmentRepository deptRepository;
	
	/**
	 * This method is used for saving the given employee object in database.
	 * @param employee
	 * @return
	 * @throws Exception
	 */
	public Employee saveEmployee(Employee employee) throws Exception{		
		
			if(employee.getFirstName().isEmpty()){
				throw new EmptyInputException("FirstName field should not be empty.");
			}
			if(employee.getLastName().isEmpty()){
				throw new EmptyInputException("LastName field should not be empty.");
			}
		   if(employee.getCity().isEmpty()){
			   throw new EmptyInputException("City field should not be empty.");
		    }
		   if(!deptRepository.existsById(employee.getDepartment().getId())) {			
			   throw new IdDoesNotExist("Given department id is not exist in database");		
			}	
		return employeeRepository.save(employee);
	}
	
	
	
	/**
	 * This method is used for finding employee by given id.
	 * @param id
	 * @return
	 */
	public Employee findEmployeeById(int id) {
		if(employeeRepository.existsById(id))
		return employeeRepository.findById(id);
		else
		throw new IdDoesNotExist("Given id" + id +" is not exist");			
	}
	
	/**
	 * This method is used for finding employees by filter on
	 * firstName  or
	 * city       or
	 * firstName and city.
	 * If no request param is given then it can return all employees present in database.
	 * @param firstName
	 * @param lastName
	 * @param city
	 * @return
	 */
	public List<Employee> findEmployees(String firstName,String lastName,String city)
	{
		
		List<Employee> employees=null;
			if(firstName!=null&&city!=null) 
			employees=employeeRepository.findByFirstNameAndCity(firstName,city);
		
			else
			if(firstName!=null)
				employees=employeeRepository.findByFirstName(firstName);
			else
			if(city!=null)
				employees=employeeRepository.findByCity(city);
			else
			if(firstName==null && lastName==null && city==null)
			  employees=employeeRepository.findAll();
			
			return employees;				
	}
	
	
	/**
	 * This method is used for deleting employee by given employee id.
	 * @param id
	 * @return
	 */
	public String deleteEmployee(int id) {
		
		if(employeeRepository.existsById(id)) {
		employeeRepository.deleteById(id);
		return "employee removed" +id;
		}
		else {
			throw new IdDoesNotExist("Given id is not exist");
		}		
	}
	
	/**
	 * This method is used for updating the employee information.
	 * @param employee
	 * @return
	 */
	public Employee updateEmployee(Employee employee) {
		if(employeeRepository.existsById(employee.getId()))
		{
		Employee existingEmployee=employeeRepository.findById(employee.getId());
		existingEmployee.setFirstName(employee.getFirstName());
		existingEmployee.setlastName(employee.getLastName());
		existingEmployee.setDepartment(employee.getDepartment());
		existingEmployee.setCity(employee.getCity());
		return employeeRepository.save(existingEmployee);
		}
		else
			throw new IdDoesNotExist("Given id is not exist");
	}
	/**
	 * This method is used for finding department names with their corresponding employee names.
	 * @return
	 */
	public List<DeptEmpDto> getDeparmentEmployeeData(){
			
		return employeeRepository.fetchDeptEmpDataInnerJoin();
	}
	
	/**
	 * This method returns all employees who are present in given department.
	 * @param id
	 * @return
	 */
	public List<Employee> findEmployeeByDepartmentId(int id){
		
		if(deptRepository.existsById(id))
		return employeeRepository.findEmployeesByDepartmentId(id);
		else 
			throw new IdDoesNotExist("Given id is not exist");			
	}
	
		
	static Specification<Employee> hasFirstName(String firstName) {
	    return (employee, cq, cb) -> cb.equal(employee.get("firstName"), firstName);
	}
	static Specification<Employee> hasLastName(String lastName) {
	    return (employee, cq, cb) -> cb.equal(employee.get("lastName"), lastName);
	}
	static Specification<Employee> hasCity(String city) {
	    return (employee, cq, cb) -> cb.equal(employee.get("city"), city);
	}
	
	/**
	 * This method finds employee by firstName,lastName and city. 
	 * @param firstName
	 * @param lastName
	 * @param city
	 * @return
	 */
	public List<Employee> findEmployeeByFirstNameAndLastNameAndCity(String firstName,String lastName,String city){		
		return employeeRepository.findAll(Specification.where(hasFirstName(firstName)).and(hasLastName(lastName)).and(hasCity(city)));
	}
	
}
