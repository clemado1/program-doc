package com.mac.doc.service;

import com.mac.doc.domain.Function;
import com.mac.doc.dto.FunctionDto;
import com.mac.doc.repository.FunctionRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FunctionServiceImpl implements FunctionService {
    private final FunctionRepository functionRepository;

    public FunctionServiceImpl(FunctionRepository functionRepository) {
        this.functionRepository = functionRepository;
    }

    @Override
    public List<FunctionDto> findFunctions() {
        return functionRepository.findAllFunctions();
    }

    @Override
    public FunctionDto findFunction(String id) {
        return FunctionDto.of(functionRepository.findById(id).orElseThrow());
    }

    @Override
    public void saveFunction(Function function) {
        functionRepository.save(function);
    }

    @Override
    public boolean validateWriter(String functionCd) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserDetails userDetails = (UserDetails) principal;

        return functionRepository.findById(functionCd)
                .map(function -> function.getHoldUser().getUserId().equals(userDetails.getUsername()))
                .orElse(false);
    }

    @Override
    public boolean holdFunction(String functionCd) {
        return false;
    }

}
