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
import java.util.Optional;

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

    @PostMapping("/create")
    public Long create(@RequestBody DocForm form) {
        Program program = Program.builder()
                .programCd(form.getProgramCd())
                .build();

        Document document = Document.builder()
                .title(form.getTitle())
                .program(program)
                .build();

        documentService.saveDocument(document);

        DocumentData documentData = DocumentData.builder()
                .document(document)
                .docStat(form.getDocStat())
                .contents(form.getContents())
                .version(form.getVersion())
                .build();

        documentService.saveDocumentData(documentData);

        return document.getDocId();
    }

    @PostMapping("/save")
    public DocumentData save(@RequestBody DocForm form) {
        DocumentData documentData = DocumentData.builder()
                .docSn(Long.parseLong(form.getDocSn()))
                .document(Document.builder().docId(Long.parseLong(form.getDocId())).build())
                .docStat(form.getDocStat())
                .contents(form.getContents())
                .version(form.getVersion())
                .build();

        return documentService.saveDocumentData(documentData);
    }

    @PostMapping("/publish")
    public Long publish(@RequestBody DocForm form) {
        Document document = Document.builder()
                .docId(Long.parseLong(form.getDocId()))
                .build();

        DocumentData documentData = DocumentData.builder()
                .docSn(Long.parseLong(form.getDocSn()))
                .document(document)
                .docStat(DocStat.PUBLISHED)
                .build();

        documentService.publishDocument(documentData);

        return documentData.getDocument().getDocId();
    }

    @GetMapping(value = {"/{docId}", "/{docId}/{docSn}"})
    public Document view(@PathVariable("docId") long docId
            , @PathVariable("docSn") Optional<Long> docSn) throws Exception {
        return documentService.findDocument(docId, docSn).orElseThrow();
    }

    @GetMapping("/list")
    public List<Program> list() {
        return programService.findPrograms();
    }
}
