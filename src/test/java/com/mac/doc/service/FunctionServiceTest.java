package com.mac.doc.service;

import com.mac.doc.dto.FunctionDto;
import com.mac.doc.repository.FunctionRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class FunctionServiceTest {
    @Mock
    FunctionService functionService;

    @Mock
    FunctionRepository functionRepository;

    @Test
    void createFunction() {
        FunctionService functionService = new FunctionServiceImpl(functionRepository);
        Assertions.assertNotNull(functionService);
    }

    @Test
    void findFunction() {
        FunctionDto dto = new FunctionDto();
        dto.setFunctionCd("COM1");
        dto.setFunctionNm("공통");

        when(functionService.findFunction("COM1")).thenReturn(dto);
    }
}
