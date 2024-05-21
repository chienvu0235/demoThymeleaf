package com.example.resource_management.security.secure.controller;

import com.example.resource_management.security.secure.model.CustomUserDetails;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminController {

  @GetMapping
  public String index() {
    return "redirect:/admin/";
  }

  @GetMapping("/")
  public String admin(Model model) {
    CustomUserDetails userDetails = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    model.addAttribute("user", userDetails);
    return "admin/index";
  }
}
