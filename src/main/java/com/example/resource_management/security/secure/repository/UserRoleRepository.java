package com.example.resource_management.security.secure.repository;

import com.example.resource_management.security.secure.entity.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRoleRepository extends JpaRepository<UserRole, Long> {

}
