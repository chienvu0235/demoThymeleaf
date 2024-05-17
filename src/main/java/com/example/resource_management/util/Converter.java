package com.example.resource_management.util;

import com.example.resource_management.model.Employee;
import com.example.resource_management.model.dto.EmployeeDto;

public class Converter {

  // Can be refactored by using ModelMapper
  public static EmployeeDto convertToEmployeeDto(Employee employee) {
    return EmployeeDto
        .builder()
        .id(employee.getId())
        .name(employee.getName())
        .email(employee.getEmail())
        .roleId(employee.getRoleId())
        .department(employee.getDepartment())
        .gender(employee.getGender())
        .phoneNumber(employee.getPhoneNumber())
        .build();
  }

  public static void convertToEmployee(EmployeeDto employeeDto, Employee employee) {
    employee.setName(employeeDto.getName());
    employee.setEmail(employeeDto.getEmail());
    employee.setPhoneNumber(employeeDto.getPhoneNumber());
    employee.setGender(employeeDto.getGender());
    employee.setDepartment(employeeDto.getDepartment());
    employee.setRoleId(employeeDto.getRoleId());
  }

}
