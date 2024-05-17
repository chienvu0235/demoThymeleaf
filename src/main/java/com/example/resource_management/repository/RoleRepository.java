package com.example.resource_management.repository;

import com.example.resource_management.model.Role;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Integer> {

  List<Role> findAllByOrderByIdAsc();

}
