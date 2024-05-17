package com.example.resource_management.controller;

import com.example.resource_management.model.dto.EmployeeDto;
import com.example.resource_management.model.dto.ResponseDto;
import com.example.resource_management.service.EmployeeService;
import com.example.resource_management.util.Converter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/employees")
public class EmployeeController {

  private final EmployeeService employeeService;

  @GetMapping
  public ResponseEntity<?> getAll(@RequestParam(defaultValue = "0") int page,
      @RequestParam(defaultValue = "10") int size) {
    return ResponseEntity.ok(employeeService.getAll(PageRequest.of(page, size)));
  }


  @PostMapping
  public ResponseEntity<EmployeeDto> addEmployee(@ModelAttribute EmployeeDto employee) {
    EmployeeDto employeeDto = Converter.convertToEmployeeDto(employeeService.create(employee));
    return ResponseEntity.ok(employeeDto);
  }

  @PutMapping
  public ResponseEntity<EmployeeDto> updateEmployee(@RequestBody EmployeeDto employeeDto) {
    return ResponseEntity.ok(employeeService.update(employeeDto));
  }

  @DeleteMapping
  public ResponseDto<Object> delete(@RequestParam long id) {
    employeeService.delete(id);
    return ResponseDto.builder().data("Delete successfully").status(HttpStatus.ACCEPTED).build();
  }

}
