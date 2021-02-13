package com.mac.doc.service;

import com.mac.doc.domain.Program;
import com.mac.doc.dto.ProgramDto;
import com.mac.doc.repository.ProgramRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProgramServiceImpl implements ProgramService {
    private final ProgramRepository programRepository;

    public ProgramServiceImpl(ProgramRepository programRepository) {
        this.programRepository = programRepository;
    }

    @Override
    public List<ProgramDto> findPrograms() {
        return programRepository.findAllPrograms();
    }

    @Override
    public void saveProgram(Program program) {
        programRepository.save(program);
    }

    @Override
    public boolean validateWriter(String programCd) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserDetails userDetails = (UserDetails) principal;

        return programRepository.findById(programCd)
                .map(program -> program.getPicUser().getUserId().equals(userDetails.getUsername()))
                .orElse(false);
    }

    @Override
    public boolean checkInProgram(String programCd) {
        return false;
    }

}
