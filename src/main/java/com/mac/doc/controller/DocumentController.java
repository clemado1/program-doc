package com.mac.doc.controller;

import com.mac.doc.domain.Document;
import com.mac.doc.domain.DocumentData;
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
    public Long save(@RequestBody DocForm form) {
        DocumentData documentData = DocumentData.builder()
                .title(form.getTitle())
                .contents(form.getContents())
                .version(form.getVersion())
                .build();

        Document document = Document.builder()
                .documentData(documentData)
                .docStat(DocStat.TEMPSAVE)
                .build();

        documentService.saveDocument(document);

        return document.getDocId();
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
