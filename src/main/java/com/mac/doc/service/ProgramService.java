package com.mac.doc.service;

import com.mac.doc.domain.Program;

import java.util.List;

public interface ProgramService {

    List<Program> findPrograms();

    void saveProgram(Program program);

    boolean validateWriter(String programCd);

    boolean checkInProgram(String programCd);

}
