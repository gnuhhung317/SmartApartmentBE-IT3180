package com.hust.smart_apartment.service.impl;

import com.hust.smart_apartment.constants.CodeGeneratorType;
import com.hust.smart_apartment.entity.CodeGenerator;
import com.hust.smart_apartment.repository.CodeGeneratorRepository;
import com.hust.smart_apartment.service.CodeGeneratorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CodeGeneratorServiceImpl implements CodeGeneratorService {

    private final CodeGeneratorRepository codeGeneratorRepository;
    @Override
    public List<String> generateCodes(CodeGeneratorType type, int length) {

        CodeGenerator codeGenerator = codeGeneratorRepository.findById(type).orElse(null);
        return List.of();
    }

    @Override
    public List<CodeGenerator> createGeneratedCodes() {
        Set<CodeGeneratorType> saveCodes = codeGeneratorRepository.findAll().stream().map(CodeGenerator::getPrefix).collect(Collectors.toSet());
        Set<CodeGeneratorType> codes = Arrays.stream(CodeGeneratorType.values()).collect(Collectors.toSet());
        Set<CodeGeneratorType> newCodes = codes.stream().filter(code -> !saveCodes.contains(code)).collect(Collectors.toSet());
        List<CodeGenerator> codeGenerators = newCodes.stream().map(code -> CodeGenerator.builder().prefix(code).build()).toList();
        return codeGeneratorRepository.saveAll(codeGenerators);
    }
}
