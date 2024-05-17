package com.example.resource_management.initial;

import com.example.resource_management.model.Role;
import com.example.resource_management.repository.RoleRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Runner {

  @Autowired
  RoleRepository roleRepository;

  @Bean
  public CommandLineRunner roleCommandLineRunner() {
    return args -> {
      var roles = List.of(
          Role.builder().role("Trưởng phòng").build(),
          Role.builder().role("Phó phòng").build(),
          Role.builder().role("Nhân viên").build(),
          Role.builder().role("Thực tập").build()
      );
      roleRepository.saveAll(roles);
    };
  }
}
