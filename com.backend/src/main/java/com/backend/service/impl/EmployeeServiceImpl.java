package com.backend.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.backend.dto.EmployeeDto;
import com.backend.entity.Employee;
import com.backend.exception.ResourceNotFoundException;
import com.backend.mapper.EmployeeMapper;
import com.backend.repository.EmployeeRepository;
import com.backend.service.EmployeeService;

import lombok.AllArgsConstructor;
@Service
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService{
	
	private EmployeeRepository employeeRepository;

	@Override
	public List<EmployeeDto> getAllEmployees() {
		 List<Employee> employees = employeeRepository.findAll();
	        List<EmployeeDto> employeeDtos = employees.stream()
	                .map((employee) -> EmployeeMapper.mapToEmployeeDto(employee))
	                .collect(Collectors.toList());
	        return employeeDtos;
	}

	@Override
	public EmployeeDto createEmployee(EmployeeDto employeeDto) {
		// TODO Auto-generated method stub
		Employee employee = EmployeeMapper.mapToEmployee(employeeDto);
        Employee savedEmployee = employeeRepository.save(employee);
        return EmployeeMapper.mapToEmployeeDto(savedEmployee);
	}

	@Override
	public EmployeeDto getEmployeeById(Long employeeId) {
		// TODO Auto-generated method stub
		Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Employee not exist with id: " + employeeId));
        EmployeeDto employeeDto = EmployeeMapper.mapToEmployeeDto(employee);
        return employeeDto;
	}

	@Override
	public EmployeeDto updateEmployee(Long employeeId, EmployeeDto employeeDto) {
		// TODO Auto-generated method stub

        Employee existingEmployee = employeeRepository.findById(employeeId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Employee not exist with id: " + employeeId));

        existingEmployee.setFirstName(employeeDto.getFirstName());
        existingEmployee.setLastName(employeeDto.getLastName());
        existingEmployee.setEmail(employeeDto.getEmail());

        employeeRepository.save(existingEmployee);
        return EmployeeMapper.mapToEmployeeDto(existingEmployee);
	}

	@Override
	public void deleteEmployee(Long employeeId) {
		// TODO Auto-generated method stub
		Employee existingEmployee = employeeRepository.findById(employeeId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Employee not exist with id: " + employeeId));

        employeeRepository.deleteById(employeeId);
		
	}

}
