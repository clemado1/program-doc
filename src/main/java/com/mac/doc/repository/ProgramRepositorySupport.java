package com.mac.doc.repository;

import com.mac.doc.dto.ProgramDto;

import java.util.List;

public interface ProgramRepositorySupport {
    List<ProgramDto> findAllPrograms();
}
