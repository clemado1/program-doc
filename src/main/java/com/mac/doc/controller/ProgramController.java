package com.mac.doc.controller;

import com.mac.doc.domain.Program;
import com.mac.doc.dto.PrgForm;
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
    public String create(@RequestBody PrgForm prgForm) {
        Program program = Program.builder()
                .programCd(prgForm.getProgramCd())
                .programNm(prgForm.getProgramNm())
                .programType(prgForm.getProgramType())
                .build();

        programService.saveProgram(program);

        return program.getProgramCd();
    }

    @GetMapping("/list")
    public List<Program> list() {
        return programService.findPrograms();
    }
}
