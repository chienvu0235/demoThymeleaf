package com.example.resource_management.security.secure.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
//@RequestMapping("/")
public class HomeController {

  @GetMapping("/")
    public String home() {
    return "home";
  }

  @GetMapping("/logon")
  public String logon() {
    return "logon";
  }
}
