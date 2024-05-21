package com.example.resource_management.security.secure.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RequestMapping("/admin")
public class AdminController {

  @GetMapping
  public String index() {
    return "redirect:/admin/";
  }

  @GetMapping("/")
  public String admin() {
    return "admin/index";
  }
}
