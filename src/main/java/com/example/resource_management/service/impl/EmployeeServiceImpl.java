package com.example.resource_management.service.impl;

import com.example.resource_management.exception.EmployeeException;
import com.example.resource_management.model.Employee;
import com.example.resource_management.model.dto.EmployeeDto;
import com.example.resource_management.repository.EmployeeRepository;
import com.example.resource_management.service.EmployeeService;
import com.example.resource_management.util.Converter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

  private final EmployeeRepository employeeRepository;

  @Override
  public Page<?> getAll(PageRequest pageRequest) {
    return employeeRepository.findAll(pageRequest);
  }

  @Override
  public Employee create(EmployeeDto employeeDto) {
    if (employeeRepository.existsByEmail(employeeDto.getEmail())) {
      throw new EmployeeException("Email already exists: " + employeeDto.getEmail());
    }
    Employee employee = new Employee();
    Converter.convertToEmployee(employeeDto, employee);
    return employeeRepository.save(employee);
  }

  @Override
  public Employee findById(Long id) {
    return employeeRepository.findById(id)
        .orElseThrow(() -> new EmployeeException("Employee not found."));
  }

  @Override
  public EmployeeDto update(EmployeeDto employeeDto) {
    Employee employee = this.findById(employeeDto.getId());

    // because email must be unique
    if (!employee.getEmail().equals(employeeDto.getEmail()) && employeeRepository.existsByEmail(
        employeeDto.getEmail())) {
      throw new EmployeeException("Email already existed: " + employeeDto.getEmail());
    }

    Converter.convertToEmployee(employeeDto, employee);
    employeeRepository.save(employee);
    return employeeDto;
  }

  @Override
  public void delete(Long id) {
    employeeRepository.delete(this.findById(id));
  }
}