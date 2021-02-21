package com.mac.doc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ViewController {

    @GetMapping("/user/login")
    public String loginForm() {
        return "/user/loginForm";
    }

    @GetMapping(value = {"/doc/write", "/doc/write/{docId}", "/doc/write/{docId}/{docSn}"})
    public String writeForm(
            @PathVariable(value = "docId", required = false) Long docId,
            @PathVariable(value = "docSn", required = false) Long docSn,
            @RequestParam(value = "functionCd", required = false) String functionCd,
            Model model) {

        model.addAttribute("docId", docId);
        model.addAttribute("docSn", docSn);
        model.addAttribute("functionCd", functionCd);

        return "/doc/writeForm";
    }

    @GetMapping(value = {"/doc/{docId}", "/doc/{docId}/{docSn}"})
    public String view(
            @PathVariable("docId") long docId,
            @PathVariable(value = "docSn", required = false) Long docSn,
            Model model) {
        model.addAttribute("docId", docId);
        model.addAttribute("docSn", docSn);

        return "/doc/viewForm";
    }

    @GetMapping("/doc/list")
    public String list() {

        return "/doc/listForm";
    }
}
