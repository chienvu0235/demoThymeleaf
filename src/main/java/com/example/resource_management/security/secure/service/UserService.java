package com.example.resource_management.security.secure.service;

import com.example.resource_management.security.secure.entity.User;
import com.example.resource_management.security.secure.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

  @Autowired
  private UserRepository userRepository;

  public User findByUserName(String username) {
    return userRepository.findByUserName(username);
  }
}
