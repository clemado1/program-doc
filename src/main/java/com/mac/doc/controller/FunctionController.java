package com.mac.doc.controller;

import com.mac.doc.domain.Function;
import com.mac.doc.dto.FunctionDto;
import com.mac.doc.service.FunctionService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/fnc")
public class FunctionController {
    private final FunctionService functionService;

    public FunctionController(FunctionService functionService) {
        this.functionService = functionService;
    }

    @PostMapping("/create")
    public Function create(@RequestBody FunctionDto functionDto) {
        Function function = Function.builder()
                .functionCd(functionDto.getFunctionCd())
                .functionNm(functionDto.getFunctionNm())
                .functionType(functionDto.getFunctionType())
                .build();

        functionService.saveFunction(function);

        return function;
    }

    @GetMapping("/list")
    public List<FunctionDto> list() {
        return functionService.findFunctions();
    }
}
