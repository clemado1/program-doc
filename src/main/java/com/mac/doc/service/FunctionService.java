package com.mac.doc.service;

import com.mac.doc.domain.Function;
import com.mac.doc.dto.FunctionDto;

import java.util.List;

public interface FunctionService {

    List<FunctionDto> findFunctions();

    FunctionDto findFunction(String id);

    void saveFunction(Function function);

    boolean validateWriter(FunctionDto functionDto);

    boolean holdFunction(String functionCd);

}
