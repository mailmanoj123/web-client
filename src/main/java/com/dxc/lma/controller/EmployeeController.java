package com.dxc.lma.controller;


import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.dxc.lma.dto.Employee;
import com.dxc.lma.dto.EmployeeNotFoundExcepetion;

@RestController
public class EmployeeController {

	private List<Employee> employees = createList();

	@RequestMapping(value = "/employees", method = RequestMethod.GET, produces = "application/json")
	public List<Employee> firstPage() {
		return employees;
	}
	
	@RequestMapping(value = "/employees/{id}", method = RequestMethod.GET, produces = "application/json")
	public Employee getEmployee(@PathVariable("id") String id) {
		
		Employee retVal = null;
		for (Employee emp : employees) {
			if (emp.getEmpId().equals(id)) {
				retVal = emp;
				break;
			}
		}
		
		if(retVal == null) {
			throw new EmployeeNotFoundExcepetion("empId-"+id);
		}
		
		return retVal;
	}

	@DeleteMapping(path = { "/employees/{id}" })
	public Employee delete(@PathVariable("id") String id) {
		Employee deletedEmp = null;
		for (Employee emp : employees) {
			if (emp.getEmpId().equals(id)) {
				employees.remove(emp);
				deletedEmp = emp;
				break;
			}
		}
		return deletedEmp;
	}

	@PostMapping("/employees")
	public ResponseEntity<Employee> create(@RequestBody Employee user) {
		employees.add(user);
		System.out.println(employees);
		URI location = ServletUriComponentsBuilder
				.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(user.getEmpId()).toUri();
		return ResponseEntity.created(location).build();
	}

	private static List<Employee> createList() {
		List<Employee> tempEmployees = new ArrayList<>();
		Employee emp1 = new Employee();
		emp1.setName("emp1");
		emp1.setDesignation("manager");
		emp1.setEmpId("1");
		emp1.setSalary(3000);

		Employee emp2 = new Employee();
		emp2.setName("emp2");
		emp2.setDesignation("developer");
		emp2.setEmpId("2");
		emp2.setSalary(3000);
		tempEmployees.add(emp1);
		tempEmployees.add(emp2);
		return tempEmployees;
	}
}
