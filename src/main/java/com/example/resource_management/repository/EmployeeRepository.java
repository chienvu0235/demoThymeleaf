package com.example.resource_management.repository;

import com.example.resource_management.model.Employee;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

  boolean existsByEmail(String email);

  @Override
  Page<Employee> findAll(Pageable pageable);

  @Query("""
      select e from Employee e Join Role r on e.roleId = r.id
      
      """)
  List<Employee> getAll();
}
