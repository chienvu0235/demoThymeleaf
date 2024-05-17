package com.example.resource_management.service;

import com.example.resource_management.model.Employee;
import com.example.resource_management.model.dto.EmployeeDto;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

public interface EmployeeService {

  Page<?> getAll(PageRequest pageRequest);

  Employee create(EmployeeDto employeeDto);

  Employee findById(Long id);

  EmployeeDto update(EmployeeDto employeeDto);

  void delete(Long id);
}
