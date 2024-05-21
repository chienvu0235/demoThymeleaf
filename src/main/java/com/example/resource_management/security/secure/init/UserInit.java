package com.example.resource_management.security.secure.init;

import com.example.resource_management.security.secure.entity.Role;
import com.example.resource_management.security.secure.entity.User;
import com.example.resource_management.security.secure.entity.UserRole;
import com.example.resource_management.security.secure.repository.RoleRepository;
import com.example.resource_management.security.secure.repository.UserRepository;
import com.example.resource_management.security.secure.repository.UserRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class UserInit implements CommandLineRunner {

  @Autowired
  UserRepository userRepository;

  @Autowired
  UserRoleRepository userRoleRepository;

  @Autowired
  RoleRepository roleRepository;

  @Override
  public void run(String... args) throws Exception {
    User user = User.builder().userName("admin")
        .passWord(new BCryptPasswordEncoder().encode("123456")).enabled(true).build();
    userRepository.save(user);

    Role role = new Role();
    role.setName("ADMIN");
    roleRepository.save(role);

    UserRole userRole = new UserRole();
    userRole.setUser(user);
    userRole.setRole(role);
    userRoleRepository.save(userRole);

  }
}
