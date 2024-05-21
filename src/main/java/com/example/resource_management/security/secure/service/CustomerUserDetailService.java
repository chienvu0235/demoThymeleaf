package com.example.resource_management.security.secure.service;

import com.example.resource_management.security.secure.entity.User;
import com.example.resource_management.security.secure.entity.UserRole;
import com.example.resource_management.security.secure.model.CustomUserDetails;
import java.util.Collection;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomerUserDetailService implements UserDetailsService {

  @Autowired
  private UserService userService;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    User user = userService.findByUserName(username);
    if (Objects.isNull(user)) {
      throw new UsernameNotFoundException("Username Not Found");
    }
    Collection<GrantedAuthority> grantedAuthorities = new HashSet<>();
    Set<UserRole> roles = user.getUserRoles();

    for (UserRole userRole : roles) {
      grantedAuthorities.add(new SimpleGrantedAuthority(userRole.getRole().getName()));
    }
    return CustomUserDetails
        .builder()
        .user(user)
        .authorities(grantedAuthorities)
        .build();
  }
}
