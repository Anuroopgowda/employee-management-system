package com.backend.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.backend.dto.EmployeeDto;
import com.backend.service.EmployeeService;

import lombok.AllArgsConstructor;

@CrossOrigin("*")
@AllArgsConstructor
@RestController
@RequestMapping("/api/employees")
public class EmployeeController {
	
	private EmployeeService employeeService;
	
	 @GetMapping
	    public ResponseEntity<List<EmployeeDto>> getAllEmployees(){
	        List<EmployeeDto> employees = employeeService.getAllEmployees();
	        return ResponseEntity.ok(employees);
	    }
	
	// build create employee REST API
    @PostMapping
    public ResponseEntity<EmployeeDto> createEmployee(@RequestBody EmployeeDto employee) {
        EmployeeDto savedEmployee = employeeService.createEmployee(employee);
        return new ResponseEntity<>(savedEmployee, HttpStatus.CREATED);
    }
    
 // build get employee by id REST API
    @GetMapping("{id}")
    public ResponseEntity<EmployeeDto> getEmployeeById(@PathVariable("id") Long employeeId){
        EmployeeDto employee = employeeService.getEmployeeById(employeeId);
        return ResponseEntity.ok(employee);
    }

    // build update employee REST API
    @PutMapping("{id}")
    public ResponseEntity<EmployeeDto> updateEmployee(@PathVariable("id") Long employeeId,
                                                   @RequestBody EmployeeDto employeeDetails) {
        EmployeeDto updateEmployee = employeeService.updateEmployee(employeeId, employeeDetails);
        return ResponseEntity.ok(updateEmployee);
    }

    // build delete employee REST API
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteEmployee(@PathVariable("id") Long employeeId){
        employeeService.deleteEmployee(employeeId);
        return ResponseEntity.ok("Employee deleted successfully!");
    }

}
