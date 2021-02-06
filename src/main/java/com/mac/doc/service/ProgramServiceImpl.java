package com.mac.doc.service;

import com.mac.doc.domain.Program;
import com.mac.doc.repository.ProgramRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProgramServiceImpl implements ProgramService {
    private final ProgramRepository programRepository;

    public ProgramServiceImpl(ProgramRepository programRepository) {
        this.programRepository = programRepository;
    }

    @Override
    public List<Program> findPrograms() {
        return programRepository.findAllPrograms();
    }

    @Override
    public void saveProgram(Program program) {
        programRepository.save(program);
    }

}
