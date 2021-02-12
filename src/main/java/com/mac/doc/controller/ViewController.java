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
            @RequestParam("programCd") Optional<String> programCd,
            Model model) {

        docId.ifPresent(d -> model.addAttribute("docId", d));
        docSn.ifPresent(d -> model.addAttribute("docSn", d));
        programCd.ifPresent(d -> model.addAttribute("programCd", d));

        return "/doc/writeForm";
    }

    @GetMapping(value = {"/doc/{docId}", "/doc/{docId}/{docSn}"})
    public String view(
            @PathVariable("docId") long docId,
            @PathVariable("docSn") Optional<Long> docSn,
            Model model) {
        model.addAttribute("docId", docId);
        docSn.ifPresent(d -> model.addAttribute("docSn", d));

        return "/doc/viewForm";
    }

    @GetMapping("/doc/list")
    public String list() {

        return "/doc/listForm";
    }
}
