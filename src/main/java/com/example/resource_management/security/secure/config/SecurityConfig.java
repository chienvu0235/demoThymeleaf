package com.example.resource_management.security.secure.config;

import com.example.resource_management.security.secure.service.CustomerUserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer.FrameOptionsConfig;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity(debug = true)
public class SecurityConfig {

  @Autowired
  private CustomerUserDetailService customUserDetailService;

  @Bean
  SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    http.csrf(csrf -> csrf.ignoringRequestMatchers("/h2-console/**"))
        .headers(header -> header.frameOptions(FrameOptionsConfig::sameOrigin))
        .authorizeHttpRequests(auth -> auth.requestMatchers("/*", "/h2-console/*").permitAll()
            .requestMatchers("/admin/**").hasAuthority("ADMIN").anyRequest().authenticated())
        .formLogin(login -> login.loginPage("/logon").loginProcessingUrl("/logon")
            .usernameParameter("username").passwordParameter("password")
            .defaultSuccessUrl("/admin", true))
//        .logout(logout -> logout.logoutUrl("/admin-logout").logoutSuccessUrl("/logon"))
        .logout(logout -> logout.logoutUrl("/admin-logout").logoutSuccessUrl("/"));
    return http.build();
  }

  @Bean
  WebSecurityCustomizer webSecurityCustomizer() {
    return web -> web.ignoring().requestMatchers("/static/**", "/assets/**");
  }

  @Bean
  BCryptPasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

}
