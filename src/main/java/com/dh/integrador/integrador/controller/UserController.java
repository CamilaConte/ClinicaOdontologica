package com.dh.integrador.integrador.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class UserController {
    @GetMapping("/")
    public ModelAndView home(){
        return new ModelAndView("redirect:/index.html");
    }
}
