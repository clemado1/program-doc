package com.mac.doc.service;

import com.mac.doc.domain.Document;
import com.mac.doc.domain.Label;
import com.mac.doc.domain.Program;
import com.mac.doc.domain.type.DocStat;
import com.mac.doc.repository.DocumentRepository;
import com.mac.doc.repository.LabelRepository;
import com.mac.doc.repository.ProgramRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
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
    ProgramService programService;

    @Autowired
    ProgramRepository programRepository;

    @Test
    void saveDocument() {
        Set<Label> labelSet = new HashSet<>();
        Label label1 = Label.builder().labelId(1L).labelNm("label1").build();
        Label label2 = Label.builder().labelId(2L).labelNm("label2").build();
        labelSet.add(label1);
        labelSet.add(label2);

        Program program = Program.builder().programCd("COM10").programNm("TEST").build();
        Document doc = Document.builder().docStat(DocStat.TEMPSAVE).program(program).title("title1").contents("content1").label(labelSet).build();

        Document newdoc = documentService.saveDocument(doc);

        Assertions.assertThat(newdoc.getDocId()).isEqualTo(doc.getDocId());
    }

    @Test
    void updateDocument() {
    }

    @Test
    void findOne() {
    }

    @Test
    void findDocuments() {
        programRepository.findAll();
    }
}