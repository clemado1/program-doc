package com.mac.doc.repository;

import com.mac.doc.domain.Function;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FunctionRepository extends JpaRepository<Function, String>, FunctionRepositorySupport {
}
