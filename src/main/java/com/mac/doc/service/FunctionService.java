package com.mac.doc.service;

import com.mac.doc.domain.Function;
import com.mac.doc.dto.FunctionDto;

import java.util.List;

public interface FunctionService {

    List<FunctionDto> findFunctions();

    void saveFunction(Function function);

    boolean validateWriter(String functionCd);

    boolean holdFunction(String functionCd);

}
