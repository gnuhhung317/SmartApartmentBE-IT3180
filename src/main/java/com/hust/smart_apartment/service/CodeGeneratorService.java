package com.hust.smart_apartment.service;

import com.hust.smart_apartment.constants.CodeGeneratorType;
import com.hust.smart_apartment.entity.CodeGenerator;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CodeGeneratorService {

    List<String> generateCodes(CodeGeneratorType type,int length);
    List<CodeGenerator> createGeneratedCodes();
}
