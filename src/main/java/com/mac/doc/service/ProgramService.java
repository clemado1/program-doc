package com.mac.doc.service;

import com.mac.doc.domain.Program;
import com.mac.doc.dto.ProgramDto;

import java.util.List;

public interface ProgramService {

    List<ProgramDto> findPrograms();

    void saveProgram(Program program);

    boolean validateWriter(String programCd);

    boolean checkInProgram(String programCd);

}
