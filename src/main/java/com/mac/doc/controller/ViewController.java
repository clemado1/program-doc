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
            @RequestParam("functionCd") Optional<String> functionCd,
            Model model) {

        // ifPresent 로 설정 시 view 에서 optional.empty string type 으로 변환되는 문제로 수정
        // TODO: 간단한 방법 찾아보기
        docId.ifPresentOrElse(
                id -> model.addAttribute("docId", id),
                () -> model.addAttribute("docId", null)
        );
        docSn.ifPresentOrElse(
                sn -> model.addAttribute("docSn", sn),
                () -> model.addAttribute("docSn", null)
        );
        functionCd.ifPresentOrElse(
                f -> model.addAttribute("functionCd", f),
                () -> model.addAttribute("functionCd", null)
        );

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
