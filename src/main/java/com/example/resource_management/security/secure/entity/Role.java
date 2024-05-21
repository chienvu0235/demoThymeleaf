package com.example.resource_management.security.secure.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.Set;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "role")
public class Role {

  @Id
  @GeneratedValue
  private Long id;

  private String name;

  @OneToMany(mappedBy = "role")
  private Set<UserRole> roleUsers;

}
