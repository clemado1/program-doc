package com.mac.doc.controller;

import com.mac.doc.domain.Program;
import com.mac.doc.dto.ProgramDto;
import com.mac.doc.service.ProgramService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/prg")
public class ProgramController {
    private final ProgramService programService;

    public ProgramController(ProgramService programService) {
        this.programService = programService;
    }

    @PostMapping("/create")
    public Program create(@RequestBody ProgramDto programDto) {
        Program program = Program.builder()
                .programCd(programDto.getProgramCd())
                .programNm(programDto.getProgramNm())
                .programType(programDto.getProgramType())
                .build();

        programService.saveProgram(program);

        return program;
    }

    @GetMapping("/list")
    public List<ProgramDto> list() {
        return programService.findPrograms();
    }
}
