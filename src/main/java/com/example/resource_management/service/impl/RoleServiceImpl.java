package com.example.resource_management.service.impl;

import com.example.resource_management.security.secure.entity.Role;
import com.example.resource_management.service.RoleService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {

//  private final RoleRepository roleRepository;

  @Override
  public List<Role> getAllRoles() {
    return null;
  }
}
