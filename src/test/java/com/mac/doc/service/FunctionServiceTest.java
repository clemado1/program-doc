package com.mac.doc.service;

import com.mac.doc.dto.FunctionDto;
import com.mac.doc.repository.FunctionRepository;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.*;

@ActiveProfiles("test")
@SpringBootTest
@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class FunctionServiceTest {
    @Mock
    FunctionService functionService;

    @Autowired
    FunctionRepository functionRepository;

    @Container
    static PostgreSQLContainer<?> postgreSQLContainer = new PostgreSQLContainer<>("postgres")
            .withDatabaseName("doctest");

    @BeforeAll
    static void beforeAll() {
        postgreSQLContainer.start();
        System.out.println(postgreSQLContainer.getJdbcUrl());
    }

    @AfterAll
    static void afterAll() {
        postgreSQLContainer.stop();
    }

    @Test
    void createFunction(@Mock UserService userService) {
        FunctionService functionService = new FunctionServiceImpl(functionRepository, userService);
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

    @Test
    void findFunction3(@Mock UserService userService) throws Exception {
        // given
        FunctionService functionService = new FunctionServiceImpl(functionRepository, userService);
        Assertions.assertNotNull(functionService);

        FunctionDto dto = new FunctionDto();
        dto.setFunctionCd("COM1");
        dto.setFunctionNm("공통");

        // when
        functionService.validateWriter(dto);

        // then
        verify(userService, times(1)).getSessionUser();
        verify(userService, never()).loadUserByUsername("clemado1");
        // verifyNoInteractions(userService); Error!

        InOrder inOrder = inOrder(userService);
        inOrder.verify(userService).getSessionUser();
    }

    /**
     * BDD
     * - Title
     * - Narrative
     *     - As a / I want / so that
     * - Acceptance criteria
     *     - Given / When / Then
     */
    @Test
    void findFunction4(@Mock UserService userService) throws Exception {
        // given
        FunctionService inService = new FunctionServiceImpl(functionRepository, userService);
        Assertions.assertNotNull(inService);

        FunctionDto dto = new FunctionDto();
        dto.setFunctionCd("COM1");
        dto.setFunctionNm("공통");

        given(functionService.findFunction("COM1")).willReturn(dto);

        // when
        inService.validateWriter(dto);

        // then
        then(userService).should().getSessionUser();

    }
}
