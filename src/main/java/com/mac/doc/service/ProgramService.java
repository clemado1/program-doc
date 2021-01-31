package com.mac.doc.service;

import com.mac.doc.domain.Program;
import com.mac.doc.domain.ProgramDocument;

import java.util.List;

public interface ProgramService {

    List<Program> findPrograms();

    void saveProgramDocument(ProgramDocument programDocument);

}
