package com.mac.doc.repository;

import com.mac.doc.dto.FunctionDto;

import java.util.List;

public interface FunctionRepositorySupport {
    List<FunctionDto> findAllFunctions();
}
