package com.mac.doc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class ViewController {

    @GetMapping("/user/login")
    public String loginForm() {
        return "/user/loginForm";
    }

    @GetMapping("/doc/write")
    public String writeForm() {
        return "/doc/writeForm";
    }

    @GetMapping("/doc/view/{docId}")
    public String view(@PathVariable("docId") long docId, Model model) {
        model.addAttribute("docId", docId);

        return "/doc/viewForm";
    }

    @GetMapping("/doc/list")
    public String list() {

        return "/doc/listForm";
    }
}
