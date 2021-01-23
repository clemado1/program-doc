package com.mac.doc.controller;

import com.mac.doc.domain.Document;
import com.mac.doc.domain.Menu;
import com.mac.doc.domain.type.DocStat;
import com.mac.doc.service.DocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Optional;

@Controller
public class DocumentController {

    private final DocumentService documentService;

    @Autowired
    public DocumentController(DocumentService documentService) {
        this.documentService = documentService;
    }


    @GetMapping("/doc/write")
    public String writeForm() {
        return "/doc/write";
    }

    @PostMapping("/doc/save")
    public String save(DocDto form) {
        Menu menu = Menu.builder().menuCd(form.getMenuCd()).menuNm(form.getMenuCd()).build();
        Document document = Document.builder().menu(menu).docStat(DocStat.TEMPSAVE).title(form.getTitle()).contents(form.getContents()).version(form.getVersion()).build();
        Document newDoc = documentService.saveDocument(document);

        return "redirect:/doc/list";
    }

    @GetMapping("/doc/view/{docId}")
    public String view(@PathVariable("docId") long docId) {
        Optional<Document> doc = documentService.findOne(docId);

        return "redirect:/doc/list";
    }

    @GetMapping("/doc/list")
    public String list() {
        return "/doc/list";
    }

    @GetMapping("/test")
    public String test() {
        return "/test";
    }
}
