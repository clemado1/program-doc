package com.mac.doc.controller;

import com.mac.doc.domain.Document;
import com.mac.doc.service.DocumentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ViewController {

    private final DocumentService documentService;

    public ViewController(DocumentService documentService) {
        this.documentService = documentService;
    }

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
        Document document = Document.builder().docId(docId).build();

        model.addAttribute("docId", docId);
        model.addAttribute("docSn", docSn);
        model.addAttribute("docData", documentService.findAllDocumentSnByDocId(document));

        return "/doc/viewForm";
    }

    @GetMapping(value = {"/doc/diff/{docId}/{docSn1}", "/doc/diff/{docId}/{docSn1}/{docSn2}"})
    public String diff(
            @PathVariable("docId") long docId,
            @PathVariable("docSn1") Long docSn1,
            @PathVariable(value = "docSn2", required = false) Long docSn2,
            Model model) {
        model.addAttribute("docId", docId);
        model.addAttribute("docSn1", docSn1);
        model.addAttribute("docSn2", docSn2);

        return "/doc/diffForm";
    }

    @GetMapping("/doc/list")
    public String list() {

        return "/doc/listForm";
    }
}
