package com.mac.doc.service;

import com.mac.doc.dto.FunctionDto;
import com.mac.doc.repository.FunctionRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
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
    void findFunction1() {
        FunctionDto dto = new FunctionDto();
        dto.setFunctionCd("COM1");
        dto.setFunctionNm("공통");

        when(functionService.findFunction(any())).thenReturn(dto);

        Assertions.assertEquals("공통", functionService.findFunction("COM1").getFunctionNm());
        Assertions.assertEquals("공통", functionService.findFunction("COM2").getFunctionNm());

        doThrow(new IllegalArgumentException()).when(functionService).findFunctions();

        Assertions.assertThrows(IllegalArgumentException.class, () -> functionService.findFunctions());
    }

    @Test
    void findFunction2() {
        FunctionDto dto = new FunctionDto();
        dto.setFunctionCd("COM1");
        dto.setFunctionNm("공통");

        when(functionService.findFunction(any()))
                .thenReturn(dto)
                .thenThrow(new RuntimeException())
                .thenReturn(null);

        Assertions.assertEquals("공통", functionService.findFunction("COM1").getFunctionNm());
        Assertions.assertThrows(RuntimeException.class, () -> functionService.findFunction("COM2"));
        Assertions.assertNull(functionService.findFunction("COM3"));
    }
}
