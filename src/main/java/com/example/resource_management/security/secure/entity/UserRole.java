package com.example.resource_management.security.secure.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "users_roles")
public class UserRole {

  @Id
  @GeneratedValue
  private Long id;

  @ManyToOne
  @JoinColumn(name = "userId", referencedColumnName = "id")
  private User user;
  
  @ManyToOne
  @JoinColumn(name = "roleId", referencedColumnName = "id")
  private Role role;
}
