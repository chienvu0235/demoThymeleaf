package com.example.resource_management.model;

import jakarta.annotation.Nonnull;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tb_employee")
public class Employee {

  @Id
  @GeneratedValue// tu dong sinh id (primary key)
  private Long id;

  @Column(name = "name") // neu trung thi bo di cung duoc
  @Nonnull
  private String name;

  @Column(name = "gender")
  private String gender;

  @Column(name = "phone_number")
  private String phoneNumber;

  @Column(name = "email", unique = true)
  @Nonnull
  private String email;

  @Column(name = "department")
  private String department;

  private String role;

  private int roleId;
}
