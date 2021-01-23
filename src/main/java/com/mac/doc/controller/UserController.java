package com.mac.doc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserController {
    @GetMapping("/user/login/result")
    public String loginResult() {
        return "redirect:/doc/write";
    }
}
