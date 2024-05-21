package com.example.resource_management.security.secure.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.Set;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "users")
public class User {

  @Id
  @GeneratedValue
  private Long id;

  private String userName;

  private String passWord;

  private Boolean enabled;

  private String fullName;

  private Boolean gender;

  private String address;

  private String email;

  @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
  private Set<UserRole> userRoles;

}
