package com.mac.doc.repository;

import com.mac.doc.domain.Program;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProgramRepository extends JpaRepository<Program, String>, ProgramRepositorySupport {
}
