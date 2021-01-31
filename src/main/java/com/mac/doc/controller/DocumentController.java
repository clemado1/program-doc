package com.mac.doc.controller;

import com.mac.doc.domain.Document;
import com.mac.doc.domain.Program;
import com.mac.doc.domain.type.DocStat;
import com.mac.doc.dto.DocForm;
import com.mac.doc.service.DocumentService;
import com.mac.doc.service.ProgramService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/doc")
public class DocumentController {

    private final DocumentService documentService;
    private final ProgramService programService;

    @Autowired
    public DocumentController(DocumentService documentService, ProgramService programService) {
        this.documentService = documentService;
        this.programService = programService;
    }

    @PostMapping("/save")
    public String save(@RequestBody DocForm form) {
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

    @GetMapping("/view/{docId}")
    public Document view(@PathVariable("docId") long docId) throws Exception {
        return documentService.findOne(docId)
                .orElseThrow(() -> new Exception("not found."));
    }

    @GetMapping("/list")
    public List<Program> list() {
        return programService.findPrograms();
    }
}
