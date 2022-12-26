package com.site.gamingblog.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;

@RequiredArgsConstructor
@Controller
public class HomeController {

    @GetMapping("/")
    public String home(Model model, Principal principal) {

        if(principal != null) {
            String name = principal.getName();
            model.addAttribute("user", name);
        }
        return "index";
    }
}
