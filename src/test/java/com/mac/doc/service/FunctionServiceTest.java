package com.mac.doc.service;

import com.mac.doc.repository.FunctionRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

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
}
