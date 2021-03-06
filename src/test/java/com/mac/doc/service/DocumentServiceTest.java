package com.mac.doc.service;

import com.mac.doc.domain.*;
import com.mac.doc.domain.type.DocStat;
import com.mac.doc.dto.CompareDto;
import com.mac.doc.dto.DocumentDto;
import com.mac.doc.repository.DocumentRepository;
import com.mac.doc.repository.LabelRepository;
import com.mac.doc.repository.FunctionRepository;
import com.mac.doc.repository.FunctionRepositorySupportImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Transactional
@SpringBootTest
class DocumentServiceTest {

    @Autowired
    DocumentService documentService;

    @Autowired
    DocumentRepository documentRepository;

    @Autowired
    LabelService labelService;

    @Autowired
    LabelRepository labelRepository;

    @Autowired
    FunctionService functionService;

    @Autowired
    FunctionRepository functionRepository;

    @Autowired
    FunctionRepositorySupportImpl functionRepositorySupportImpl;

    @Test
    void saveDocument() {
        Set<Label> labelSet = new HashSet<>();
        Label label1 = Label.builder().labelId(1L).labelNm("label1").build();
        Label label2 = Label.builder().labelId(2L).labelNm("label2").build();
        labelSet.add(label1);
        labelSet.add(label2);

        Function function = Function.builder().functionCd("COM10").functionNm("TEST").build();

        DocumentData documentData = DocumentData.builder().docStat(DocStat.TEMPSAVE).contents("content1").label(labelSet).build();

        Document doc = Document.builder().documentData(documentData).title("title1").build();

        documentService.saveDocument(doc, documentData);
        DocumentDto newdoc = documentService.findDocument(doc, null);

        Assertions.assertThat(newdoc.getDocId()).isEqualTo(doc.getDocId());
    }

    @Test
    void testSaveDocument() {
    }

    @Test
    void saveDocumentData() {
    }

    @Test
    void publishDocument() {
    }

    @Test
    void validateWriter() {
    }

    @Test
    void testUpdateDocument() {
    }

    @Test
    void findDocument() {
        Document document = Document.builder().docId(1L).build();
        DocumentDto documentDto = new DocumentDto();
        documentDto = documentService.findDocument(document);
        Assertions.assertThat(documentDto.getDocSn()).isEqualTo(4L);
        documentDto = documentService.findDocument(document, 4L);
        Assertions.assertThat(documentDto.getDocSn()).isEqualTo(4L);
        documentDto = documentService.findDocument(document, 5L);
        Assertions.assertThat(documentDto.getDocSn()).isEqualTo(5L);
    }

    @Test
    void findDocumentData() {
    }

    @Test
    void findFirstDocumentData() {
    }

    @Test
    void testFindDocuments() {
    }

    @Test
    void diffContents() {
        Document document = Document.builder().docId(1L).build();
        CompareDto dto = documentService.compareDocumentData(document, 4L, 5L);



        System.out.println(dto.getDocument().getContents());

    }
}