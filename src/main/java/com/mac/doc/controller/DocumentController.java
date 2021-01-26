package com.mac.doc.controller;

import com.mac.doc.domain.Document;
import com.mac.doc.domain.Program;
import com.mac.doc.domain.type.DocStat;
import com.mac.doc.service.DocumentService;
import com.mac.doc.service.ProgramService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class DocumentController {

    private final DocumentService documentService;
    private final ProgramService programService;

    @Autowired
    public DocumentController(DocumentService documentService, ProgramService programService) {
        this.documentService = documentService;
        this.programService = programService;
    }

    @GetMapping("/doc/write")
    public String writeForm() {
        return "/doc/write";
    }

    @PostMapping("/doc/save")
    public String save(DocForm form) {
        Program program = Program.builder()
                .programCd(form.getProgramCd())
                .build();

        Document document = Document.builder()
                .program(program)
                .docStat(DocStat.TEMPSAVE)
                .title(form.getTitle())
                .contents(form.getContents())
                .version(form.getVersion())
                .build();

        documentService.saveDocument(document);

        return "redirect:/doc/view/" + document.getDocId();
    }

    @GetMapping("/doc/view/{docId}")
    public String view(@PathVariable("docId") long docId, Model model) throws Exception {
        Document document = documentService.findOne(docId)
                .orElseThrow(() -> new Exception("not found."));
        model.addAttribute("document", document);

        return "/doc/view";
    }

    @GetMapping("/doc/list")
    public String list(Model model) {
        List<Program> programs = programService.findPrograms();
        model.addAttribute("programs", programs);

        return "/doc/list";
    }
}
