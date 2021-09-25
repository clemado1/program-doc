package com.mac.doc.service;

import com.mac.doc.domain.Function;
import com.mac.doc.domain.User;
import com.mac.doc.dto.FunctionDto;
import com.mac.doc.repository.FunctionRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FunctionServiceImpl implements FunctionService {
    private final FunctionRepository functionRepository;
    private final UserService userService;

    public FunctionServiceImpl(FunctionRepository functionRepository, UserService userService) {
        this.functionRepository = functionRepository;
        this.userService = userService;
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
    public boolean validateWriter(FunctionDto functionDto) {
        try {
            User sessionUser = userService.getSessionUser();
            return functionRepository.findById(functionDto.getFunctionCd())
                    .map(function -> function.getHoldUser().getUserId().equals(sessionUser.getUserId()))
                    .orElse(false);

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean holdFunction(String functionCd) {
        return false;
    }

}
