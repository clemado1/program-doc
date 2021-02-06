package com.mac.doc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Controller
public class ViewController {

    @GetMapping("/user/login")
    public String loginForm() {
        return "/user/loginForm";
    }

    @GetMapping(value = {"/doc/write", "/doc/write/{docId}", "/doc/write/{docId}/{docSn}"})
    public String writeForm(
            @PathVariable("docId") Optional<Long> docId,
            @PathVariable("docSn") Optional<Long> docSn,
            @RequestParam("programCd") String programCd,
            Model model) {
        model.addAttribute("docId", docId);
        model.addAttribute("docSn", docSn);
        model.addAttribute("programCd", programCd);

        return "/doc/writeForm";
    }

    @GetMapping(value = {"/doc/view/{docId}", "/doc/view/{docId}/{docSn}"})
    public String view(
            @PathVariable("docId") long docId,
            @PathVariable("docSn") Optional<Long> docSn,
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
