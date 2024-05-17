package com.example.resource_management.model.dto;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeDto implements Serializable {

  private Long id;

  private String name;

  private String gender;

  private String phoneNumber;

  private String email;

  private String department;

  private int roleId;

}
