package com.example.resource_management.controller;

import com.example.resource_management.model.Employee;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/employee")
public class ResourceManagementController {

  @GetMapping("/home")
  public String add(Model model) {
    model.addAttribute("employee", new Employee());
    return "qlnv/home";
  }
}
