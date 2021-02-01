package com.mac.doc.service;

import com.mac.doc.domain.Program;
import com.mac.doc.domain.ProgramDocument;
import com.mac.doc.repository.ProgramDocumentRepository;
import com.mac.doc.repository.ProgramRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProgramServiceImpl implements ProgramService {
    private final ProgramRepository programRepository;
    private final ProgramDocumentRepository programDocumentRepository;

    public ProgramServiceImpl(ProgramRepository programRepository, ProgramDocumentRepository programDocumentRepository) {
        this.programRepository = programRepository;
        this.programDocumentRepository = programDocumentRepository;
    }

    @Override
    public List<Program> findPrograms() {
        return programRepository.findAllPrograms();
    }

    @Override
    public void saveProgramDocument(ProgramDocument programDocument) {
        programDocumentRepository.save(programDocument);
    }
}
