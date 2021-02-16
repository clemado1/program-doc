package com.mac.doc.controller;

import com.mac.doc.domain.Document;
import com.mac.doc.domain.DocumentData;
import com.mac.doc.domain.Function;
import com.mac.doc.domain.type.DocStat;
import com.mac.doc.dto.DocumentDto;
import com.mac.doc.dto.FunctionDto;
import com.mac.doc.service.DocumentService;
import com.mac.doc.service.FunctionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/doc")
public class DocumentController {

    private final DocumentService documentService;
    private final FunctionService functionService;

    @Autowired
    public DocumentController(DocumentService documentService, FunctionService functionService) {
        this.documentService = documentService;
        this.functionService = functionService;
    }

    @PostMapping("/create")
    public Document create(@RequestBody DocumentDto form) {
        Function function = Function.builder()
                .functionCd(form.getFunctionCd())
                .build();

        Document document = Document.builder()
                .title(form.getTitle())
                .function(function)
                .build();

        DocumentData documentData = DocumentData.builder()
                .document(document)
                .docStat(form.getDocStat())
                .contents(form.getContents())
                .version(form.getVersion())
                .build();

        documentService.saveDocument(document, documentData);

        return document;
    }

    @PostMapping("/save")
    public DocumentData save(@RequestBody DocumentDto form) {
        DocumentData documentData = DocumentData.builder()
                .docSn(form.getDocSn())
                .document(Document.builder().docId(form.getDocId()).build())
                .docStat(form.getDocStat())
                .contents(form.getContents())
                .version(form.getVersion())
                .build();

        return documentService.saveDocumentData(documentData);
    }

    @PostMapping("/publish")
    public Long publish(@RequestBody DocumentDto form) {
        Document document = Document.builder()
                .docId(form.getDocId())
                .build();

        DocumentData documentData = DocumentData.builder()
                .docSn(form.getDocSn())
                .document(document)
                .docStat(DocStat.PUBLISHED)
                .build();

        documentService.publishDocument(documentData);

        return documentData.getDocument().getDocId();
    }

    @GetMapping(value = {"/{docId}", "/{docId}/{docSn}"})
    public Document view(@PathVariable("docId") long docId
            , @PathVariable("docSn") Optional<Long> docSn) throws Exception {
        Document document = documentService.findDocument(docId, docSn).orElseThrow();
        if (document.getDocumentData() == null) {
            documentService.findFirstDocumentData(document).ifPresent(document::setDocumentData);
        }
        return document;
    }

    @GetMapping("/list")
    public List<FunctionDto> list() {
        return functionService.findFunctions();
    }
}
