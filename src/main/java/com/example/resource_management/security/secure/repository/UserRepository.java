package com.example.resource_management.security.secure.repository;

import com.example.resource_management.security.secure.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
User findByUserName(String userName);

}
